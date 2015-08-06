package game.version8;

public class Sword extends Weapon{
	
	public Sword(String name) {
		super(name);
	}

	@Override
	public int damage(Hunter hunter, Enemy enemy) {
		int d = hunter.injured(enemy);
		return d;
	}

}
