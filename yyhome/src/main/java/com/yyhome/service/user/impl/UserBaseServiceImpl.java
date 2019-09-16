package com.yyhome.service.user.impl;

import com.yyhome.dao.example.FamilyMemberRelationPOExample;
import com.yyhome.dao.example.MemberPOExample;
import com.yyhome.dao.example.UserFamilyRelationPOExample;
import com.yyhome.dao.mapper.FamilyMemberRelationPOMapper;
import com.yyhome.dao.mapper.MemberPOMapper;
import com.yyhome.dao.mapper.UserFamilyRelationPOMapper;
import com.yyhome.dao.po.FamilyMemberRelationPO;
import com.yyhome.dao.po.MemberPO;
import com.yyhome.dao.po.UserFamilyRelationPO;
import com.yyhome.service.user.UserBaseService;
import lombok.var;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author miluo
 * @date 2019-09-16
 */
@Component
public class UserBaseServiceImpl implements UserBaseService {

    @Resource
    private UserFamilyRelationPOMapper umrMapper;

    @Resource
    private FamilyMemberRelationPOMapper fmrMapper;

    @Resource
    private MemberPOMapper memberMapper;

    @Override
    @SuppressWarnings("unchecked")
    public List<MemberPO> getMemberList(Long userId) {
        var memberList = Collections.EMPTY_LIST;
        var umrExample = new UserFamilyRelationPOExample();
        var umrCri = umrExample.createCriteria();
        umrCri.andUserIdEqualTo(userId);
        var umrRelations = umrMapper.selectByExample(umrExample);
        if (!CollectionUtils.isEmpty(umrRelations)){
            var fmrExample = new FamilyMemberRelationPOExample();
            var fmrCri = fmrExample.createCriteria();
            fmrCri.andFamilyIdIn(umrRelations.stream().map(UserFamilyRelationPO::getFamilyId).collect(Collectors.toList()));
            var fmrRelations = fmrMapper.selectByExample(fmrExample);
            if (!CollectionUtils.isEmpty(fmrRelations)){
                var memberExample = new MemberPOExample();
                var memberCri = memberExample.createCriteria();
                memberCri.andIdIn(fmrRelations.stream().map(FamilyMemberRelationPO::getMemberId).collect(Collectors.toList()));
                memberList = memberMapper.selectByExample(memberExample);
            }
        }
        return memberList;
    }
}
