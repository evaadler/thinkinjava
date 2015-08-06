package game.version10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Weapon {
	private String name;
	private int attack;
	private int width;
	private int length;
	private boolean isShow = false;

	public Weapon() {
	}

	public Weapon(String name) {
		this.name = name;
	}

	public abstract int damage(Hunter hunter, Enemy enemy);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void draw(Hunter hunter, Graphics g) {
		if (isShow) {
			int startX = hunter.getX() + Hunter.HUNTER_WIDHT / 2;
			int startY = hunter.getY() + Hunter.HUNTER_HEIGHT / 2;
			g.setColor(Color.blue);
			if (hunter.getDir() == Direction.LEFT) {
				g.drawLine(startX - this.length, startY, startX, startY);
			} else if (hunter.getDir() == Direction.RIGHT) {
				g.drawLine(startX, startY, startX + this.length, startY);
			} else if (hunter.getDir() == Direction.UP) {
				g.drawLine(startX, startY - this.length, startX, startY);
			} else if (hunter.getDir() == Direction.DOWN) {
				g.drawLine(startX, startY, startX, startY + this.length);
			}
		}
	}
	
	public Rectangle getArea(Hunter hunter){
		int startX = hunter.getX() + Hunter.HUNTER_WIDHT/2;
		int startY = hunter.getY() + Hunter.HUNTER_HEIGHT/2;
		if (hunter.getDir() == Direction.LEFT) {
			return new Rectangle(startX-this.length, startY, this.length, 2);
		} else if (hunter.getDir() == Direction.RIGHT) {
			return new Rectangle(startX, startY, this.length, 2);
		} else if (hunter.getDir() == Direction.UP) {
			return new Rectangle(startX, startY-this.length, this.length, 2);
		} else if (hunter.getDir() == Direction.DOWN) {
			return new Rectangle(startX, startY, 2, this.length);
		}
		return null;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
