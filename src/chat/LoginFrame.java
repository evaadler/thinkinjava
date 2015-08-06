package chat;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	private JLabel jl;
	private JButton jb;
	private JTextField jtf;

	public static void main(String[] args) {
		new LoginFrame();
	}

	private void close() {
		this.setVisible(false);
	}

	private void link() {
		String name = jtf.getText();
		if (name.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "请输入连接名称");
			return;
		}
		new ChatFrame(name);
		close();
	}

	public LoginFrame() {
		this.setLocation(100, 100);
		this.setSize(400, 100);
		this.setTitle("用户连接");
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		jl = new JLabel("输入用户名");
		jb = new JButton("连接");
		// 添加按钮事件
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				link();
			}

		});
		
		jtf = new JTextField(25);
		jtf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("fffff");
					link();
				}
			}
		});
		this.add(jl);
		this.add(jtf);
		this.add(jb);

		this.setVisible(true);
	}

}
