package emp.util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class EmpUtil {
	public static void showError(Component parent, String message){
		JOptionPane.showMessageDialog(parent, message, "发现错误", JOptionPane.ERROR_MESSAGE);
	}
}
