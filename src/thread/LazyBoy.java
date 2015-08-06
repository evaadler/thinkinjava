package thread;

public class LazyBoy implements Runnable {
	private Clock c;
	
	public LazyBoy(Clock c) {
		this.c = c;
	}

	@Override
	public void run() {
		while(true){
		operate();
		}
	}

	private void operate() {
		synchronized (c) {
			if(c.isWake()){
				System.out.println("懒人说：知道啦！");
				c.setWake(false);
				c.notify();
				try {
					c.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				try {
					c.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Clock getC() {
		return c;
	}

	public void setC(Clock c) {
		this.c = c;
	}
	
	
}
