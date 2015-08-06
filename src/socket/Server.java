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
 * netstat -na 查看占用端口
 * 
 * @author Nina
 * 
 */
public class Server {
	List<ServerThread> clients = new ArrayList<ServerThread>();

	public static void main(String[] args) {
		new Server().startup();
	}

	private void startup() {
		ServerSocket ss = null;
		Socket s = null;
		
		try {
			ss = new ServerSocket(5858);
			while (true) {
				s = ss.accept();
				ServerThread st = new ServerThread(s);
				
				new Thread(st).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	class ServerThread implements Runnable {
		Socket s;
		BufferedReader br;
		PrintWriter pw;
		String name;
		boolean flag = true;

		public ServerThread(Socket s) throws IOException {
			this.s = s;
			
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream(), true);
			name = s.getInetAddress().getHostAddress() + ":" + s.getPort();
			System.out.println(name + "已连接");
			clients.add(this);
			send(name+"上线了");
		}
		
		private void send(String str){
			for(ServerThread ss : clients){
				//System.out.println(name + ":" + str);
				ss.pw.println("Receive:"+str);
			}
		}

		private void receive() throws IOException {
			String str = null;
			while ((str = br.readLine()) != null) {
				
				if (str.equalsIgnoreCase("quit")) {
					// 关闭并释放资源
					stop();
					pw.println("disconnect");
					break;
				}
				System.out.println(name + ":" + str);
				send(str);
				//pw.println("Receive:" + str);
			}
		}

		private void stop() {
			flag = false;
			clients.remove(this);
		}

		@Override
		public void run() {
			while (true) {
				if (!flag)
					break;
				try {
					receive();
				} catch (SocketException e) {
					System.out.println(s.getInetAddress().getHostAddress()
							+ s.getLocalPort() + "已经断开");
					stop();
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
		}

	}
}
