package game.version10;

public class Axe extends Weapon {

	public Axe(String name) {
		super(name);
		this.setLength(Hunter.HUNTER_WIDHT / 2 + 10);
	}

	@Override
	public int damage(Hunter hunter, Enemy enemy) {
		return enemy.injured(hunter);
	}

}