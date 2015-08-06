package game.version8;

public class CowMonster extends Enemy {
	
	public CowMonster() {
		this.setType("火云邪神");
		this.setMaxLife(55);
		this.setLife(this.getMaxLife());
		this.setAttack(13);
		this.setDefend(5);
		this.setHideRate(55);
		this.setLive(true);
		this.setAgile(40);
	}

	@Override
	public String toString() {
		return "CowMonster [getAgile()=" + getAgile() + ", getAttack()="
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
