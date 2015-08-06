
/**
 * 《Agile Java》
 * 内存泄漏：应用程序持续使用越来越多的内存直到内存耗尽
 * 内存地址用十六进制表示，0x开头
 * @author Nina
 *
 */

//public 关键字可以使代码与JUnit框架协同工作
public class StudentTest extends junit.framework.TestCase{
	
	//JUnit测试框架要求把方法指定为public类型
	public void testCreate() {
		Student student = new Student("Jane Doe");
		/*
		 * 用student引用发送消息给student对象
		 * 引用student，消息getName
		 * 将返回的string对象的内存地址赋值给局部变量studentName
		 */
		String studentName = student.getName();
		assertEquals("Jane Doe", studentName);
	}
}

class Student {
	public Student(String name) {
		
	}
	
	String getName(){
		return "";
	}
}
