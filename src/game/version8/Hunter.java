package game.version8;

/**
 * 升级条件：经验值，最大生命，等级 经验达到50升级 1：50，2：150，3：300，4：500 Monster的生命是多少就加多少经验
 * 升级：攻击+4，防御+3，maxLife增加，curLife= maxLife
 * 躲避上限60
 * 如果躲避率20，随机数在20以内躲避成功
 * @author Administrator
 * 
 */
public class Hunter {
	private int life;
	private int maxLife;
	private String name;
	private Weapon weapon;
	private boolean isLive;
	private int attack;
	private int defend;

	// 等级和经验值
	private int exp;
	private int level;
	private int agile;   //躲避率
	private int hideRate;

	public Hunter() {

	}

	public Hunter(String name, Weapon weapon) {
		this.name = name;
		this.weapon = weapon;
		isLive = true;
		life = 100;
		attack = 30;
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

	public void fight(Enemy enemy) {
		// 如果自己死亡无法战斗
		if (!isLive)
			return;
		// 如果怪物死亡退出战斗
		if (!enemy.isLive())
			return;

		System.out.println("【" + name + "】开始疯狂的用(" + weapon + ")击打"
				+ enemy.getType());
		// 怪物受伤
		//enemy.injured(this);
		weapon.damage(this, enemy);
		
		// 增加战士经验
		if(!enemy.isLive()){
			addExp(enemy);
		}
		this.show();
		enemy.show();
	}

	public void dead() {
		isLive = false;
		System.out.println(name + ":百年以后我还是一条好汉！");
	}

	public void show() {
		System.out.println(toString());
	}
	
	public int injured(Enemy enemy) {
		//躲避的方法
		if(GameUtil.isHidden(agile, hideRate)){
			System.out.println("耶，没打着，气死你");
			return 0;
		}
		
		//获取丢失的生命
		int lostLife = GameUtil.lostLife(enemy.getAttack(), this.defend);
		//① vampire.getLife(lostLife);
		life -= lostLife;
		
		System.out.println(name + ":我没事，看我的");
		show();
		// life -= 20;
		if (life <= 0) {
			dead();
			return 0;
		}
		return lostLife;
	}


	/**
	 * 增加经验升级与否
	 * 
	 * @param monster
	 */
	public void addExp(Enemy monster) {
		exp += monster.getMaxLife();
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

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefend() {
		return defend;
	}

	public void setDefend(int defend) {
		this.defend = defend;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAgile() {
		return agile;
	}

	public void setAgile(int agile) {
		this.agile = agile;
	}

	public int getHideRate() {
		return hideRate;
	}

	public void setHideRate(int hideRate) {
		this.hideRate = hideRate;
	}
	
	
}
