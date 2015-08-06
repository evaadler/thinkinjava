package two;
/**
 * 2.11 practice 1
 * @author Administrator
 *
 */
public class InitialParameter {
	int value1;
	char value2;
	byte b1 = 12;
	byte b2 = 22;
	byte b3 = (byte)(b1+b2);

	private void printVar() {
		System.out.println("value1=" + value1 + ",value2=" + value2+".输出完毕");
	}

	public static void main(String[] args) {
		new InitialParameter().printVar();
	}
}
