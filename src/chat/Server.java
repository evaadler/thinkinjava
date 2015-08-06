package chat;

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
 * 群聊
 * @author Nina
 *
 */
public class Server {
	public static final String HOST = "127.0.0.1";
	public static final int PORT = 6868;
	private boolean stop = false;
	private static final String DISCONNECT_TOKEN = "disconnect";

	private List<ServerThread> sts;

	public static void main(String[] args) {
		new Server().startup();

	}

	public void startup() {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(PORT);
			sts = new ArrayList<ServerThread>();
			while (true) {
				Socket s = ss.accept();
				ServerThread st = new ServerThread(s);
				new Thread(st).start();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class ServerThread implements Runnable {
		private Socket s;
		private BufferedReader br;
		private PrintWriter out;
		private String name;

		public ServerThread(Socket s) throws IOException {
			this.s = s;
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
			name = br.readLine();
			name += "[" + s.getInetAddress().getHostAddress() + "]";
			send(name + "连接了");
			sts.add(this);
		}

		private void close() {
			stop = true;
			out.println(DISCONNECT_TOKEN);
			sts.remove(this);
			send(name + "断开连接了");
		}

		@Override
		public void run() {
			String str = null;
			try {
				while (true) {
					if ((str = br.readLine()) != null) {
						send(str);
						if (str.equalsIgnoreCase("disconnect")) {
							close();
							break;
						}
					}
				}
			} catch (SocketException e) {
				close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void send(String msg) {
			for (ServerThread st : sts) {
				st.out.println(msg);
			}
		}
	}
}
