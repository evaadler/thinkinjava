package game.version4;

import game.version4.GameUtil;
import game.version4.Hunter;

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
	
	public void injured(Hunter hunter) {
		int tmpLife = getLife();
		if (GameUtil.isHidden(getAgile(), getHideRate())) {
			System.out.println("哈哈，我无敌我存在");
		} else {
			tmpLife -= GameUtil.lostLife(hunter.getAttack(), this.getDefend());
			setLife(tmpLife);
			System.out.println(getType() + ":呃呃呃啊啊啊，老子 怒了！");
			show();
			if (getLife() <= 0) {
				dead();
				hunter.addExp(this);
				return;
			}
		}

		kill(hunter);
		setLife(hunter.injured(this));
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
