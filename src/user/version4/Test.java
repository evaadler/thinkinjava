package user.version4;

/**
 * 用户管理功能
 * 注册、登录、删除、修改、查询、列表
 * 版本三：使用Map保存User
 * 版本四：使用异常处理机制
 * @author Administrator
 *
 */
public class Test {
	public static void main(String[] args) {
		UserManager um = new UserManager(5);
		um.add(new User("mh","Marty Hall","123",23));
		um.add(new User("lb","Larry Brown","123",23));
		um.add(new User("yc","Yaakov Chaikin","123",23));
		um.add(new User("hsm","胡舒敏","123",23));
		um.add(new User("be","Bruce Eckel","123",23));
		um.add(new User("d","Dawn","123",23));
		um.add(new User("gc","Gary Cornel","123",23));
		
		User user = um.load("lb");
		System.out.println(user);
		
		User updUser = new User("hsm","胡书敏","123",23);
		um.update(updUser);
		System.out.println(um.load("hsm"));
		
		um.login("yc", "123");
		um.login("hsm", "123");
		um.login("gc", "123");
		
		System.out.println(um.getUsers());
		um.delete("hsm");
		System.out.println(um.getUsers());
		
		//um.add(updUser, 1);
		System.out.println(um.getUsers());
		
		try {
			um.login("gc", "ppp");
			System.out.println("欢迎登陆");
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
	}
}
