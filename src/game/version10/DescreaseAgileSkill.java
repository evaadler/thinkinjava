package game.version10;

public class DescreaseAgileSkill extends SkillDecorator {
	private Weapon weapon;
	private int deAgileNum;
	
	public DescreaseAgileSkill(Weapon weapon, int deAgileNum){
		this.weapon = weapon;
		this.deAgileNum = deAgileNum;
		
	}

	@Override
	public String getDesc() {
		
		return "降低敏捷的"+weapon.getName();
	}

	@Override
	public int damage(Hunter hunter, Enemy enemy) {
		int damage = weapon.damage(hunter, enemy);
		if(damage>0){
			int td = enemy.getAgile()-deAgileNum;
			if(td<0){
				enemy.setAgile(0);
				System.out.println(enemy.getType()+"的敏捷度降低为"+enemy.getAgile());
			}else{
				enemy.setAgile(td);
				System.out.println(enemy.getType()+"的敏捷度降低为"+enemy.getAgile());
			}
		}
		return damage;
	}
	

}
