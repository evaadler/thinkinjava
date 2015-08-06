package emp.view;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import user.version1.User;
import emp.bean.Dept;
import emp.dao.DeptDao;
import emp.dao.UserDao;

public class DepModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private DeptDao depDao;
	private Vector<String> columnNames;
	private Vector<Vector<String>> rowData;

	public DepModel() {
		depDao = new DeptDao();
		columnNames = new Vector<String>();
		initData();
	}

	 void initData() {
		columnNames.add("部门标识");
		columnNames.add("部门名称");
		columnNames.add("部门人数");
		rowData = new Vector<Vector<String>>();

		List<Dept> depts = depDao.list();

		Vector<String> ue = null;
		for (Dept u : depts) {
			ue = new Vector<String>();
			ue.add(String.valueOf(u.getId()));
			ue.add(u.getName());
			ue.add(String.valueOf(depDao.getDepEmpNums(u.getId())));
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

	public DeptDao getDeptDao() {
		return depDao;
	}

	public void setDeptDao(DeptDao deptDao) {
		this.depDao = deptDao;
	}

	public Vector<Vector<String>> getRowData() {
		return rowData;
	}

	public void setRowData(Vector<Vector<String>> rowData) {
		this.rowData = rowData;
	}
	
	
}
