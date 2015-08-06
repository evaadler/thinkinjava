package chat;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ChatForm extends JFrame{
	private JPanel jp1;
	private JTextArea jta;
	private JComboBox jcb;
	private JTextField jtf;
	private JButton jb1;
	private JScrollPane jsp;
	
	public ChatForm(){
		this.setTitle("聊天");
		this.setSize(600, 500);
		this.setLocation(200, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		this.add(jsp);
		jp1 = new JPanel();
		jcb = new JComboBox(new String[]{"str1","str2","str3"});
		jtf = new JTextField(20);
		jb1 = new JButton("发送");
		jp1.add(jcb); jp1.add(jtf); jp1.add(jb1);
		
		this.add(jp1, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ChatForm();
	}

}
