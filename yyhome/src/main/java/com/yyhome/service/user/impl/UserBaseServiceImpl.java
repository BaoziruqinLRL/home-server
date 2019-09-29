package com.yyhome.service.user.impl;

import com.yyhome.dao.mapper.*;
import com.yyhome.data.example.*;
import com.yyhome.data.po.*;
import com.yyhome.data.vo.menu.MenuVO;
import com.yyhome.service.user.UserBaseService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
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

    @Resource
    private AuthMenuRelationMapper authMenuRelationMapper;

    @Resource
    private UserAuthRelationPOMapper userAuthRelationPOMapper;

    @Resource
    private MenuDictionaryPOMapper menuDictionaryPOMapper;

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

    @Override
    public List<MenuVO> getMenus(Long userId) {
        var menuList = new ArrayList<MenuVO>();
        var uarExample = new UserAuthRelationPOExample();
        var uarCri = uarExample.createCriteria();
        uarCri.andUserIdEqualTo(userId);
        var uarRelations = userAuthRelationPOMapper.selectByExample(uarExample);
        if (!CollectionUtils.isEmpty(uarRelations)){
            var amrExample = new AuthMenuRelationExample();
            var amrCri = amrExample.createCriteria();
            amrCri.andAuthIdIn(uarRelations.stream().map(UserAuthRelationPO::getAuthId).collect(Collectors.toList()));
            var amrRelations = authMenuRelationMapper.selectByExample(amrExample);
            if (!CollectionUtils.isEmpty(amrRelations)){
                var menuExample = new MenuDictionaryPOExample();
                var menuCri = menuExample.createCriteria();
                menuCri.andIdIn(amrRelations.stream().map(AuthMenuRelation::getMenuId).collect(Collectors.toList()));
                var menus = menuDictionaryPOMapper.selectByExampleWithBLOBs(menuExample);
                if (!CollectionUtils.isEmpty(menus)){
                    var menuFamily = menus.stream().collect(Collectors.groupingBy(MenuDictionaryPO::getParentId));
                    var menuMap = menus.stream().collect(Collectors.toMap(MenuDictionaryPO::getId, Function.identity()));
                    menuFamily.forEach((key,list) -> {
                        // 父菜单的parentId为0
                        if (key == 0){
                            list.forEach(li -> {
                                if (!menuFamily.containsKey(li.getId())) {
                                    var vo = new MenuVO();
                                    vo.setMenu(li);
                                    menuList.add(vo);
                                }
                            });
                        }else {
                            if (menuMap.containsKey(key)) {
                                var vo = new MenuVO();
                                vo.setMenu(menuMap.get(key));
                                vo.setChildMenus(list);
                                menuList.add(vo);
                            }
                        }
                    });
                }
            }
        }
        return menuList;
    }
}
