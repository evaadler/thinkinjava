package innerclass;

/**
 * 任意的作用域内嵌入内部类
 * @author Nina
 *
 */
public class Parcel6 {
	private void internalTracking(boolean b){
		if(b){
			class TrackingSlip{
				private String id;
				TrackingSlip(String s){
					id = s;
				}
				String getSlip(){return id;}
			}
			TrackingSlip ts = new TrackingSlip("slip");
			String s = ts.getSlip();
		}
		//超出作用域不可用
		//TrackingSlip ts = new TrakingSlip("x");
	}
}
