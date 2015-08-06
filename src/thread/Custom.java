package thread;

/**
 * 顾客
 * @author Nina
 *
 */
public class Custom implements Runnable{
	private String name;
	private Disk d;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Disk getD() {
		return d;
	}

	public void setD(Disk d) {
		this.d = d;
	}
	
	public Custom(String name, Disk d){
		this.name = name;
		this.d = d;
	}
	
	public void eat(){
		synchronized (d) {
			if(!d.isEmpty()){
				String food = d.getFood();
				System.out.println(name+"|正在享受|"+food);
				d.notify();
				d.setEmpty(true);
				try {
					d.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}else{
				try {
					d.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run() {
		while(true){
			eat();
		}
	}
}
