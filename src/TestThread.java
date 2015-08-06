
public class TestThread {
	public static void main(String[] args) {
		new TestThread().runThread();
	}
	
	public void runThread(){
		Thread01 t1 = new Thread01();
		t1.start();
		
		new Thread(new Thread02()).start();
		
	}
	
	//定义Thread方法一
	class Thread01 extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
		}
	}
	
	//定义Thread方法二
	class Thread02 implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
