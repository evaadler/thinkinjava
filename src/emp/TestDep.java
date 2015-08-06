package emp;

import java.util.List;

import org.dom4j.Document;

import emp.bean.Dept;
import emp.dao.DeptDao;
import emp.util.XMLUtil;

public class TestDep {
	private static DeptDao dd = new DeptDao();

	public static void main(String[] args) {
		testAdd();
	}

	public static void testSingleton() {
		Document d1 = XMLUtil.getUserDocument();
		Document d2 = XMLUtil.getUserDocument();
		System.out.println(d1 == d2);
	}

	public static void testAdd() {
		Dept d = new Dept();
		d.setName("part");
		dd.add(d);
	}

	public static void testLoad() {
		Dept d = dd.load(3);
		System.out.println(d);

	}

	public static void testDelete() {
		dd.delete(1);
	}

	public static void testUpdate() {
		Dept d = new Dept();
	}

	public static void testList() {
		List<Dept> ds = dd.list();
		for(Dept d : ds){
			System.out.println(d);
		}
	}

}
