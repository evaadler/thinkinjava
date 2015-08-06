package emp;

import org.dom4j.Document;

import user.version1.User;
import emp.dao.UserDao;
import emp.util.XMLUtil;

public class TestUser {
	private static UserDao ud = new UserDao();
	
	public static void main(String[] args) {
		testAdd();
	}
	
	public static void testSingleton(){
		Document d1 = XMLUtil.getUserDocument();
		Document d2 = XMLUtil.getUserDocument();
		System.out.println(d1==d2);
	}
	
	public static void testAdd(){
		User u = new User();
		u.setNickname("张三");
		u.setPassword("123");
		ud.add(u);
	}
	
	public static void testLoad(){
		User u = ud.load("111");
		System.out.println(u);
	}
}
