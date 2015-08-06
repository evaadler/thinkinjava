package emp.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import emp.EmpException;
import emp.bean.Dept;
import emp.util.XMLUtil;

public class DeptDao {
	Document dd;
	Document de;
	public final static String DEP_XML = "deps";

	public DeptDao() {
		dd = XMLUtil.getDepDocument();
		de = XMLUtil.getEmpDocument();
	}

	// 增加
	public void add(Dept dep) {
		Element root = dd.getRootElement();
		Element de = root.addElement("dep");
		de.addElement("id").setText(String.valueOf(getMaxId() + 1));
		de.addElement("name").setText(dep.getName());
		write();
	}

	private void write() {
		XMLUtil.write2XML(dd, DEP_XML);
	}

	/**
	 * 取得最大id值，id唯一 <br>
	 * 注意：任意节点均默认有id属性，故而使用id属性时要注意
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int getMaxId() {
		String path = "/deps/dep/id";
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
	public void update(Dept dep){
		Element e = loadById(dep.getId());
		if(e==null)throw new EmpException("要修改的部门不存在");
		e.element("name").setText(dep.getName());
	}
	
	public int getDepEmpNums(int depId){
		String path = "emps/emp[deptId="+depId+"]";
		return de.selectNodes(path).size();
	}
	
	
	// 删除
	public void delete(int id){
		if(getDepEmpNums(id)>0)throw new EmpException("要删除的部门还有员工存在！");
		Element root = dd.getRootElement();
		Element de = loadById(id);
		root.remove(de);
		write();
	}
	
	@SuppressWarnings("unchecked")
	public List<Dept> list(){
		String path = "/deps/dep";
		List<Element> eles = dd.selectNodes(path);
		List<Dept> deps = new ArrayList<Dept>();
		for(Element e : eles){
			deps.add(ele2Dep(e));
		}
		return deps;
	}
	
	private Dept ele2Dep(Element e){
		Dept d = new Dept();
		d.setId(Integer.parseInt(e.elementText("id")));
		d.setName(e.elementText("name"));
		return d;
	}
	
	public Dept load(int id){
		Element e = loadById(id);
		if(e==null) return null;
		return ele2Dep(e);
	}
	
	private Element loadById(int id ){
		String path = "/deps/dep[id="+id+"]";
		return (Element)dd.selectSingleNode(path);
	}

}
