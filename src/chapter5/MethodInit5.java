package chapter5;

public class MethodInit5 {
	//! int j=g(i);//Illegal forward reference
	int i=f();
	
	int f() {
		return 11;
	}
	
	int g(int n){
		return n*10;
	}
}
