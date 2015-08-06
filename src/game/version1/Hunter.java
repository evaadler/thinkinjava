package game.version1;

/**
 * 升级条件：经验值，最大生命，等级 经验达到50升级 1：50，2：150，3：300，4：500 Monster的生命是多少就加多少经验
 * 升级：攻击+4，防御+3，maxLife增加，curLife= maxLife
 * 躲避上限60
 * 如果躲避率20，随机数在20以内躲避成功
 * @author Administrator
 * 
 */
public class Hunter {
	int life;
	int maxLife;
	String name;
	String weapon;
	boolean isLive;
	int attack;
	int defend;

	// 等级和经验值
	int exp;
	int level;
	int agile;   //躲避率
	int hideRate;

	public Hunter() {

	}

	public Hunter(String name, String weapon) {
		this.name = name;
		this.weapon = weapon;
		isLive = true;
		life = 100;
		attack = 25;
		defend = 8;
		level = 1;
		exp = 0;
		agile = 40;
		hideRate = 60;
	}

	@Override
	public String toString() {
		return "Hunter [attack=" + attack + ", defend=" + defend + ", exp="
				+ exp + ", isLive=" + isLive + ", level=" + level + ", life="
				+ life + ", maxLife=" + maxLife + ", name=" + name
				+ ", weapon=" + weapon + "]";
	}

	public void fight(Monster monster) {
		// 如果自己死亡无法战斗
		if (!isLive)
			return;
		// 如果怪物死亡退出战斗
		if (!monster.isLive)
			return;

		System.out.println("【" + name + "】开始疯狂的用" + weapon + "击打"
				+ monster.type);
		// 怪物受伤
		monster.injured(this);
	}

	public void show() {
		System.out.println(toString());
	}

	public int randomRange(int start, int end){
		return (int)(Math.random()*(end-start)+start);
	}
	
	public void injured(Monster monster) {
		if(hidden()){
			System.out.println("耶，没打着，气死你");
			return;
		}
		
		int lostLife = monster.attack - this.defend;
		int basicLostLife = 10;
		if (lostLife < 0) {
			life -= basicLostLife;
		} else {
			life -= (lostLife + basicLostLife);
		}
		System.out.println(name + ":我没事，看我的");
		show();
		// life -= 20;
		if (life <= 0) {
			dead();
			return;
		}
	}

	/**
	 * 躲避
	 * @return
	 */
	private boolean hidden() {
		//生成躲避系数（和敏捷相关）
		int sucRate = agile*hideRate/100;
		//生成随机数判断是否躲避成功
		int ran = randomRange(1,101);
		//判断是否躲避成功
		if(ran<sucRate){
			return true;
		}
		return false;
	}

	public void dead() {
		isLive = false;
		System.out.println(name + ":百年以后我还是一条好汉！");
	}

	/**
	 * 增加经验升级与否
	 * 
	 * @param monster
	 */
	public void addExp(Monster monster) {
		exp += monster.maxLife;
		int needExp = 0;
		for (int i = 0; i <= level; i++) {
			needExp += i * 50;
		}
		if (exp >= needExp) {
			upgrade();
		}
	}

	/**
	 * 升级
	 */
	private void upgrade() {
		System.out.println("~~~~~~~~~~~~~~duang~~duang~~~~`~~~~~~~~~~~~~,你升级了！");
		level++;
		attack += 4;
		defend += 3;
		maxLife = life + 20;
		life = maxLife;
		exp -= 50;
		
		show();
	}
}
