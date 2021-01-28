package cn.xjt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 内容：
 *
 * @author
 * @date 2020/10/10-17:40
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int id;
	private String name;
	private String pwd;
	private String perms;
}
