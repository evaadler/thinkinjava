package thread;

/**
 * 1.生产者 消费者问题
 * 2.每隔3m闹钟叫三声，小明按铃答知道，3s之后闹钟继续叫，往复
 * @author Nina
 *
 */
public class Test {
	public static void main(String[] args) {
		/*
		Disk d = new Disk();
		Cooker co = new Cooker("大厨", d);
		Custom cu = new Custom("顾客", d);
		Thread tco = new Thread(co);
		tco.start();
		Thread tcu = new Thread(cu);
		tcu.setDaemon(true);
		tcu.start();
		*/
		/*
		Clock c = new Clock();
		LazyBoy b = new LazyBoy(c);
		Thread tc = new Thread(c);
		Thread tb = new Thread(b);
		tb.setDaemon(true);
		tc.start();
		tb.start();*/
		
		Asker asker  = new Asker("李宇春");
		Answer ans = new Answer("汪涵", asker);
		Thread ast = new Thread(asker);
		Thread ant = new Thread(ans);
		ant.setDaemon(true);
		ast.start();
		ant.start();
	}
}
