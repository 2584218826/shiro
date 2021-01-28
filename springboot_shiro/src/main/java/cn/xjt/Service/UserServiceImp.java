package cn.xjt.Service;

import cn.xjt.mapper.Usermapper;
import cn.xjt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 内容：
 *
 * @author
 * @date 2020/10/10-17:46
 */
public class UserServiceImp implements UserService{

	@Autowired
	Usermapper usermapper;

	@Override
	public User getUserByName(String name) {
		return usermapper.getUserByName(name);
	}
}
