package game.version6;

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
	}


}
