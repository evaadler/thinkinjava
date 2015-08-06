package game.version4;

/**
 * 
 * @author Administrator
 * 
 */
public class Game {
	private Hunter hunter;
	private Monster m1, m2, m3, m4, m5;
	private Vampire v1,v2,v3;
	private SnakeKiller sk;

	public Game(Hunter hunter) {
		//hunter = new Hunter("步惊云", "神器");
		this.hunter = hunter;
		m1 = new Monster(1);
		m2 = new Monster(1);
		m3 = new Monster(2);
		m4 = new Monster(3);
		m5 = new Monster(4);
		v1 = new Vampire(1);
		v2 = new Vampire(2);
		v3 = new Vampire(3);
		sk = new SnakeKiller();
	}

	public void start() {
		while (true) {
			

			// 3.生成随机数,自发生成战斗
			int ran = GameUtil.randomRange(1, 9);
			switch (ran) {
			case 1:
				hunter.fight(m1);
				break;
			case 2:
				hunter.fight(m2);
				break;
			case 3:
				hunter.fight(m3);
				break;
			case 4:
				hunter.fight(m4);
				break;
			case 5:
				hunter.fight(m5);
				break;
			case 6:
				hunter.fight(v1);
				break;
			case 7:
				hunter.fight(v2);
				break;
			case 8:
				hunter.fight(v3);
				break;
			case 9:
				hunter.fight(sk);
				break;
			default:
				System.out.println("没有这个僵尸，你哪造的？");
			}
			
			// 1.判断hunter 是否dead
			if (!hunter.isLive()) {
				break;
			}

			// 2.判断monster是否全军覆没
			if (!m1.isLive() && !m2.isLive() && !m3.isLive() && !m4.isLive()
					&& !m5.isLive()) {
				break;
			}
			
			/** 让程序暂停3秒钟 **/
			try {
				System.out.println("寻找敌人... ... ... ... ...");
				Thread.sleep(3000);
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
