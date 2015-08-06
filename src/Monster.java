public class Monster {
	int life;
	boolean isLive;
	String type;
	int attack;
	int defend;
	int maxLife;

	public Monster() {
		// TODO Auto-generated constructor stub
	}

	public Monster(int mt) {
		isLive = true;
		if (mt == 1) {
			type = "普通僵尸";
			life = 40;
			attack = 15;
			defend = 8;
			maxLife = life;
		} else if (mt == 2) {
			type = "魔法僵尸";
			life = 35;
			attack = 27;
			defend = 5;
			maxLife = life;
		} else if (mt == 3) {
			type = "钢盔僵尸";
			life = 40;
			attack = 10;
			defend = 15;
			maxLife = life;
		} else if (mt == 4) {
			type = "大僵尸";
			life = 60;
			attack = 30;
			defend = 10;
			maxLife = life;
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
		
		kill(hunter);
	}

	public void dead() {
		isLive = false;
		System.out.println(type + ":没想到竟然输了！");
	}

	public void show() {
		System.out.println(toString());
	}
}
