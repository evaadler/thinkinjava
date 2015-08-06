package game.version8;


/**
 * 版本八，生成武器系统（包含大量多技能武器，避免类爆炸使用装饰者模式）
 * @author Administrator
 *
 */
public class TestGame {
	public static void main(String[] args) {
		Weapon w = new Sword("包子");
		Hunter hunter = new Hunter("熊猫大侠",w);
		new Game(hunter).start();
	}
}
