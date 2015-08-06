package emp.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import emp.EmpException;
import emp.bean.Dept;
import emp.bean.Emp;
import emp.util.XMLUtil;

public class EmpDao {
	Document dd;
	Document de;
	DeptDao deptDao = new DeptDao();
	public final static String EMP_XML = "emps";

	public EmpDao() { 
		dd = XMLUtil.getEmpDocument();
		de = XMLUtil.getDepDocument();
	}

	// 增加
	public void add(Emp emp) {
		Element root = dd.getRootElement();
		Element de = root.addElement("emps");
		de.addElement("id").setText(String.valueOf(getMaxId() + 1));
		de.addElement("name").setText(emp.getName());
		write();
	}

	private void write() {
		XMLUtil.write2XML(dd, EMP_XML);
	}

	/**
	 * 取得最大id值，id唯一 <br>
	 * 注意：任意节点均默认有id属性，故而使用id属性时要注意
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int getMaxId() {
		String path = "/emps/emp/id";
		// 一组id列表
		List<Element> eids = dd.selectNodes(path);
		System.out.println(eids.size());
		if (eids == null || eids.size() == 0)
			return 0;
		List<Integer> ids = new ArrayList<Integer>();
		for (Element e : eids) {
			if (e.getTextTrim() == null || "".equals(e.getTextTrim()))
				return 0;
			ids.add(Integer.parseInt(e.getText()));
		}
		if (ids.size() <= 0)
			return 0;
		Collections.sort(ids);
		return ids.get(eids.size() - 1);
	}

	// 修改
	public void update(Emp emp, int depId){
		//检查部门是否存在，不存在就不能更新
		Dept d = deptDao.load(depId);
		if(d==null) throw new EmpException("要更新的员工部门不存在！");
		Element e = loadById(emp.getId());
		e.element("name").setText(emp.getName());
		e.element("sex").setText(emp.getSex());
		e.element("salary").setText(String.valueOf(emp.getSalary()));
		e.element("depId").setText(String.valueOf(depId));
		write();
	}
	
	
	// 删除
	public void delete(int id){
		Element root = dd.getRootElement();
		Element de = loadById(id);
		root.remove(de);
		write();
	}
	
	@SuppressWarnings("unchecked")
	public List<Emp> list(){
		String path = "/emps/emp";
		List<Element> eles = dd.selectNodes(path);
		List<Emp> emps = new ArrayList<Emp>();
		for(Element e : eles){
			emps.add(ele2Emp(e));
		}
		return emps;
	}
	
	private Emp ele2Emp(Element e){
		if(e==null) return null;
		Emp d = new Emp();
		d.setId(Integer.parseInt(e.elementText("id")));
		d.setName(e.elementText("name"));
		d.setSalary(Double.parseDouble(e.elementText("salary")));
		d.setSex(e.elementText("sex"));
		d.setDepId(Integer.parseInt(e.elementText("deptId")));
		return d;
	}
	
	public Emp load(int id){
		Element e = loadById(id);
		if(e==null) return null;
		return ele2Emp(e);
	}
	
	private Element loadById(int id ){
		String path = "/emps/emp[id="+id+"]";
		return (Element)dd.selectSingleNode(path);
	}

}
