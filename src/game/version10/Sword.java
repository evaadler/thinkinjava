package game.version10;

public class Sword extends Weapon{
	
	public Sword(String name) {
		super(name);
		setLength(Hunter.HUNTER_WIDHT/2+20);
	}

	@Override
	public int damage(Hunter hunter, Enemy enemy) {
		int d = hunter.injured(enemy);
		return d;
	}

}
