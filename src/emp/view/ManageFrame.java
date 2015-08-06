package emp.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * 【雇员管理系统】
 * 
 * @author Nina
 * 
 */
public class ManageFrame extends JFrame {
	private JPanel jp1;
	private JMenuBar jmb;
	private JMenu jm1, jm2, jm3;
	private JMenuItem jmi1, jmi2, jmi3;
	private DepPanel dp;
	private EmpPanel ep;
	private UserPanel up;

	public ManageFrame() {
		this.setSize(500, 400);
		this.setLocation(100, 100);
		this.setTitle("雇员管理");
		this.setLayout(new BorderLayout(2, 2));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		jmb = new JMenuBar();
		jm1 = new JMenu("部门管理");
		jm2 = new JMenu("员工管理");
		jm3 = new JMenu("用户管理");

		jmi1 = new JMenuItem("部门信息管理");
		jmi2 = new JMenuItem("员工信息管理");
		jmi3 = new JMenuItem("用户信息管理");

		jm1.add(jmi1);
		jm2.add(jmi2);
		jm3.add(jmi3);
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		this.add(jmb, BorderLayout.NORTH);
		jp1 = new JPanel(new BorderLayout());
		up = new UserPanel(this);
		dp = new DepPanel(this);
		ep = new EmpPanel(this);
		this.add(jp1);

		jmi1.addActionListener(new MenuClick());
		jmi2.addActionListener(new MenuClick());
		jmi3.addActionListener(new MenuClick());

		this.setVisible(true);
	}

	private class MenuClick implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 注意要把之前的全部清除再添加，否则界面不变
			jp1.removeAll();

			if (e.getSource() == jmi1) {
				// 部门管理
				jp1.add(dp);

			} else if (e.getSource() == jmi2) {
				// 員工管理
				jp1.add(ep);

			} else if (e.getSource() == jmi3) {
				// 用戶管理
				jp1.add(up);
			}
			jp1.updateUI();
		}
	}

	// 嵌套类：测试类
	public static class Tester {
		public static void main(String[] args) {

		}
	}

}
