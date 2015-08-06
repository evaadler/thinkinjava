package chat02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 群聊
 * 版本二：私聊
 * @author Nina
 *
 */
public class Server {
	public static final String HOST = "127.0.0.1";
	public static final int PORT = 6868;
	private boolean stop = false;
	public static final String DISCONNECT_TOKEN = "disconnect";
	public static final String TRAN_USER_FLAG = "connect:";
	public static final String CHAT_FLAG_START = "to:";
	public static final String CHAT_FLAG_END = "end:";

	private List<ServerThread> sts;
	private Map<String, ServerThread> cs;

	public static void main(String[] args) {
		new Server().startup();

	}

	public void startup() {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(PORT);
			sts = new ArrayList<ServerThread>();
			cs = new HashMap<String, ServerThread>();
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
						if (str.equalsIgnoreCase(DISCONNECT_TOKEN)) {
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
			Set<String> keys = cs.keySet();
			for (String key : keys) {
				cs.get(key).out.println(msg);
			}
		}
		
		private void sendPrivateUsers(String us, String msg){
			String[] uus = us.split(",");
			for(String u:uus){
				cs.get(u).out.println(name+":"+msg+"[私]");
			}
		}
	}
}
