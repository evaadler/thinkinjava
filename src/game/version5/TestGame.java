package game.version5;
/**
 * 版本五，利用继承，多态特性消除重复，增加程序可扩展性
 * 增加抽象类
 * @author Administrator
 *
 */
public class TestGame {
	public static void main(String[] args) {
		Hunter hunter = new Hunter("熊猫大侠","包子");
		new Game(hunter).start();
	
		
	}



}
