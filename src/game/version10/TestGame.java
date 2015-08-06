package game.version10;


/**
 * 版本八，生成武器系统（包含大量多技能武器，避免类爆炸使用装饰者模式）
 * 版本九，使用装饰器模式
 * 版本十，增加可视化界面
 * @author Administrator
 *
 */
public class TestGame {
	public static void main(String[] args) {
		//Weapon w = new Sword("包子");
		//Hunter hunter = new Hunter("熊猫大侠",w);
		//new Game(hunter).start();
		
		//Hunter hunter = new Hunter("周杰伦", new GetLifeSkill(new Axe("骑士"),54));
		//Hunter hunter = new Hunter("周杰伦", new DescreaseAgileSkill(new Axe("骑士"),54));
		Hunter hunter = new Hunter("周杰伦", new GetLifeSkill(new DescreaseAgileSkill(new Axe("骑士"),54),100));
		new Game(hunter).start();
	}
}
