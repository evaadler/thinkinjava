package thread;

import java.util.Random;

public class Asker implements Runnable{
	private String name;
	private String[] questions;
	private String question;
	private boolean isAsk;
    static Random ran = new Random();

	public Asker(String name) {
		this.name = name;
		questions = new String[]{"我是超人","狭义相对论","我是一个蚂蚁","曲面褶皱","我是天使媒介"};
		isAsk = false;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			ask();
		}
	}

	private void ask() {
		synchronized (this) {
			if(!isAsk){
				int index = ran.nextInt(questions.length);
				question = questions[index];
				System.out.println(name+":"+question+"?");
				try {
					Thread.sleep(2000);
					isAsk = true;
					this.notify();
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}else{
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public boolean isAsk() {
		return isAsk;
	}

	public void setAsk(boolean isAsk) {
		this.isAsk = isAsk;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	
}
