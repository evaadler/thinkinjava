package game.version7;

public class GameUtil {
	/**
	 * 区间内随机数
	 * @param start
	 * @param end
	 * @return
	 */
	public static int randomRange(int start, int end){
		return (int)(Math.random()*(end-start)+start);
	}
	
	/**
	 * 躲避
	 * @param agile 敏捷
	 * @param hideRate 躲避率
	 * @return
	 */
	public static boolean isHidden(int agile, int hideRate) {
		// 生成躲避系数（和敏捷相关）
		int sucRate = agile * hideRate / 100;
		// 生成随机数判断是否躲避成功
		int ran = GameUtil.randomRange(1, 101);
		// 判断是否躲避成功
		if (ran < sucRate) {
			return true;
		}
		return false;
	}
	
	/**
	 * 丢失生命
	 * @param attack 攻击
	 * @param defend 防御
	 * @return
	 */
	public static int lostLife(int attack, int defend) {
		int lostLife = attack - defend;
		int basicLostLife = 10;
		int life = 0;
		if (lostLife <= 0) {
			life = basicLostLife;
		} else {
			life = (lostLife + basicLostLife);
		}
		return life;
	}
}
