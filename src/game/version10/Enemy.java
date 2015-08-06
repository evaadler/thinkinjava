package game.version10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class Enemy {
	private int life;
	private  boolean isLive;
	private  String type;
	private  int attack;
	private  int defend;
	private  int maxLife;
	private  int agile;
	private  int hideRate;
	private int x, y, width, height;
	
	public Enemy() {
		x = 100;
		y = 100;
		width = 30;
		height = 30;
	}
	
	public void draw(Graphics g){
		if(!isLive)return;
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
		g.drawString(this.type, x-5, y-2);
		
		//画生命
		GameUtil.drawLine(x, y, height, width, life, maxLife, g);
		
	}
	
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
		back(hunter);
		return lossLife;
	}
	
	//被击打之后，敌人退后
	private void back(Hunter hunter) {
		if(hunter.getDir()==Direction.UP){
			this.y -= hunter.getSpeed() + 3;
		} else if(hunter.getDir() == Direction.LEFT){
			this.x -= hunter.getSpeed() + 3;
		} else if(hunter.getDir() == Direction.RIGHT){
			this.x += hunter.getSpeed() + 3;
		}else if(hunter.getDir() == Direction.DOWN){
			this.y += hunter.getSpeed() + 3;
		}
	}

	public Rectangle getArea(){
		return new Rectangle(x, y, width, height);
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
