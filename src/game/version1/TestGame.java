package game.version1;

import innerclass.Parcel3;

public class TestGame {
	public static void main(String[] args) {
		Hunter h = new Hunter("22","ff");
		Monster m = new Monster(1);
		h.fight(m);
		h.fight(m);
		h.fight(m);
		h.fight(m);
		Monster m2 = new Monster(2);
		Monster m3 = new Monster(3);
		Monster m4 = new Monster(4);
		h.fight(m2);
		h.fight(m2);
		h.fight(m3);
		h.fight(m4);
		h.fight(m4);
		h.fight(m4);
		h.fight(m4);
		//m.kill(h);
		//m.kill(h);
		
	}
	
}

class Hunter1{
	int life;
	String name;
	String weapon;
	boolean isLive;
	
	public Hunter1(String name, String weapon) {
		this.name = name;
		this.weapon = weapon;
		isLive = true;
		life = 100;
	}
	
	@Override
	public String toString() {
		return "Hunter [isLive=" + isLive + ", life=" + life + ", name=" + name
				+ ", weapon=" + weapon + "]";
	}

	public void fight(Monster1 monster) {
		//如果自己死亡无法战斗
		if(!isLive)return;
		//如果怪物死亡退出战斗
		if(!monster.isLive)return;
		
		System.out.println("【"+name+"】开始疯狂的用"+weapon+"击打"+monster.type);
		//怪物受伤
		monster.injured();
	}
	
	public void show(){
		System.out.println(toString());
	}
	
	public void injured(){
		life -= 20;
		if(life<=0){
			dead();
			return;
		}
		System.out.println(name+":我没事，看我的");
	}
	
	public void dead(){
		isLive = false;
		System.out.println(name+":百年以后我还是一条好汉！");
	}
}

class Monster1{
	int life;
	boolean isLive;
	String type;
	
	public Monster1() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Monster [isLive=" + isLive + ", life=" + life + ", type="
				+ type + "]";
	}

	public void kill(Hunter1 hunter) {
		//判断怪物
		if(!isLive)return;
		//判断猎人
		if(!hunter.isLive)return;
		
		System.out.println(type+":敢打老子，【"+hunter.name+"】你不想混了！");
		//猎人受伤
		hunter.injured();
	}
	
	public void injured(){
		life -= 20;
		if(life<=0){
			dead();
			return;
		}
		System.out.println(type+":呃呃呃啊啊啊，老子 怒了！");
	}
	
	public void dead(){
		isLive = false;
		System.out.println(type+":没想到竟然输了！");
	}
	
	public void show(){
		System.out.println(toString());
	}
}
