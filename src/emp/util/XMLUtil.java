package emp.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 单例模式
 * 
 * @author Nina
 *
 */
public class XMLUtil {
	private static Document userDocument;
	private static Document depDocument;
	private static Document empDocument;

	/**
	 * 使用单例模式初始化Document
	 * @return
	 */
	public static Document getUserDocument() {
		if(userDocument!=null) return userDocument;
		
		try {
			SAXReader reader = new SAXReader();
			userDocument = reader.read(XMLUtil.class.getClassLoader().getResource("emp/users.xml"));
			//FIXME xml文件读取错误？
			return userDocument;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 读取depDocument
	 * @return
	 */
	public static Document getDepDocument() {
		if(depDocument!=null) return depDocument;
		
		try {
			SAXReader reader = new SAXReader();
			depDocument = reader.read(XMLUtil.class.getClassLoader().getResource("emp/deps.xml"));
			//FIXME xml文件读取错误？
			return depDocument;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Document getEmpDocument() {
		if(empDocument!=null) return empDocument;
		
		try {
			SAXReader reader = new SAXReader();
			empDocument = reader.read(XMLUtil.class.getClassLoader().getResource("emp/emps.xml"));
			//FIXME xml文件读取错误？
			return empDocument;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void write2XML(Document d, String name){
		XMLWriter out = null;
		String path = XMLUtil.class.getClassLoader().getResource("emp/"+name+".xml").getPath();
		//现在可能存在的问题是：xml文件没有同步更新，但是在实际使用环境中，不需要替换path,即不存在这样的问题
		path = path.replace("bin", "src");
		try {
			out = new XMLWriter(new FileWriter(path));
			out.write(d);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * 取得最大id值，id唯一 <br>
	 * 注意：任意节点均默认有id属性，故而使用id属性时要注意
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static int getMaxId(Document d, String path) {
		// 一组id列表
		List<Element> eids = d.selectNodes(path);
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
}
