package com.cc.shiro;


import com.cc.utils.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {
    // 如果请求头带有token，则对token进行检查；否则，直接放行
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 判断请求头是否带有 token
        if (isLoginAttempt(request, response)) {
            // 如果存在 token ，则进入executeLogin()方法执行登入，并检测 token 的正确性
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                log.error("Error! {}", e.getMessage());
                responseError(response, e.getMessage());
            }
        }
        // 如果不存在 token ，则可能是执行登录操作/游客访问状态，所以直接放行
        return true;
    }

    // 检测 header中是否包含 token
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        return getTokenFromRequest(request) != null;
    }

    // 执行登入操作
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        String token = getTokenFromRequest(request);
        JwtToken jwtToken = new JwtToken(token);
        // 提交给 realm 进行登入，如果错误，会抛出异常并捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常，则代表登入成功，返回 true
        return true;
    }

    // 从请求中获取 token
    private String getTokenFromRequest(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        return req.getHeader("Token");
    }

    // 非法请求将跳转到 "/unauthorized/**"
    private void responseError(ServletResponse response, String message) {
        try {
            HttpServletResponse resp = (HttpServletResponse) response;
            // 设置编码，否则中文字符在重定向时会变为空字符串
            message = URLEncoder.encode(message, "UTF-8");
            resp.sendRedirect("/unauthorized/" + message);
        } catch (UnsupportedEncodingException e) {
            log.error("Error! {}", e.getMessage());
        } catch (IOException e) {
            log.error("Error! {}", e.getMessage());
        }
    }
}
