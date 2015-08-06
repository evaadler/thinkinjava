package game.version4;

public class Enemy {
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
		hunter.injured(this);
		//getLife(retVal);
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
