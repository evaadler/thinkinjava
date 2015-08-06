package emp.view;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import emp.bean.Emp;
import emp.dao.DeptDao;
import emp.dao.EmpDao;

public class EmpModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private EmpDao empDao;
	private DeptDao depDao;
	private Vector<String> columnNames;
	private Vector<Vector<String>> rowData;

	public EmpModel() {
		empDao = new EmpDao();
		depDao = new DeptDao();
		columnNames = new Vector<String>();
		initData();
	}

	 void initData() {
		columnNames.add("标识");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("薪水");
		columnNames.add("所在部门");
		rowData = new Vector<Vector<String>>();

		List<Emp> emps = empDao.list();

		Vector<String> ue = null;
		for (Emp u : emps) {
			ue = new Vector<String>();
			ue.add(String.valueOf(u.getId()));
			ue.add(u.getName());
			ue.add(u.getSex());
			ue.add(String.valueOf(u.getSalary()));
			ue.add(depDao.load(u.getDepId()).getName());
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

	public Vector<Vector<String>> getRowData() {
		return rowData;
	}

	public void setRowData(Vector<Vector<String>> rowData) {
		this.rowData = rowData;
	}
	
	
}
