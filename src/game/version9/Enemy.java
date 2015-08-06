package game.version9;


public abstract class Enemy {
	private int life;
	private  boolean isLive;
	private  String type;
	private  int attack;
	private  int defend;
	private  int maxLife;
	private  int agile;
	private  int hideRate;
	
	public void dead() {
		isLive = false;
		System.out.println(type + ":没想到竟然输了！");
	}

	public void show() {
		System.out.println(toString());
	}
	
	public abstract void kill(Hunter hunter);
	
	public int injured(Hunter hunter) {
		int tmpLife = getLife();
		int lossLife = 0;
		if (GameUtil.isHidden(getAgile(), getHideRate())) {
			System.out.println("哈哈，我无敌我存在");
		} else {
			lossLife = GameUtil.lostLife(hunter.getAttack(), this.getDefend());
			tmpLife -= lossLife;
			setLife(tmpLife);
			System.out.println(getType() + ":呃呃呃啊啊啊，老子 怒了！");
			show();
			if (getLife() <= 0) {
				dead();
				hunter.addExp(this);
				return 0;
			}
		}

		kill(hunter);
		return lossLife;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefend() {
		return defend;
	}

	public void setDefend(int defend) {
		this.defend = defend;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public int getAgile() {
		return agile;
	}

	public void setAgile(int agile) {
		this.agile = agile;
	}

	public int getHideRate() {
		return hideRate;
	}

	public void setHideRate(int hideRate) {
		this.hideRate = hideRate;
	}
}
