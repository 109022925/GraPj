package com.cc.config;

import com.cc.shiro.AccountRealm;
import com.cc.shiro.JwtFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 注册安全管理器
        bean.setSecurityManager(securityManager);

        // 添加过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", new JwtFilter());

        // 设置无权限时跳转的 url;
        bean.setUnauthorizedUrl("user/relogin");


        // 添加内置过滤器
        /*
            anon： 无需认证就可以访问
            authc：必须认证才可以访问
            user：必须拥有 记住我 才可以访问
            perms：拥有对某个资源的权限才能访问
            role：拥有某个角色权限才能访问
         */
        Map<String, String> filterRuleMap = new LinkedHashMap<>();
        // 登录不拦截
        filterRuleMap.put("/user/login", "anon");
        filterRuleMap.put("/unauthorized/**", "anon");
        // 处理swagger不能访问问题
        filterRuleMap.put("/swagger-ui.html", "anon");
        filterRuleMap.put("/swagger**/**", "anon");
        filterRuleMap.put("/webjars/**", "anon");
        filterRuleMap.put("/v2/**", "anon");

        // 所有请求都要通过JWT Filter
        filterRuleMap.put("/**", "jwt");

        bean.setFilters(filterMap);
        bean.setFilterChainDefinitionMap(filterRuleMap);

        bean.setLoginUrl("");

        return bean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("accountRealm") AccountRealm accountRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联Realm
        securityManager.setRealm(accountRealm);

//        // 关闭shiro自带的session
//        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);

        return securityManager;
    }

    @Bean(name = "accountRealm")
    public AccountRealm accountRealm() {
        return new AccountRealm();
    }

    /**
     * 添加注解支持
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
