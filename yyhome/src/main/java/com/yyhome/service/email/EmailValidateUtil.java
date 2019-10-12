package com.yyhome.service.email;

import com.yyhome.data.vo.mail.EmailJobVO;
import org.apache.commons.collections.CollectionUtils;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * @author miluo
 * @date 2019-10-12
 */
public class EmailValidateUtil {

    public static String validateRule(EmailJobVO email){
        if (CollectionUtils.isEmpty(email.getRules())){
            return "rules can't be null";
        }
        if (email.getRules().size() > 1) {
            for (var rule : email.getRules()) {
                for (var rule2 : email.getRules()) {
                    if ((rule.getId() != null && rule2.getId() != null && rule.getId().equals(rule2.getId()) ||
                            (rule.getId() == null && rule2.getId() == null))) {
                        // 同一条规则不比较
                        continue;
                    }
                    if (rule.getRuleSort().equals(rule2.getRuleSort())){
                        return MessageFormat.format("不能存在相同的规则序号{0}",rule.getRuleSort());
                    }
                    if (rule.getEndDate() == null && rule2.getEndDate() == null){
                        return MessageFormat.format("不能同时存在两条永久规则, 规则{0}和{1}",rule.getRuleSort(),rule2.getRuleSort());
                    }
                    if (rule.getEndDate() == null && rule.getRuleSort() < rule2.getRuleSort()){
                        return MessageFormat.format("永久规则只能为最后一条规则, 永久规则为{0}",rule.getRuleSort());
                    }
                    if (rule.getRuleSort() > rule2.getRuleSort() &&
                            rule2.getEndDate() != null &&
                            rule.getStartDate().before(rule2.getEndDate())) {
                        return MessageFormat.format("规则时间重叠,规则{0}开始时间{1} 小于 规则{2}结束时间{3}",
                                rule.getRuleSort(),
                                LocalDate.ofInstant(rule.getStartDate().toInstant(), ZoneId.systemDefault()),
                                rule2.getRuleSort(),
                                LocalDate.ofInstant(rule2.getEndDate().toInstant(),ZoneId.systemDefault()));
                    }
                }
            }
        }
        return null;
    }
}
