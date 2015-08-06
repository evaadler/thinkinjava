package game.version1;

public class Monster {
	int life;
	boolean isLive;
	String type;
	int attack;
	int defend;
	int maxLife;
	int agile;
	int hideRate;

	public Monster() {
		// TODO Auto-generated constructor stub
	}

	public Monster(int mt) {
		isLive = true;
		hideRate = 80;
		if (mt == 1) {
			type = "普通僵尸";
			life = 40;
			attack = 15;
			defend = 8;
			maxLife = life;
			agile = 20;
		} else if (mt == 2) {
			type = "魔法僵尸";
			life = 35;
			attack = 27;
			defend = 5;
			maxLife = life;
			agile = 35;
		} else if (mt == 3) {
			type = "钢盔僵尸";
			life = 40;
			attack = 10;
			defend = 15;
			maxLife = life;
			agile = 16;
		} else if (mt == 4) {
			type = "大僵尸";
			life = 60;
			attack = 30;
			defend = 10;
			maxLife = life;
			agile = 30;
		}
	}

	@Override
	public String toString() {
		return "Monster [isLive=" + isLive + ", life=" + life + ", type="
				+ type + ", attack=" + attack + ", defend=" + defend + "]";
	}

	public void kill(Hunter hunter) {
		// 判断怪物
		if (!isLive)
			return;
		// 判断猎人
		if (!hunter.isLive)
			return;

		System.out.println(type + ":敢打老子，【" + hunter.name + "】你不想混了！");
		// 猎人受伤
		hunter.injured(this);
	}

	public void injured(Hunter hunter) {
		if (hidden()) {
			System.out.println("哈哈，我无敌我存在");
		} else {
			int lostLife = hunter.attack - this.defend;
			int lostBasicLife = 7;
			if (lostLife < 0) {
				life -= lostBasicLife;
			} else {
				life -= (lostLife + lostBasicLife);
			}
			System.out.println(type + ":呃呃呃啊啊啊，老子 怒了！");
			show();
			if (life <= 0) {
				dead();
				hunter.addExp(this);
				return;
			}
		}

		kill(hunter);
	}

	public void dead() {
		isLive = false;
		System.out.println(type + ":没想到竟然输了！");
	}

	public void show() {
		System.out.println(toString());
	}

	/**
	 * 躲避
	 * 
	 * @return
	 */
	private boolean hidden() {
		// 生成躲避系数（和敏捷相关）
		int sucRate = agile * hideRate / 100;
		// 生成随机数判断是否躲避成功
		int ran = randomRange(1, 101);
		// 判断是否躲避成功
		if (ran < sucRate) {
			return true;
		}
		return false;
	}

	public int randomRange(int start, int end) {
		return (int) (Math.random() * (end - start) + start);
	}
}
