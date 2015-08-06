package innerclass;

class Parcel4 {
	private class PContents implements Contents{
		private int i= 11;
		public int value(){return i;}
	}
	
	 public class PDestination implements Destination{
		private String label;
		private PDestination(String whereTo){
			
		}
		@Override
		public String readLabel() {
			return null;
		}
		
		public Destination destination(String s){
			return new PDestination(s);
		}
		
		public Contents contents(){
			return new PContents();
		}
	}
	
	public static void main(String[] args) {
		Parcel4 p = new Parcel4();
		///Contents c = p.contents();
		
	}
	
}
