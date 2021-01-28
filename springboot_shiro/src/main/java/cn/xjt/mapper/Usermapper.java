package cn.xjt.mapper;

import cn.xjt.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 内容：
 *
 * @author
 * @date 2020/10/10-17:41
 */

@Repository
@Mapper
public interface Usermapper {

	User getUserByName(String name);
}
