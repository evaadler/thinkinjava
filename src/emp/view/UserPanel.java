package emp.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import user.version1.User;
import emp.EmpException;
import emp.util.EmpUtil;

public class UserPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jp1, jp2;
	private JLabel jl1;
	private JTable jt1;
	private JScrollPane jsp;
	private JButton jb1, jb2, jb3;
	private UserModel um;
	private AddDialog ad;
	private UpdateDialog ud;
	private JFrame jf;

	public UserPanel(JFrame jf) {
		this.jf = jf;
		this.setLayout(new BorderLayout());
		jp1 = new JPanel();
		jp2 = new JPanel();
		jl1 = new JLabel("用户管理界面");
		jb1 = new JButton("添加用户");
		jb2 = new JButton("删除用户");
		jb3 = new JButton("修改用户");
		jb1.addActionListener(new UserManageClick());
		jb2.addActionListener(new UserManageClick());
		jb3.addActionListener(new UserManageClick());
		jp1.add(jl1);
		jp2.add(jb1);
		jp2.add(jb2);
		jp2.add(jb3);
		um = new UserModel();
		jt1 = new JTable(um);
		jsp = new JScrollPane(jt1);
		ad = new AddDialog();
		ud = new UpdateDialog();
		this.add(jsp, BorderLayout.CENTER);
		this.add(jp1, BorderLayout.NORTH);
		this.add(jp2, BorderLayout.SOUTH);

	}
	
	public void refreshData(){
		um.initData();
		jt1.updateUI();
	}

	private class UserManageClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jb1) {
				// 添加,弹出添加窗口
				ad.setVisible(true);
			} else if (e.getSource() == jb2) {
				// 删除(单条，批量)
				deleteUser();
			} else if (e.getSource() == jb3) {
				// 修改
				updateUser();
			}
		}

	}
	
	private void deleteUser(){
		int row = jt1.getSelectedRow();
		if(row<0){
			EmpUtil.showError(jp1, "必须选择要删除的用户");
		}
		System.out.println(row);
		String username = um.getRowData().get(row).get(2);
		um.getUserDao().delete(username);
		um = new UserModel();
		jt1.setModel(um);
	}
	
	private void updateUser(){
		int row = jt1.getSelectedRow();
		if(row<0){
			EmpUtil.showError(jp1, "必须选择要更新的用户");
		}
		String username = um.getRowData().get(row).get(2);
		User u = um.getUserDao().load(username);
		ud.show(u);
	}

	@SuppressWarnings(value = { "serial" })
	 class AddDialog extends JDialog {

		private JLabel jl1, jl2, jl3;
		private JButton jb1, jb2;
		private JPanel jp1, jp2, jp3, jp4;
		private JTextField jtf1, jtf2;
		private JPasswordField jpf;

		public AddDialog() {
			this.setSize(350, 180);

			// jdk1.4 后使用：组件居中
			this.setLocationRelativeTo(jf);
			this.setModal(true);
			this.setTitle("添加用戶信息");
			this.setLayout(new GridLayout(4, 1));
			jp1 = new JPanel();
			jp2 = new JPanel();
			jp3 = new JPanel();
			jp4 = new JPanel();
			jl1 = new JLabel("用  戶  名：");
			jl2 = new JLabel("用戶密碼：");
			jl3 = new JLabel("用戶昵称：");
			jb1 = new JButton("添加用戶");
			jb2 = new JButton("重置数据");
			jtf1 = new JTextField(20);
			jtf2 = new JTextField(20);
			jpf = new JPasswordField(20);
			jp1.add(jl1);
			jp1.add(jtf1);
			jp2.add(jl2);
			jp2.add(jpf);
			jp3.add(jl3);
			jp3.add(jtf2);
			jp4.add(jb1);
			jp4.add(jb2);
			jb1.addActionListener(new AddDialogClick());
			jb2.addActionListener(new AddDialogClick());
			this.add(jp1);
			this.add(jp2);
			this.add(jp3);
			this.add(jp4);

		}

	 class AddDialogClick implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == jb1) {
						// 添加用户
						String username = jtf1.getText();
						if (username == null || "".equals(username)) {
							EmpUtil.showError(jp1, "必须输入用户名");
							return;
						}
						String password = new String(jpf.getPassword());
						String nickname = jtf2.getText();
						User u = new User();
						u.setNickname(nickname);
						u.setPassword(password);
						u.setUsername(username);
						um.getUserDao().add(u);
						ad.setVisible(false);
						reset();
						um = new UserModel();
						jt1.setModel(um);
					} else if (e.getSource() == jb2) {
						// 重置数据
						reset();
					}
				} catch (EmpException e1) {System.out.println(e1.getMessage());
					EmpUtil.showError(jp1, e1.getMessage());
				}
			}

		}

		private void reset() {
			jtf1.setText("");
			jtf2.setText("");
			jpf.setText("");
		}

	}
	
	
	@SuppressWarnings(value = { "serial" })
	private class UpdateDialog extends JDialog {

		private JLabel jl1, jl2;
		private JButton jb1, jb2;
		private JPanel jp1, jp2, jp3;
		private JTextField jtf1;
		private JPasswordField jpf;
		private User user;
		
		public void show(User user){
			this.user = user;
			jtf1.setText(user.getNickname());
			this.setTitle("正在更新"+user.getUsername()+"的用户信息");
			//jl1.setText(jl1.getText()+user.getUsername());
			this.setVisible(true);
		}

		public UpdateDialog() {
			this.setSize(350, 180);
			// jdk1.4 后组件居中
			this.setLocationRelativeTo(jf);
			this.setModal(true);
			this.setTitle("更新用戶信息");
			this.setLayout(new GridLayout(4, 1));
			jp1 = new JPanel();
			jp2 = new JPanel();
			jp3 = new JPanel();
			jl1 = new JLabel("用户密码：");
			jl2 = new JLabel("用戶昵称：");
			jb1 = new JButton("更新用戶");
			jb2 = new JButton("重置数据");
			jtf1 = new JTextField(20);//具有默认文本
			jpf = new JPasswordField(20);
			jp1.add(jl1);
			jp1.add(jtf1);
			jp2.add(jl2);
			jp2.add(jpf);
			jp3.add(jb1);
			jp3.add(jb2);
			jb1.addActionListener(new UpdateDialogClick());
			jb2.addActionListener(new UpdateDialogClick());
			this.add(jp1);
			this.add(jp2);
			this.add(jp3);
		}

		private class UpdateDialogClick implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == jb1) {
						// 更新用户
						String nickname = jtf1.getText();
						String password = new String(jpf.getPassword());
						user.setPassword(password);
						user.setNickname(nickname);
						um.getUserDao().update(user);
						ud.setVisible(false);
						reset();
						um = new UserModel();
						jt1.setModel(um);
					} else if (e.getSource() == jb2) {
						// 重置数据
						reset();
					}
				} catch (EmpException e1) {System.out.println(e1.getMessage());
					EmpUtil.showError(jp1, e1.getMessage());
				}
			}

		}

		private void reset() {
			jtf1.setText(user.getNickname());
			jpf.setText(user.getPassword());
		}

	}

}
