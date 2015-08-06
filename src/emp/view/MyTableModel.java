package emp.view;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel{
	Vector<String> columnNames;
	Vector<Vector<String>> rowData;
	
	public MyTableModel() {
		columnNames = new Vector<String>();
		initData();
	}
	
	private void initData() {
		columnNames.add("用户名");
		columnNames.add("用户密码");
		columnNames.add("用户昵称");
		rowData = new Vector<Vector<String>>();
		Vector<String> ue = new Vector<String>();
		ue.add("tangseng");
		ue.add("123");
		ue.add("唐僧");
		rowData.add(ue);
	}

	@Override
	public int getColumnCount() {
		//列数
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
	
	

}
