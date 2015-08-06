package chapter21;

class Meal {
	private final int orderNum;
	
	Meal(int orderNum){
		this.orderNum = orderNum;
	}
	
	@Override
	public String toString() {
		return "Meal " + orderNum;
	}
}

class WaitPerson implements Runnable{
	private Restaurant restaurant;
	
	WaitPerson(Restaurant r){
		restaurant = r;
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){
			 
		}
	}
	
}

public class Restaurant {

}
