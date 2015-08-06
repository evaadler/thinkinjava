package innerclass;

/**
 * 用外部类的对象创建内部类的对象
 * @author Nina
 *
 */
public class Parcel3 {

	class Contents{
		private int i = 11;
		public int value(){return i;}
	}
	
	class Destination{
		private String label;
		Destination(String whereTo) {
			label = whereTo;
		}
		String readLable(){return label;}
	}
	
	public static void main(String[] args) {
		Parcel3 p = new Parcel3();
		Parcel3.Contents c = p.new Contents();
		Parcel3.Destination d = p.new Destination("Tasmania");
	}
}

class TestClass{}
