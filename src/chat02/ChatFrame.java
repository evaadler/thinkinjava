package chat02;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ChatFrame extends JFrame {
	private JTextArea jta;
	private JTextField jtf;
	private JPanel jp;
	private JButton jb;
	private JScrollPane jsp1, jsp2;
	private JList list;
	private String name;
	private static DefaultListModel mol = new DefaultListModel();;
	private Socket s;
	private BufferedReader br;
	private PrintWriter out;

	public ChatFrame(String name) {
		this.name = name;
		this.setSize(800, 600);
		this.setLocation(100, 100);
		this.setTitle("聊天:当前用户是[" + name + "]");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				out.println(Server.DISCONNECT_TOKEN);
			}
		});

		jta = new JTextArea();
		jsp1 = new JScrollPane(jta);
		jtf = new JTextField(35);
		jtf.addKeyListener(new KeyClick());
		jb = new JButton("发送");
		jb.addActionListener(new BtnClick());
		mol.addElement(name);

		//list = new JList(new String[] { "All", "San", "Si" });
		list = new JList(mol);

		// 设置了宽度之后，如果宽度不够，会使用...代替
		// list.setFixedCellWidth(100);
		jsp2 = new JScrollPane(list);
		jp = new JPanel();

		this.add(jsp1);
		jp.add(jtf);
		jp.add(jb);
		this.add(jp, BorderLayout.SOUTH);
		this.add(jsp2, BorderLayout.WEST);

		connect();
		new Thread(new WriteThread()).start();
		this.setVisible(true);
	}

	private void connect() {
		try {
			s = new Socket(Server.HOST, Server.PORT);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
			out.println(name);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ChatFrame("zasn");
	}

	private class WriteThread implements Runnable {
		// BufferedReader sbr = new BufferedReader(new
		// InputStreamReader(System.in));
		String str = null;

		@Override
		public void run() {
			try {
				while ((str = br.readLine()) != null) {
					jta.setText(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void send() {
		String word = jtf.getText();
		if (word.trim().equals("")) {
			jtf.setFocusable(true);
			return;
		}
		Object[] vals = list.getSelectedValues();
		boolean isAll = false;
		String us = Server.CHAT_FLAG_START;
		if(vals.length<=0) isAll = true;
		for (Object v : vals) {
			if (v.toString().equals("所有人")) {
				isAll = true;
				break;
			} else {
				us += v.toString();
			}
		}
		if (isAll) {
			out.println(us + "all" + Server.CHAT_FLAG_END + word);
		} else {
			out.println(us + Server.CHAT_FLAG_END + word);
		}
		String text = jta.getText() + name + ":" + word + "\n";
		jta.setText(text);
		jtf.setText("");
		out.println(text);
	}

	private class BtnClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jb) {
				send();
				Object[] objs = list.getSelectedValues();
			}
		}

	}

	private class KeyClick extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				send();
			}
		}
	}
}
