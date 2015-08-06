package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务器广播，客户端只负责接收
 * 
 * @author Nina
 * 
 */
public class ServerWithAd {
	private List<ServerThread> clients = new ArrayList<ServerThread>();

	public static void main(String[] args) {
		new ServerWithAd().startup();
	}

	private void startup() {
		try {
			ServerSocket ss = new ServerSocket(6666);
			while (true) {
				Socket s = ss.accept();
				new Thread(new ServerThread(s)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class ServerThread implements Runnable {
		BufferedReader br;
		PrintWriter pw;
		String name;
		boolean flag = true;

		public ServerThread(Socket s) {
			try {
				br = new BufferedReader(new InputStreamReader(s
						.getInputStream()));
				pw = new PrintWriter(s.getOutputStream(),true);
				name = s.getLocalAddress().getHostAddress() + ":" + s.getPort();
				clients.add(this);
				System.out.println(name + "上线了");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void send(String str) {
			for (ServerThread st : clients) {
				st.pw.println(str);
			}
		}

		@Override
		public void run() {
			BufferedReader sbr = null;
			String str = null;
			send("hello everyone!");
			while (true) {
				if (!flag)
					break;
				sbr = new BufferedReader(new InputStreamReader(System.in));
			
				try {
					send(sbr.readLine());
					if((str = br.readLine())!=null){
						System.out.println(str);
					}
				} catch (SocketException e) {
					System.out.println(name+"已经断开");
					send(name + "下线了");
					flag = false;
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			
				/*
				try {
					str = br.readLine();
					System.out.println(str);
				} catch (SocketException e) {
					System.out.println(name+"已经断开");
					send(name + "下线了");
				} catch (IOException e) {
					e.printStackTrace();
				} */
			}
		}

	}
}
