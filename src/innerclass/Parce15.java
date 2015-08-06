package innerclass;


/**
 * 在方法的作用域内定义内部类
 * 局部内部类
 * @author Nina
 *
 */
public class Parce15 {
	public Destination destination(String s){
		
		class PDestination implements Destination{
			private String label;
			private PDestination(String whereTo){
				label = whereTo;
			}
			@Override
			public String readLabel() {
				return label;
			}
			
		}
		return new PDestination(s);
		
	}
	
	public static void main(String[] args) {
		Parce15 p = new Parce15();
		Destination d = p.destination("Tasmania");
	}
}
