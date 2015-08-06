class Homer{
	char doh(char c){
		System.out.println("doh(char)");
		return 'd';
	}
	
	float doh(float f){
		System.out.println("doh(float)");
		return 1.0f;
	}
}

class Milhouse{}

public class TestClass extends Homer{
	
	
	private int m;
	

	/*
	 * 加上注解@override，则必须覆盖父类方法
	@Override
	void doh(Milhouse m){
		System.out.println("");
	}
	*/


	public int inc() {
		return m + 1;
	}
}
