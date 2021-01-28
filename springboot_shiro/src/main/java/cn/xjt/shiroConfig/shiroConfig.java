package cn.xjt.shiroConfig;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 内容：
 *
 * @author  徐江涛
 * @date 2020/10/10-14:01
 */

@Configuration
public class shiroConfig {

	//ShiroFilterFactoryBean        3、
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("security") DefaultWebSecurityManager defaultWebSecurityManager)
	{
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		//设置安全管理器
		bean.setSecurityManager(defaultWebSecurityManager);
//		添加shiro内置过滤器
		/*
			anon:无需认证就能访问
			authc:必须认证才能访问
			user：必须拥有  记住我  功能才能使用
			perms:拥有对某个资源得权限才能访问
			role：拥有某个角色权限才能访问
		 */

//		登录拦截
		Map<String, String> filter = new LinkedHashMap<>();

		//授权
		filter.put("/user/add", "perms[user:add]");
		filter.put("/user/update", "perms[user:update]");
//		filter.put("/add", "authc");
//		filter.put("/update","authc");
		//可以使用下面通配符使用
		filter.put("/user/*", "authc");


		bean.setFilterChainDefinitionMap(filter);
		//设置登陆的请求
		bean.setLoginUrl("/tologin");
		//设置未授权得页面
		bean.setUnauthorizedUrl("/noauthor");

		return bean;
	}


	//defaultWebSecurityManager     2、
	@Bean(name = "security")
	public DefaultWebSecurityManager defaultWebSecurityManager( @Qualifier("userRealm") UserRealm userRealm)
	{
		DefaultWebSecurityManager d = new DefaultWebSecurityManager();
		//关联realm
		d.setRealm(userRealm);
		return d;
	}


	//创建realm对象，需要自定义       1、
	@Bean   //添加自定义配置到配置类
			(name = "userRealm")        //可有可无，方法名就是默认得spring管理中得别名
	public UserRealm userRealm()
	{
		return new UserRealm();
	}

//	整合shiroDialect：用来整合shiro thymeleaf
	@Bean
	public ShiroDialect getShiroDialect()
	{
		return new ShiroDialect();
	}
}
