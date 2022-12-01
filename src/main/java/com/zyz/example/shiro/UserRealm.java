package com.zyz.example.shiro;

import com.zyz.example.constants.AdminConstants;
import com.zyz.example.entity.SysResource;
import com.zyz.example.entity.SysRole;
import com.zyz.example.entity.SysUser;
import com.zyz.example.service.SysResourceService;
import com.zyz.example.service.SysRoleService;
import com.zyz.example.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

public class UserRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(AuthorizingRealm.class);

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;
    
    @Resource
    private SysResourceService sysResourceService;

    /**
     * 授权认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Session session = SecurityUtils.getSubject().getSession();
        SysUser sysUser = (SysUser) session.getAttribute(AdminConstants.SESSION_USER_INFO);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<SysRole> roleList = sysRoleService.getRoleCacheByUserId(sysUser.getId());
        for (SysRole sysRole : roleList) {
            info.addRole(sysRole.getEnName());
            List<SysResource> list = sysResourceService.getSysResourceCacheByRoleId(sysRole.getId());
            for (SysResource sysResource : list) {
                info.addStringPermission(sysResource.getCode());
            }
        }

        return info;
    }


    /**
     * 身份认证
     * 当LoginController.login()方法中执行SecurityUtils.getSubject().login()时，会执行该方法
     * 实现内容：
     * 1.检查令牌的信息
     * 2.从数据库中获取用户信息
     * 3.对用户信息验证
     * 4.返回封装用户信息的AuthenticationInfo实例
     * 5.验证失败会返回AuthenticationException异常
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());

        SysUser sysUser = sysUserService.authLogin(username, password);
        // 没有找到用户
        if (sysUser == null) {
            log.error("loginName: {}, password: {}", username, password);
            throw new UnknownAccountException("用户名或密码错误！");
        }

        // 账号已冻结(0)
        if (sysUser.getUserState() == 0) {
            log.error("用户（{})）已被冻结！", username, password);
            throw new DisabledAccountException("用户已被冻结！");
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
