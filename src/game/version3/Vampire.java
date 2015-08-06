package game.version3;

public class Vampire {
	int life;
	boolean isLive;
	String type;
	int attack;
	int defend;
	int maxLife;
	int agile;
	int hideRate;
	int getLife;
	int getLifeRate;

	public Vampire() {
		// TODO Auto-generated constructor stub
	}

	public Vampire(int mt) {
		isLive = true;
		hideRate = 80;
		if (mt == 1) {
			type = "吸血蝙蝠";
			life = 40;
			attack = 15;
			defend = 8;
			maxLife = life;
			agile = 20;
			getLifeRate = 10;
		} else if (mt == 2) {
			type = "吸血鬼";
			life = 35;
			attack = 27;
			defend = 5;
			maxLife = life;
			agile = 35;
			getLifeRate = 20;
		} else if (mt == 3) {
			type = "吸血女王";
			life = 40;
			attack = 10;
			defend = 15;
			maxLife = life;
			agile = 16;
			getLifeRate = 40;
		}
	}

	@Override
	public String toString() {
		return "Vampire [agile=" + agile + ", attack=" + attack + ", defend="
				+ defend + ", getLife=" + getLife + ", getLifeRate="
				+ getLifeRate + ", hideRate=" + hideRate + ", isLive=" + isLive
				+ ", life=" + life + ", maxLife=" + maxLife + ", type=" + type
				+ "]";
	}

	// 吸血
	public void getLife(int damage) {
		int addLife = damage * getLifeRate / 100;
		System.out.println("******"+type+"吸了"+addLife+"点血");
		if (addLife + life >= maxLife) {
			life = maxLife;
		} else {
			life += addLife;
		}
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
		//②
		int retVal = hunter.injured(this);
		getLife(retVal);
	}

	public void injured(Hunter hunter) {
		if (GameUtil.isHidden(agile, hideRate)) {
			System.out.println("哈哈，我无敌我存在");
		} else {
			life -= GameUtil.lostLife(hunter.attack, this.defend);
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

}
