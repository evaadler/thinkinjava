package game.version4;

import game.version4.GameUtil;
import game.version4.Hunter;

public class Monster extends Enemy{
	

	public Monster() {
		// TODO Auto-generated constructor stub
	}

	public Monster(int mt) {
		setLive(true);
		setHideRate(80);
		if (mt == 1) {
			setType("普通僵尸");
			setLife(40);
			setAttack(15);
			setDefend(8);
			setMaxLife(getLife());
			setAgile(20);
		} else if (mt == 2) {
			setType("魔法僵尸");
			setLife(35);
			setAttack(27);
			setDefend(5);
			setMaxLife(getLife());
			setAgile(35);
		} else if (mt == 3) {
			setType("钢盔僵尸");
			setLife(40);
			setAttack(10);
			setDefend(15);
			setMaxLife(getLife());
			setAgile(16);
		} else if (mt == 4) {
			setType("大僵尸");
			setLife(60);
			setAttack(30);
			setDefend(10);
			setMaxLife(getLife());
			setAgile(30);
		}
	}


	@Override
	public String toString() {
		return "Monster [getAgile()=" + getAgile() + ", getAttack()="
				+ getAttack() + ", getDefend()=" + getDefend()
				+ ", getHideRate()=" + getHideRate() + ", getLife()="
				+ getLife() + ", getMaxLife()=" + getMaxLife() + ", getType()="
				+ getType() + ", isLive()=" + isLive() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
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
	}


}
