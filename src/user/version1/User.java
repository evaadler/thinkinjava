package user.version1;

/**
 * POJO : Plain Old Java Object 简单原始的Java对象
 * .对于这种对象，一般不要为其增加多余的方法
 * .典型的Java Bean
 * .仅有getter setter 方法
 * @author Administrator
 *
 */
public class User {
	private String username;	//用戶名唯一
	private String nickname;
	private String password;
	private int age;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String nickname, String password, int age) {
		super();
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.age = age;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [age=" + age + ", nickname=" + nickname + ", password="
				+ password + ", username=" + username + "]";
	}
}
