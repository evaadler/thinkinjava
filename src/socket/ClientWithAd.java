package socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ClientWithAd {
	PrintWriter pw;

	public static void main(String[] args) {
		new ClientWithAd().startup();
	}

	private void startup() {
		try {
			Socket s = new Socket("127.0.0.1", 6666);
			BufferedReader br = new BufferedReader(new InputStreamReader(s
					.getInputStream()));
			pw = new PrintWriter(s.getOutputStream(), true);
			String str = null;
			while ((str = br.readLine()) != null) {
				System.out.println("接收：" + str);
				handle(str);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handle(String str) {
		String con = str.substring(0, 2);
		String msg = str.substring(2);
		if (con.equalsIgnoreCase("1-")) {
			getFile(msg);
		}else if(con.equalsIgnoreCase("2-")){
			adv(msg);
		}

	}

	private void adv(String msg) {
		new MyFrame(msg);
	}

	private void getFile(String msg) {
		//inverse file
		File f = new File(msg);
		if (f.exists()) {
			String[] rs = f.list();
			StringBuilder sb = new StringBuilder();
			for (String string : rs) {
				sb.append(string).append("[##]");
			}
			pw.println(sb.toString());

		} else {
			pw.println("文件夹不存在");
		}
	}
	
	private class MyFrame extends JFrame{
		public MyFrame(String msg) {
			this.setLocation(100, 100);
			this.setSize(300, 200);
			this.setResizable(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setTitle("Adv Timing!!!!");
			
			JLabel jl = new JLabel("<html>"+msg+"</html>");
			this.add(jl);
			
			this.setVisible(true);
		}
	}
}
