package game.version8;



public abstract class Weapon {
	private String name;
	private int attack;
	
	public Weapon() {}
	
	public Weapon(String name){
		this.name = name;
	}
	
	public abstract int damage(Hunter hunter, Enemy enemy);
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
}
