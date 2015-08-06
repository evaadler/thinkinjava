package game.version9;

public class Axe extends Weapon{
	
	public Axe(String name){
		super(name);
	}

	@Override
	public int damage(Hunter hunter, Enemy enemy) {
		return enemy.injured(hunter);
	}
	
}