package game.version7;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 * 
 */
public class Game {
	private Hunter hunter;
	private List<Enemy> enemys;
	

	public Game(Hunter hunter) {
		//hunter = new Hunter("步惊云", "神器");
		this.hunter = hunter;
		enemys = new ArrayList<Enemy>();
		enemys.add(new Monster(1));
		enemys.add(new Monster(1));
		enemys.add(new Monster(2));
		enemys.add(new Monster(3));
		enemys.add(new Monster(4));
		enemys.add(new Vampire(1));
		enemys.add(new Vampire(2));
		enemys.add(new Vampire(3));
		enemys.add(new SnakeKiller());
		enemys.add(new CowMonster());
	}

	public void start() {
		while (true) {
			// 3.生成随机数,自发生成战斗
			int ran = GameUtil.randomRange(0, enemys.size());
			
			
			// 1.判断hunter 是否dead
			if (!hunter.isLive()) {
				break;
			}

			// 2.判断monster是否全军覆没
			if(enemys.size()<=0){
				break;
			}
			
			Enemy enemy = enemys.get(ran);
			hunter.fight(enemy);
			if(!enemy.isLive()){
				enemys.remove(enemy);
			}
			
			/** 让程序暂停3秒钟 **/
			try {
				System.out.println("寻找敌人... ... ... ... ...");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		end();
	}

	public void end() {
		if (!hunter.isLive()) {
			System.out.println("大侠请重新来过");
		} else {
			System.out.println("恭喜恭喜恭喜你呀！！！大侠你通关啦");
		}
	}

	public void save() {

	}

	public void load() {

	}

}
