package com.yyhome.job;

import com.alibaba.fastjson.JSON;
import com.yyhome.common.BeanTools;
import com.yyhome.common.timewheel.TimeoutNotification;
import com.yyhome.common.timewheel.TimerWheel;
import com.yyhome.data.bo.EmailJobBO;
import com.yyhome.data.bo.EmailJobRuleBO;
import com.yyhome.data.vo.mail.EmailJobVO;
import com.yyhome.service.email.EmailJobService;
import com.yyhome.service.email.EmailSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author miluo
 * @date 2019-10-10
 */
@Component
@Slf4j
public class EmailSendProcessor {

    @Resource
    private EmailSendService sendService;

    @Resource
    private EmailJobService jobService;

    private TimerWheel<EmailJobBO> timerWheel = new TimerWheel<>(new RetryWheelImpl());

    @PostConstruct
    private void init(){
        var jobParam = new EmailJobVO();
        jobParam.setUserId(10000L);
        var jobList = jobService.getEmailJobList(jobParam);
        jobList.forEach(job -> {
            var mailJob = BeanTools.copy(job,EmailJobBO.class);
            var nextTime = this.calculateNextSendTime(mailJob);
            if (nextTime >= 0) {
                this.addToWheel(mailJob, nextTime - System.currentTimeMillis());
            }
        });
    }

    /**
     * 添加至轮转
     * @param key 轮转key
     */
    private void addToWheel(EmailJobBO key, long time){
        if (key == null){
            return;
        }
        timerWheel.add(key,time);
    }

    /**
     * 从轮转中移除
     * @param key 移除数据
     */
    private void removeFromWheel(EmailJobBO key){
        if (key == null){
            return;
        }
        timerWheel.remove(key);
    }

    /**
     * 重置邮件任务
     * @param email 邮件任务
     */
    public void reAddToWheel(EmailJobBO email){
        log.info("重置邮件任务. 任务详情: {}",JSON.toJSONString(email));
        this.removeFromWheel(email);
        var nextTime = this.calculateNextSendTime(email);
        this.addToWheel(email,nextTime - System.currentTimeMillis());
    }

    private class RetryWheelImpl implements TimeoutNotification<EmailJobBO> {

        @Override
        public long notice(EmailJobBO email) {
            sendService.sendTextMail(email);
            removeFromWheel(email);
            var nextTime = calculateNextSendTime(email);
            addToWheel(email,nextTime - System.currentTimeMillis());
            return 0L;
        }
    }

    /**
     * 计算下次发送邮件时间,由以下几种情况组成
     * 1.满足起始发送时间则
     *   1-1. 无结束日期, 永久规则
     *      1-1-1. 无结束时间, 则满足发送时间区间(startTime - 23:59:59)
     *      1-1-2. 有结束时间, 判断是否跨天
     *          1-1-2-1. 跨天, 当前时间若在 (endTime - startTime) 内, 则不能发送, 反之满足发送区间
     *          1-1-2-2. 非跨天, 当前时间在 (startTime - endTime) 内, 满足发送区间
     *   1-2. 有结束日期, 非永久规则
     *      1-2-1. 若当前日期超过结束日期, 则规则作废
     *      1-2-2. 当前日期没有超过结束日期, 判断时间区间
     *          1-2-2-1. 跨天, 当前时间若在 (endTime - startTime) 内, 则作废, 反之满足发送区间
     *          1-2-2-2. 非跨天, 当前时间在 (startTime - endTime) 内, 满足发送区间
     * 2. 不满足起始发送时间, 返回-1
     * @param email 邮件
     * @return 时间,不满足则返回-1, 否则返回计算的时间
     */
    private long calculateNextSendTime(EmailJobBO email){
        var nextTime = -1L;
        for (var rule : email.getRules()){
            var now = LocalDateTime.now();
            var needCalculate = false;
            if (now.isAfter(LocalDateTime.of(LocalDate.ofInstant(rule.getStartDate().toInstant(), ZoneId.systemDefault()),
                    LocalTime.ofInstant(rule.getStartTime().toInstant(),ZoneId.systemDefault())))){
                // 达到开始时间
                if (rule.getEndDate() == null){
                    // 永久规则
                    if (rule.getEndTime() == null){
                        // 每日无限定结束时间,默认为0点,满足条件
                        needCalculate = true;
                    }else{
                        var tempEndTime = LocalTime.ofInstant(rule.getEndTime().toInstant(),ZoneId.systemDefault());
                        if (tempEndTime.equals(LocalTime.of(0,0,0))){
                            // 做一下转换,否则0点要把日期+1天
                            tempEndTime = LocalTime.of(23,59,59);
                        }
                        if (tempEndTime.isBefore(LocalTime.ofInstant(rule.getStartTime().toInstant(),ZoneId.systemDefault()))){
                            // 跨天,则判定是否在可执行范围内,即 结束时间<当前<开始时间 为不可执行时间
                            if (now.toLocalTime().isAfter(tempEndTime) && now.toLocalTime().isBefore(LocalTime.ofInstant(rule.getStartTime().toInstant(),ZoneId.systemDefault()))){
                                // 不在执行时间内,跳过该任务
                                log.info("跨天永久任务不在执行时间内,跳过该任务. 任务规则详情: {}", JSON.toJSONString(rule));
                                break;
                            }else{
                                // 在执行时间内
                                needCalculate = true;
                            }
                        }else {
                            // 非跨天
                            if (now.isBefore(LocalDateTime.of(LocalDate.now(), tempEndTime))) {
                                // 未超过当天的结束时间
                                needCalculate = true;
                            }else{
                                // 不在执行时间内,跳过该任务
                                log.info("非跨天永久任务不在执行时间内,跳过该任务. 任务规则详情: {}", JSON.toJSONString(rule));
                                break;
                            }
                        }
                    }
                }else{
                    // 非永久规则, 超过结束日期则作废
                    if (now.toLocalDate().isAfter(LocalDate.ofInstant(rule.getEndDate().toInstant(),ZoneId.systemDefault()))){
                        // 超过结束日期, 直接作废
                        log.info("任务规则超过结束日期,跳过该规则. 任务规则详情: {}", JSON.toJSONString(rule));
                    }else{
                        if (rule.getEndTime() == null){
                            // 每日无限定结束时间,默认为23:59:59点
                            needCalculate = true;
                        }else {
                            var tempEndTime = LocalTime.ofInstant(rule.getEndTime().toInstant(), ZoneId.systemDefault());
                            if (tempEndTime.equals(LocalTime.of(0, 0, 0))) {
                                // 做一下转换,否则0点要把日期+1天
                                tempEndTime = LocalTime.of(23, 59, 59);
                            }
                            if (tempEndTime.isBefore(LocalTime.ofInstant(rule.getStartTime().toInstant(), ZoneId.systemDefault()))) {
                                // 跨天,则判定是否在可执行范围内,即 结束时间<当前<开始时间 为不可执行时间
                                if (now.toLocalTime().isAfter(tempEndTime) && now.toLocalTime().isBefore(LocalTime.ofInstant(rule.getStartTime().toInstant(), ZoneId.systemDefault()))) {
                                    // 不在执行时间内,跳过该任务
                                    log.info("跨天非永久规则任务不在执行时间内,跳过该任务. 任务规则详情: {}", JSON.toJSONString(rule));
                                    break;
                                } else {
                                    // 在执行时间内
                                    needCalculate = true;
                                }
                            } else {
                                // 非跨天
                                if (now.toLocalTime().isBefore(tempEndTime) &&
                                        now.toLocalTime().isAfter(LocalTime.ofInstant(rule.getStartTime().toInstant(),ZoneId.systemDefault()))) {
                                    // 在当天的执行时间内
                                    needCalculate = true;
                                } else {
                                    log.info("非跨天非永久规则任务不在执行时间内,跳过该任务. 任务规则详情: {}", JSON.toJSONString(rule));
                                }
                            }
                        }
                    }
                }
            }
            if (needCalculate){
                // 计算下次发送时间点
                nextTime = this.calculateTime(rule);
                log.info("邮件 \"{}\" 下次发送时间: {}",email.getSubject(),LocalDateTime.ofInstant(new Date(nextTime).toInstant(),ZoneId.systemDefault()).toString());
            }
        }
        return nextTime;
    }

    private long calculateTime(EmailJobRuleBO rule){
        var time = -1L;
        if (rule.getEndTime() != null) {
            var tempEndTime = LocalTime.ofInstant(rule.getEndTime().toInstant(), ZoneId.systemDefault());
            if (tempEndTime.equals(LocalTime.of(0, 0, 0))) {
                // 做一下转换,否则0点要把日期+1天
                tempEndTime = LocalTime.of(23, 59, 59);
            }
            if (rule.getEndTime().before(rule.getStartTime())) {
                // 跨天, 则时间点需要计算00:00-tempEndTime 以及 startTime-23:59:59两个区间
                var zeroTime = LocalTime.of(0, 0, 0);
                var tempStartTime = LocalTime.ofInstant(rule.getStartTime().toInstant(), ZoneId.systemDefault());
                var nowTime = LocalTime.now();
                if (nowTime.isBefore(tempEndTime)) {
                    // 00:00-tempEndTime
                    while (zeroTime.isBefore(nowTime)) {
                        zeroTime = zeroTime.plusMinutes(rule.getInterval());
                    }
                    time = Date.from(LocalDateTime.of(LocalDate.now(), zeroTime).atZone(ZoneId.systemDefault()).toInstant()).getTime();
                } else {
                    // startTime-23:59:59
                    while (tempStartTime.isBefore(nowTime)) {
                        tempStartTime = tempStartTime.plusMinutes(rule.getInterval());
                    }
                    time = Date.from(LocalDateTime.of(LocalDate.now(), tempStartTime).atZone(ZoneId.systemDefault()).toInstant()).getTime();
                }
            } else {
                var tempStartTime = LocalTime.ofInstant(rule.getStartTime().toInstant(), ZoneId.systemDefault());
                var nowTime = LocalTime.now();
                // 非跨天, 时间点需要计算startTime-tempEndTime
                while (tempStartTime.isBefore(nowTime)) {
                    tempStartTime = tempStartTime.plusMinutes(rule.getInterval());
                }
                time = Date.from(LocalDateTime.of(LocalDate.now(), tempStartTime).atZone(ZoneId.systemDefault()).toInstant()).getTime();
            }
        }else{
            // 无结束时间,永久任务,则计算startTime-23:59:59
            var tempStartTime = LocalTime.ofInstant(rule.getStartTime().toInstant(), ZoneId.systemDefault());
            var nowTime = LocalTime.now();
            // 非跨天, 时间点需要计算startTime-tempEndTime
            while (tempStartTime.isBefore(nowTime)) {
                tempStartTime = tempStartTime.plusMinutes(rule.getInterval());
            }
            time = Date.from(LocalDateTime.of(LocalDate.now(), tempStartTime).atZone(ZoneId.systemDefault()).toInstant()).getTime();
        }
        return time;
    }
}
