package user.version5;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class UserUI {
	private final String SAVE_PATH = "c:/save.out";
	private ObjectOutputStream oos = null;
	private UserManager um = null;
	private BufferedReader br = null;
	
	public UserUI() {
		try{
			File f = new File(SAVE_PATH);
			oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH));
			if(!f.exists()){
				f.createNewFile();
				um = new UserManager();
			}else{
				um = load();
			}
		}catch(IOException e){
			System.out.println("发生系统错误-------"+e.getMessage());
			System.exit(-1);
		}
	}

	public void start() {

		String str = null;

		showInfo();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			while ((str = br.readLine()) != null) {
				System.out.println(str);
				sel(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("结束");
			try {
				br.close();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			//程序结束时统一关闭对象输出流
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private UserManager load(){
		ObjectInputStream ois = null;
		try {
			ois  = new ObjectInputStream(new FileInputStream(SAVE_PATH));
			UserManager um = (UserManager)ois.readObject();
			return um;
		}catch(EOFException e){
			//文件存在但是不存在加载对象(文件中没有保存对象)，则返回新对象
			return new UserManager();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(ois !=null)
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
	
	public void save(){
		try {
			
			oos.writeObject(um);
			oos.reset();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void sel(String str) throws IOException {
		if (str.equalsIgnoreCase("a") || str.equals("1")) {
			add(br);
			System.out.println("添加了");
		} else if (str.equalsIgnoreCase("u") || str.equals("2")) {
			System.out.println("删除");
			um.delete("校长");
		} else if (str.equalsIgnoreCase("u") || str.equals("3")) {
			System.out.println("修改");
			update(br);
		} else if (str.equalsIgnoreCase("q") || str.equals("4")) {
			System.out.println("查询");
			load(br);
		} else if (str.equalsIgnoreCase("l") || str.equals("5")) {
			System.out.println("列表用户");
			list();
		} else if (str.equalsIgnoreCase("login") || str.equals("6")) {
			System.out.println("登录");
			login(br);
		} else if (str.equalsIgnoreCase("e") || str.equals("7")) {
			System.out.println("退出 ，欢迎再次登录");
			System.exit(0);
		} else {
			System.out.println("请输入正确的操作");
		}
		showInfo();
	}

	// 修改用户
	private void update(BufferedReader br) throws IOException {
		System.out.println("请输入要修改的用户名：");
		String username = br.readLine();
		System.out.println("请输入要修改的密码：");
		String pwd = br.readLine();
		System.out.println("请输入要修改的昵称：");
		String nickname = br.readLine();
		System.out.println("请输入要修改的年龄：");
		int age = Integer.parseInt(br.readLine());
		
		um.update(new User(username, pwd, nickname, age));
		save();
	}

	// 查询用户
	private User load(BufferedReader br2) throws IOException {
		System.out.println("请输入查询的用户名");
		String username = br.readLine();
		User u = um.load(username);
		if (u == null) {
			System.out.println("该用户不存在！");
		}
		return um.load(username);
	}

	// 登录
	private void login(BufferedReader br) throws IOException {
		try {
			System.out.println("请输入用户名：");
			String username = br.readLine();
			System.out.println("请输入密码：");
			String password = br.readLine();
			User u = um.login(username, password);
			if (u != null) {
				System.out.println("登录成功");
			}
		} catch (UserException e) {
			System.err.println(e.getMessage());
		}
	}

	// 查看用户列表
	private void list() {
		List<User> list = um.getUsers();
		for (User u : list) {
			System.out.println(u);
		}
	}

	// 添加用户
	private void add(BufferedReader br) throws IOException {
		try {
			System.out.println("请输入用户名：");
			String username = br.readLine();
			System.out.println("请输入密码：");
			String pwd = br.readLine();
			System.out.println("请输入昵称：");
			String nickname = br.readLine();
			System.out.println("请输入年龄：");
			int age = Integer.parseInt(br.readLine());

			User user = new User();
			user.setUsername(username);
			user.setPassword(pwd);
			user.setNickname(nickname);
			user.setAge(age);
			um.add(user);
			save();
		} catch (NumberFormatException e) {
			System.out.println("duang~~,请输入正确的年龄！");
		} catch (UserException e) {
			System.err.println(e);
		}

	}


	public void showInfo() {
		System.out.println("请选择相应的操作：");
		System.out
				.println("---------------------------------------------------------------------------------------------------------------------");
		System.out
				.println("添加[A或1]--删除[D或2]--修改[U或3]--查询[Q或4]--列表[L或5]--登录[login或6]--退出系统[E或7]");
		System.out
				.println("---------------------------------------------------------------------------------------------------------------------");
	}
}
