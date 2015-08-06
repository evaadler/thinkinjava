package chapter22;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

@SuppressWarnings("serial")
class CBox extends JPanel implements Runnable{
	private int pause;
	private static Random rand = new Random();
	private Color color = new Color(0);
	
	public void paintComponent(Graphics g){
		g.setColor(color);
		Dimension s = getSize();
		g.fillRect(0, 0, s.width, s.height);
	}
	
	public CBox(int pause){
		this.pause = pause;
	}

	@Override
	public void run() {
		while(!Thread.interrupted()){
			color = new Color(rand.nextInt(0xFFFFFF));
			repaint();
			try {
				TimeUnit.MILLISECONDS.sleep(pause);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

public class ColorBoxes {
	private int grid = 12;
	private int pause = 50;
	private static ExecutorService exec = Executors.newCachedThreadPool();
}
