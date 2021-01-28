package cn.xjt.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容：
 *
 * @author
 * @date 2020/10/10-13:51
 */

@Controller
public class MyController {

	@RequestMapping({"/","/index"})
	public String toindex(Model model)
	{
		model.addAttribute("msg", "hello,shiro");
		return "index";
	}

	@RequestMapping("user/add")
	public String add()
	{
		return "user/add";
	}

	@RequestMapping("user/update")
	public String update()
	{
		return "user/update";
	}


	@RequestMapping("/tologin")
	public String tologin()
	{
		return "login";
	}

	@RequestMapping("/login")
	public String login(String username,String password,Model model)
	{
		//获取当前用户
		Subject subject = SecurityUtils.getSubject();
		//封装用户得登录数据
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);

		try{
			//执行登录，如果没有异常就说明没有问题
			subject.login(token);
			model.addAttribute("login", token.getUsername());
			return "index";
		}catch (UnknownAccountException e)  //用户名不存在
		{
			System.out.println("用户名不存在");
			model.addAttribute("msg", "用户名 不存在");
			return "login";
		}catch (IncorrectCredentialsException ec)//密码不存在
		{
			model.addAttribute("msg","密码错误");
			return "login";
		}
	}

	@RequestMapping("/noauthor")
	@ResponseBody
	public String Exception()
	{
		return "没有足够得权限访问改页面";
	}

	@RequestMapping("/logout")
	public String logoiut()
	{
		Subject subject = SecurityUtils.getSubject();
		System.out.println("执行了注销");
		subject.logout();
		return "index";
	}
}
