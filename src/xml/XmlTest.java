package xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * <p>
 * Xml SaxReader 的基本操作
 * <p>
 * XPath的基本操作
 * <p>
 * xpath的基本写操作
 * 
 * @author Nina
 * 
 */
public class XmlTest {

	public void test() {
		SAXReader reader = new SAXReader();

		try {
			Document d = reader.read(new File("books.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public void test2() {
		SAXReader sr = new SAXReader();
		Document d;
		try {
			d = sr.read(XmlTest.class.getClassLoader().getResourceAsStream(
					"books.xml"));
			// 获取根元素
			Element e = d.getRootElement();
			System.out.println(e.getName());
			// 获得文本节点
			String textNode = e.elementText("books");

			// 获得root的所有子节点
			List<Element> es = e.elements();

		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 将xml文件转换为对象
	 */
	public void xmlToObj() {
		SAXReader reader = new SAXReader();
		try {
			Document d = reader.read(XmlTest.class.getClassLoader()
					.getResource("books.xml"));
			Element root = d.getRootElement();
			List<Element> es = root.elements("book");
			List<Book> books = new ArrayList<Book>();
			for (Element e : es) {
				Book b = new Book();
				b.setId(e.attributeValue("id"));// book节点属性
				b.setAuthor(e.elementText("author"));// 文本节点
				b.setTitle(e.elementText("title"));
				b.setPrice(Double.parseDouble(e.elementText("price")));
				books.add(b);
			}
			for (Book book : books) {
				System.out.println(book);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 根据相对路径查找,从当前节点查找子节点book("book")
	 * <p>
	 * 根据绝对路径查找("/books/book")
	 * <p>
	 * 遍历所有节点查找("//books")
	 * <p>
	 * 查找所有作者为Bruce Eckel的书 <blockquote>
	 * 
	 * <pre>
	 * root.selectNodes(&quot;/books/book[author='Bruce Eckel']&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * <p>
	 * 查找属性中id>=2的数("/books/book[@id>=2]")
	 * <p>
	 * books元素下第一个book节点("/books/book[0]")
	 * <p>
	 * 查找名称中包含有java的price节点("books/book[contains(title,'java')]/price")
	 * <p>
	 * 查找名称中包含有java并且价格小于50的书(books/book[contains(title,'java') and price<50])
	 */
	public void testXpath() {
		SAXReader reader = new SAXReader();
		try {
			Document d = reader.read(XmlTest.class.getClassLoader()
					.getResource("books.xml"));
			Element root = d.getRootElement();
			// 根据相对路径查找
			List<Element> element = root.selectNodes("book");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <ol>
	 * <li>创建Document节点</li>
	 * <li>为document对象添加节点</li>
	 * <li>获取xml文件</li>
	 * <li>创建用来写数据的XMLWriter</li>
	 * <li>将节点写到xml文件中</li>
	 * </ol>
	 * 
	 */
	public void testWrite2XML() {
		XMLWriter out = null;
		Document d = DocumentHelper.createDocument();
		Element root = d.addElement("users");
		// 添加子节点
		root.addElement("user").addElement("username").addText("张三");
		String path = this.getClass().getClassLoader().getResource(
				"xml/users.xml").getPath();
		// path = path.replace("bin", "src");
		try {
			out = new XMLWriter(new FileWriter(path));
			out.write(d);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	/*创建三个person
	public void testWriteUsers(Element root, List<Person> persons){
		for(Person p : persons){
			Element ep = root.addElement("person");
			ep.addAttribute("age", p.getAge()+"");
			ep.addElement("name").setText(p.getName());
			ep.addElement("sex").setText(p.getSex());
		}
	}*/

	public static void main(String[] args) {
		new XmlTest().testXpath();

	}
}
