package cn.xjt.shiroConfig;

import cn.xjt.mapper.Usermapper;
import cn.xjt.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

/**
 * 内容：
 *
 * @author
 * @date 2020/10/10-14:03
 */

//自定义得UserRealm
public class UserRealm extends AuthorizingRealm {

	@Autowired
	Usermapper usermapper;
//	授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("执行了授权");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Subject subject = SecurityUtils.getSubject();
		User currentUser = (User) subject.getPrincipal();
		info.addStringPermission(currentUser.getPerms());
		return info;
	}
//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("执行了认证");
		//获取用户名，密码
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;

		User user = usermapper.getUserByName(usernamePasswordToken.getUsername());

		System.out.println(user);

		if(user==null){
			return null;
		}
		Session session = subject.getSession();
		session.setAttribute("login", user.getName());
		return new SimpleAuthenticationInfo(user,user.getPwd(),"");
	}
}
