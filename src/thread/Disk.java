package thread;

public class Disk {
	private String food;
	private boolean empty = true;

	public String getFood() {
		return food;
	}

	public void setFood(String name) {
		this.food = name;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

}
