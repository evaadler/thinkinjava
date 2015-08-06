package user.version5;

import java.io.Serializable;
import java.util.*;

/**
 * 为每一个POJO提供一个管理类来完成对这个对象的管理
 * 
 * @author Nina
 * 
 */
public class UserManager implements Serializable {
	private List<String> forbitUsers = Arrays.asList("张胜男","莉莉丝");
	Map<String, User> users;

	/**
	 * new User("zs","张三","123",23) ,new User("ls","李四","123",23) ,new
	 * User("ww","王五","123",23) ,new User("zl","赵六","123",23) ,new
	 * User("zs","朱七","123",23)
	 * 
	 */

	public UserManager() {
		users = new HashMap<String, User>();

	}

	public void add(User user) {
		if(user.getPassword().length()<=1){
			throw new UserException("密码长度不够啊，要至少1位");
		}
		if(forbitUsers.contains(user.getNickname())){
			throw new UserException("天哪，你来了，但是你不能被注册");
		}
		if (load(user.getUsername()) != null) {
			//System.out.println("用户已经存在!!");
			throw new UserException("用户已经存在!!");
		}

		users.put(user.getUsername(), user);
		System.out.println("您添加【" + user.getNickname() + "】成功");

	}

	/**
	 * 指定位置添加用戶
	 * 
	 * @param user
	 * @param location
	
	public void add(User user, int location) {
		if (load(user.getUsername()) != null) {
			System.out.println("你搞什么，你已经在里面了呀！");
		}

		users.add(location, user);
	}
	 */
	/* 不存在上面的方法   */

	/**
	 * 
	 * @param user
	 */
	public void update(User user) {
		User u = load(user.getUsername());

		if (user == null) {
			//System.out.println("要修改的用户不存在呀！");
			throw new UserException("要修改的用户不存在呀！");
		}

		u.setAge(user.getAge());
		u.setNickname(user.getNickname());
		u.setPassword(user.getPassword());
	}

	/**
	 * 删除用户
	 * 
	 * @param name
	 */
	public void delete(String name) {
		if (name == null || name.equals("")) {
			return;
		}

		if (load(name) == null) {
			//System.out.println("要删除的用户不存在哪");
			throw new UserException("要删除的用户不存在哪!!");
		}

		users.remove(load(name));

	}

	/**
	 * 加载用户
	 * 
	 * @param username
	 * @return
	 */
	public User load(String username) {
		if (username == null || "".equals(username)) {
			System.out.println("木有输入用户名啊！");
			throw new UserException("木有输入用户名啊！");
		}
		
		return users.get(username);
	}

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 */
	public User login(String username, String password) {
		if (username == null || password == null) {
			//System.out.println("怎么不输入用户名和密码呀!");
			throw new UserException("怎么不输入用户名和密码呀!");
		}

		User user = load(username);
		if (user == null) {
			//System.out.println("哪里来的？没有你这个用户！");
			throw new UserException("哪里来的？没有你这个用户！");
			//return null;
		}

		if (password.equals("123456")) {
			System.out.println("哎呦呦，您可终于来了，等您好久了!!");
		} else {
			//System.out.println("您密码输错了吧？");
			throw new UserException("您密码输错了吧？");
			//return null;
		}
		return user;
	}

	/**
	 * 有多少元素，返回多少元素
	 * 
	 * @return
	 */
	public List<User> getUsers() {
		showAll();
		return new ArrayList<User>(users.values());
	}

	public void showAll() {
		for(String key : users.keySet()){
			System.out.println(users.get(key).getNickname());
		}
	}

}
