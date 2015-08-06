package game.version9;

public class GetLifeSkill extends SkillDecorator {
	private Weapon weapon;
	private int getLifeRate;
	
	public GetLifeSkill(Weapon weapon, int getLifeRate){
		this.weapon = weapon;
		this.getLifeRate = getLifeRate;
		
	}

	@Override
	public String getDesc() {
		
		return "吸血的"+weapon.getName();
	}

	@Override
	public int damage(Hunter hunter, Enemy enemy) {
		int damage = weapon.damage(hunter, enemy);
		if(damage>0){
			getLife(hunter, damage);
		}
		return damage;
	}
	
	// 吸血
	public void getLife(Hunter hunter, int damage) {
	
		int addLife = damage * getLifeRate / 100;
		System.out.println("******"+weapon.getName()+"吸了"+addLife+"点血");
		if (addLife + hunter.getLife() >= hunter.getMaxLife()) {
			hunter.setLife(hunter.getMaxLife());
		} else {
			hunter.setLife(hunter.getLife()+addLife);
		}
	}

}
