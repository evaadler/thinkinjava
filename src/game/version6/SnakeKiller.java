package game.version6;

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
	public String toString() {
		return "SnakeKiller [getAgile()=" + getAgile() + ", getAttack()="
				+ getAttack() + ", getDefend()=" + getDefend()
				+ ", getHideRate()=" + getHideRate() + ", getLife()="
				+ getLife() + ", getMaxLife()=" + getMaxLife() + ", getType()="
				+ getType() + ", isLive()=" + isLive() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
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

}
