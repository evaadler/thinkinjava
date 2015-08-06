package game.version7;


/**
 * 版本七，使用List存放Enemy,不用switch case 而随机发生战斗
 * @author Administrator
 *
 */
public abstract class TestGame {
	public static void main(String[] args) {
		Hunter hunter = new Hunter("熊猫大侠","包子");
		new Game(hunter).start();
	}
}
