package emp.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DepPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JFrame jf;
	private JPanel jp1, jp2;
	private JLabel jl1;
	private JTable jt1;
	private JScrollPane jsp;
	private JButton jb1, jb2, jb3;
	private DepModel dm;
	
	public DepPanel(JFrame frame) {
		jf = frame;
		this.setLayout(new BorderLayout());
		jp1 = new JPanel();
		jp2 = new JPanel();
		jl1 = new JLabel("部门管理界面");
		jb1 = new JButton("添加部门");
		jb2 = new JButton("删除部门");
		jb3 = new JButton("修改部门");
		jp1.add(jl1);
		jp2.add(jb1);jp2.add(jb2);jp2.add(jb3);
		jb1.addActionListener(new DepClick());
		jb2.addActionListener(new DepClick());
		jb3.addActionListener(new DepClick());
		this.add(jp1, BorderLayout.NORTH);
		this.add(jp2, BorderLayout.SOUTH);
		
		dm = new DepModel();
		jt1 = new JTable(dm);
		jsp = new JScrollPane(jt1);
		
		this.add(jsp, BorderLayout.CENTER);
		
		
	}
	
	private class DepClick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==jb1){
				//add
			}else if(e.getSource()==jb2){
				//delete
			}else if(e.getSource()==jb3){
				//update
			}
		}
	}
	
	private class AddDialog extends JDialog{
		private JPanel jp;
		private JLabel jl;
		private JTextField jtf;
		private JButton jb;
		
		public AddDialog() {
			this.setSize(200, 100);
			this.setTitle("添加");
			jf.add(this);
		}
	}

}
