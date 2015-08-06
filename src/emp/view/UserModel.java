package emp.view;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import user.version1.User;
import emp.dao.UserDao;

public class UserModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private Vector<String> columnNames;
	private Vector<Vector<String>> rowData;

	public UserModel() {
		userDao = new UserDao();
		columnNames = new Vector<String>();
		initData();
	}

	 void initData() {
		columnNames.add("用户昵称");
		columnNames.add("用户名");
		columnNames.add("用户密码");
		rowData = new Vector<Vector<String>>();

		List<User> users = userDao.list();

		Vector<String> ue = null;
		for (User u : users) {
			ue = new Vector<String>();
			ue.add(u.getNickname());
			ue.add(u.getUsername());
			ue.add(u.getPassword());
			rowData.add(ue);
		}

	}
	
	@Override
	public int getColumnCount() {
		// 列数
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		// 行数
		return rowData.size();
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		// 具体到谋一单元的值
		return rowData.get(rowIndex).get(columnIndex);
	}

	/**
	 * Return a default name for a column
	 */
	@Override
	public String getColumnName(int column) {
		// 避免表头仅显示A，B，C... ...
		return columnNames.get(column);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Vector<Vector<String>> getRowData() {
		return rowData;
	}

	public void setRowData(Vector<Vector<String>> rowData) {
		this.rowData = rowData;
	}
	
	
}
