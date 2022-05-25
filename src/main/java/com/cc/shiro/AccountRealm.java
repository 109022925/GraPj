package com.cc.shiro;

import com.cc.pojo.User;
import com.cc.service.Impl.UserServiceImpl;
import com.cc.utils.JwtToken;
import com.cc.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    UserServiceImpl userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = JwtUtil.getUsernameFromToken(principalCollection.toString());
        List<User> user = userService.getByUsername(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // TODO 改为从数据库中获取该用户的角色

        if (!user.isEmpty()) {
            // 根据role值给予权限
            if (user.get(0).getRole() == User.Role.admin) {
                authorizationInfo.addRole("admin");
            }
            if (user.get(0).getRole() == User.Role.user) {
                authorizationInfo.addRole("user");
            }
        }

//        authorizationInfo.addStringPermission();

        return authorizationInfo;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("身份认证");
        // 这里的 token是从 JWTFilter 的 executeLogin() 方法传递过来的
        String token = (String) authenticationToken.getCredentials();
        // 解密
        String username = JwtUtil.getUsernameFromToken(token);
        if (StringUtils.isEmpty(username) || !JwtUtil.verify(token, username)) {
            log.error("token 认证失败");
            throw new AuthenticationException("token 认证失败");
        }
        // TODO 改为从数据库获取对应用户名密码的用户

        List<User> u = userService.getByUsername(username);
        if (!u.isEmpty()) {
            log.info("用户{}认证成功！", u.get(0).getUsername());
        } else {
            log.error("账号或密码错误");
            throw new AuthenticationException("账号或密码错误");
        }


        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
