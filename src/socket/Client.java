package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private boolean flag = true;
	BufferedReader br;

	public static void main(String[] args) {
		new Client().startup();
	}

	private void startup() {
		Socket s = null;

		try {
			s = new Socket("127.0.0.1", 5858);
			// printWriter带有缓存，必须刷新
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw.println("Teacher Zhang");
			BufferedReader sbr = new BufferedReader(new InputStreamReader(
					System.in));
			
			
			new Thread(new ClientThread()).start();
			
			String str = null;
			while ((flag)&&(str = sbr.readLine()) != null) {
				
				pw.println(str);
				/*
				 * String rs = br.readLine();
				 * if(rs.equalsIgnoreCase("disconnect")){ break; }
				 * System.out.println(rs);
				 */
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	private void receive() {
		try {
			String rs = br.readLine();
			if (rs.equalsIgnoreCase("disconnect")) {
				flag = false;
				System.out.println("点击回车退出");
			}
			System.out.println(rs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class ClientThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				if (!flag)
					break;
				receive();
			}
		}

	}
}
