package thread;

import java.util.Random;

public class Cooker implements Runnable {
	private String name;
	private Disk d;
	private static Random r = new Random();
	String foods[];

	public Disk getD() {
		return d;
	}

	public void setD(Disk d) {
		this.d = d;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	Cooker(String name, Disk d) {
		this.name = name;
		this.d = d;
		foods = new String[] { "大肠", "小肠", "蛋糕", "牛扒", "面条",
				"鱼香肉丝", "蔬菜沙拉", "八宝粥", "卤汁凉粉", "胡辣汤", "小龙虾" };
		
	}

	public void make() {
		synchronized (d) {
			if (d.isEmpty()) {
				int index = r.nextInt(foods.length);
				String food = foods[index];
				System.out.println(name+"-制作了-"+food);
				d.setFood(food);
				d.setEmpty(false);
				d.notify();
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
		for(int i=0; i<20; i++){
			make();
		}
	}
}
