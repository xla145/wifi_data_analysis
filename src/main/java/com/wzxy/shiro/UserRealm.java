/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wzxy.shiro;


import com.wzxy.service.auth.ISysActionService;
import com.wzxy.service.sys.sysuser.ISysUserService;
import com.wzxy.service.vo.sys.SysAction;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author 知秋
 * @email fei6751803@163.com
 */
public class UserRealm extends AuthorizingRealm{
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysActionService sysActionService;

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysAction.SysUser user = (SysAction.SysUser)principalCollection.getPrimaryPrincipal();
        Integer userId = user.getUid();

        List<String> permsList;
        List<SysAction> menuList;
        //系统管理员，拥有最高权限
        if(userId == 1){
            menuList = sysActionService.getAllAction();
        }else{
            menuList = sysActionService.getSysUserActionByUid(userId);
        }
        permsList = new ArrayList<>(menuList.size());
        for(SysAction menu : menuList){
            permsList.add(menu.getPerms());
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        //查询用户信息
        SysAction.SysUser user = sysUserService.getSysUser(username);

        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        //密码错误
        if(!password.equals(user.getPswd())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        //账号锁定
        if(user.getIsValid() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        return new SimpleAuthenticationInfo(user, password, getName());
    }

}
