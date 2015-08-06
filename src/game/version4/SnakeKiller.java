package game.version4;

public class SnakeKiller extends Enemy {

	public SnakeKiller() {
		this.setType("代号47");
		this.setMaxLife(33);
		this.setLife(this.getMaxLife());
		this.setAttack(33);
		this.setDefend(10);
		this.setHideRate(50);
		this.setLive(true);
		this.setAgile(34);
	}

	@Override
	public void kill(Hunter hunter) {
		if(!this.isLive()){
			return;
		}
		if(!hunter.isLive()){
			return;
		}
		hunter.injured(this);
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
