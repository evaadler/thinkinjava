package user.version1;

/**
 * 为每一个POJO提供一个管理类来完成对这个对象的管理
 * 
 * @author Nina
 * 
 */
public class UserManager {
	User[] users;
	private int index; // 当前数组数目，没有数据为-1
	private int length; // 数组长度

	/**
	 * new User("zs","张三","123",23) ,new User("ls","李四","123",23) ,new
	 * User("ww","王五","123",23) ,new User("zl","赵六","123",23) ,new
	 * User("zs","朱七","123",23)
	 * 
	 */

	public UserManager(int size) {
		users = new User[size];
		this.length = size;
	}

	public void add(User user) {
		if (load(user.getUsername()) != null) {
			System.out.println("用户已经存在!!");
			return;
		}

		if (index < length) {
			users[index] = user;
			index++;
			System.out.println("您添加【" + user.getNickname() + "】成功");
		} else {
			System.out.println("用户数已满！不能够再添加了");
			return;
		}
	}
	
	/**
	 * 指定位置添加用戶
	 * @param user
	 * @param location
	 */
	public void add(User user, int location){
		if(location>=length){
			System.out.println("我哪有这么大的地方啦，放不下啦！");
			return;
		}
		
		if(load(user.getUsername())!=null){
			System.out.println("你搞什么，你已经在里面了呀！");
		}
		
		for(int i=index; i>=location; i--){
			users[i] = users[i-1];
		}
		users[location] = user;
		index++;
	}

	/**
	 * 
	 * @param user
	 */
	public void update(User user) {
		for (int i = 0; i < length; i++) {
			if (user.getUsername().equals(users[i].getUsername())) {
				users[i] = user;
			} else {
				System.out.println("要修改的用户不存在呀！");
				return;
			}
		}
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
			System.out.println("要删除的用户不存在哪");
		}
		
		index--;
		for (int i = 0; i < index; i++) {
			if (name.equals(users[i].getUsername())) {
				for (int j = i; j < index; j++) {
					users[j] = users[j + 1];
				}
				return;
			}
		}
		
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
			return null;
		}
		for (int i = 0; i < index; i++) {
			if (username.equals(users[i].getUsername())) {
				return users[i];
			}
		}
		return null;
	}

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 */
	public void login(String username, String password) {
		if (username == null || password == null) {
			System.out.println("怎么不输入用户名和密码呀!");
			return;
		}

		if (load(username) == null) {
			System.out.println("哪里来的？没有你这个用户！");
			return;
		}

		if (password.equals("123")) {
			System.out.println("哎呦呦，您可终于来了，等您好久了!!");
		} else {
			System.out.println("您密码输错了吧？");
		}
	}

	/**
	 * 有多少元素，返回多少元素
	 * 
	 * @return
	 */
	public User[] getUsers() {
		User[] tmps = new User[index];
		for (int i = 0; i < index; i++) {
			tmps[i] = users[i];
		}
		showAll();
		return tmps;
	}
	
	public void showAll(){
		for (int i = 0; i < index; i++) {
			System.out.print(users[i].getNickname()+",");
		}
	}

}
