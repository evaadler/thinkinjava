package game.version6;


/**
 * 版本六 增加武器类
 * @author Administrator
 *
 */
public abstract class TestGame {
	public static void main(String[] args) {
		Weapon w = new Weapon();
		w.setName("包子");
		Hunter hunter = new Hunter("熊猫大侠",w);
		new Game(hunter).start();
	}
}
