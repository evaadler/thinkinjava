package thread;

public class Answer implements Runnable{
	private String name;
	private Asker asker;
	private String[] answers;
	
	public Answer(String name, Asker asker) {
		this.name = name;
		this.asker = asker;
		answers = new String[]{"我知道你不是人","没人懂我","有人来了","不会发生","相信我"};
		
	}

	@Override
	public void run() {
		while(true){
			answer();
		}
	}

	private void answer() {
		synchronized (asker) {
			if(asker.isAsk()){
				String q = asker.getQuestion();
				if(q.indexOf("狭义")>0){
					System.out.println("又犯病了");
				}else{
					int index = Asker.ran.nextInt(answers.length);
					String an = answers[index];
					System.out.println(name+": "+an+".");
				}
				try {
					asker.setAsk(false);
					Thread.sleep(2000);
					asker.notify();
					asker.wait();
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

	public Asker getAsker() {
		return asker;
	}

	public void setAsker(Asker asker) {
		this.asker = asker;
	}

	
}
