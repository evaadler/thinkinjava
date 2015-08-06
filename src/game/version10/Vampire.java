package game.version10;

public class Vampire extends Enemy {
	private int getLifeRate;
	private int getLife;

	public Vampire() {
		// TODO Auto-generated constructor stub
	}

	public Vampire(int mt) {
		setLive(true);
		setHideRate(80);
		if (mt == 1) {
			setType("吸血蝙蝠");
			setLife(40);
			setAttack(15);
			setDefend(8);
			setMaxLife(getLife());
			setAgile(20);
			getLifeRate = 10;
		} else if (mt == 2) {
			setType("吸血鬼");
			setLife(35);
			setAttack(27);
			setDefend(5);
			setMaxLife(getLife());
			setAgile(35);
			getLifeRate = 20;
		} else if (mt == 3) {
			setType("吸血女王");
			setLife(40);
			setAttack(10);
			setDefend(15);
			setMaxLife(getLife());
			setAgile(16);
			getLifeRate = 40;
		}
	}



	@Override
	public String toString() {
		return "Vampire [getLife=" + getLife + ", getLifeRate=" + getLifeRate
				+ "]";
	}

	// 吸血
	public void getLife(int damage) {
		int tmpLife = getLife();
		int addLife = damage * getLifeRate / 100;
		System.out.println("******"+getType()+"吸了"+addLife+"点血");
		if (addLife + getLife() >= getMaxLife()) {
			setLife(getMaxLife());
		} else {
			tmpLife += addLife;
			setLife(tmpLife);
		}
	}
	
	public void kill(Hunter hunter) {
		// 判断怪物
		if (!isLive())
			return;
		// 判断猎人
		if (!hunter.isLive())
			return;

		System.out.println(getType() + ":敢打老子，【" + hunter.getName() + "】你不想混了！");
		// 猎人受伤
		//②
		int lostLife = hunter.injured(this);
		getLife(lostLife);
		show();
	}

	public int getGetLifeRate() {
		return getLifeRate;
	}

	public void setGetLifeRate(int getLifeRate) {
		this.getLifeRate = getLifeRate;
	}

	public int getGetLife() {
		return getLife;
	}

	public void setGetLife(int getLife) {
		this.getLife = getLife;
	}


	
}
