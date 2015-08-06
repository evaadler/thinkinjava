package thread;

public class Clock implements Runnable{
	private boolean wake;
	
	public Clock() {
		wake = false;
	}

	@Override
	public void run() {
		for(int i=0; i<10; i++)
		alarm();
	}

	private synchronized void alarm() {
		if(!wake){
			for(int i=0; i<3; i++){
				System.out.println("起床啦！");
			}
			wake = true;
			this.notify();
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isWake() {
		return wake;
	}

	public void setWake(boolean weak) {
		this.wake = weak;
	}
	
	
}
