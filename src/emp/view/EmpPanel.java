package emp.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EmpPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel jp1, jp2;
	private JLabel jl1;
	private JTable jt;
	private JScrollPane jsp;
	private JButton jb1, jb2, jb3;
	private EmpModel em;
	private JFrame jf;
	
	public EmpPanel(JFrame jf) {
		this.jf = jf;
		this.setLayout(new BorderLayout());
		jp1 = new JPanel();
		jp2 = new JPanel();
		jl1 = new JLabel("员工管理界面");
		jb1 = new JButton("添加员工");
		jb2 = new JButton("删除员工");
		jb3 = new JButton("修改员工");
		jp1.add(jl1);
		jp2.add(jb1);jp2.add(jb2);jp2.add(jb3);
		
		em = new EmpModel();
		jt = new JTable(em);
		jsp = new JScrollPane(jt);
		this.add(jsp);
		this.add(jp1, BorderLayout.NORTH);
		this.add(jp2, BorderLayout.SOUTH);
		
	}

}
