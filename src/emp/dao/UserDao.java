package emp.dao;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import user.version1.User;
import emp.EmpException;
import emp.util.XMLUtil;

public class UserDao {
	private Document ud;
	private static final String USER_XML = "users";

	public UserDao() {
		ud = XMLUtil.getUserDocument();
	}

	private void write() {
		XMLUtil.write2XML(ud, USER_XML);
	}

	public void add(User user) {
		// ud.add(user);
		if (user.getUsername() == null || "".equals(user.getUsername()))
			throw new EmpException("用户名不能为空");
		User u = this.load(user.getUsername());
		if(u!=null)
			throw new EmpException("用户名已存在");
		Element e = ud.getRootElement().addElement("user");
		e.addElement("username").addText(user.getUsername());
		e.addElement("password").addText(user.getPassword());
		e.addElement("nickname").addText(user.getNickname());
		write();
	}

	public void delete(String username) {
		Element root = ud.getRootElement();
		Element u = loadElementByUsername(username);
		root.remove(u);
		write();
	}

	public void update(User user) {
		Element e = loadElementByUsername(user.getUsername());
		if (e == null)
			throw new EmpException("要更新的用户不存在");
	}

	public User load(String username) {
		Element e = loadElementByUsername(username);
		if (e == null)
			return null;
		User u = new User();
		u.setNickname(e.elementText("nickname"));
		u.setPassword(e.elementText("password"));
		u.setUsername(e.elementText("username"));
		return u;
	}

	@SuppressWarnings("unchecked")
	public List<User> list() {
		List<Element> elements = ud.getRootElement().elements();
		List<User> users = new ArrayList<User>();
		User u = null;
		for (Element e : elements) {
			u = new User();
			u.setUsername(e.elementText("username"));
			u.setNickname(e.elementText("nickname"));
			u.setPassword(e.elementText("password"));
			users.add(u);
		}
		return users;
	}

	private Element loadElementByUsername(String username) {
		String path = "/users/user[username='" + username + "']";
		Element e = (Element) ud.selectSingleNode(path);
		return e;
	}

}
