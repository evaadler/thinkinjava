package game.version10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame{
	private GamePanel gp;
	private static int GAME_WIDTH  = 600;
	private static int GAME_HEIGHT = 500;
	private Hunter hunter;
	private Enemy enemy;
	
	public GameFrame() {
		hunter = new Hunter("pixiu", new GetLifeSkill(new Sword("钟楼骑士jian"),10));
		enemy = new Vampire(3);
		hunter.setLocation(10, 10, 4);
		
		this.setLocation(300, 100);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setTitle("武士大战僵尸");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		gp = new GamePanel();
		this.add(gp);
		this.addKeyListener(new MyKeyEvent());
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new GameFrame();
	}
	
	private class MyKeyEvent extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				hunter.setDir(Direction.DOWN);
				hunter.move();
			}else if(e.getKeyCode() == KeyEvent.VK_UP){
				hunter.setDir(Direction.UP);
				hunter.move();
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				hunter.setDir(Direction.RIGHT);
				hunter.move();
			}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
				hunter.setDir(Direction.LEFT);
				hunter.move();
			}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
				hunter.getWeapon().setShow(true);
			}
			
			if(checkHit(hunter, enemy)){
				hunter.injured(enemy);
			}
			
			if(checkHit(hunter.getWeapon(), enemy)){
				hunter.fight(enemy);
			}
			
			gp.repaint();
		}
		
		//是否重合判断是否打上敌人
		private boolean checkHit(Hunter hunter, Enemy enemy){
			return hunter.getArea().intersects(enemy.getArea());
		}
		
		//判断武器是否打上敌人
		private boolean checkHit(Weapon weapon, Enemy enemy){
			if(!weapon.isShow())return false;
			return weapon.getArea(hunter).intersects(enemy.getArea());
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() ==  KeyEvent.VK_SPACE){
				hunter.getWeapon().setShow(false);
			}
		}
	}
	
	
	
	private class GamePanel extends JPanel{
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
			
			hunter.draw(g);
			hunter.getWeapon().draw(hunter, g);
			enemy.draw(g);
			
			
		}
	}
}


