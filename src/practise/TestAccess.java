package practise;
class Banana{void peel(int i){}}

public class TestAccess{
	public static void main(String[] args){
		Banana a = new Banana(),
		       b = new Banana();
		a.peel(1);
		a.peel(2);
	}
}