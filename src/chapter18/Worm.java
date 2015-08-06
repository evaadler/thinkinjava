package chapter18;

import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 * 对链接的对象生成一个worm对序列化机制进行测试
 * @author Nina
 *
 */
class Data implements Serializable{
	private int n;
	public Data(int n){
		this.n = n;
	}
	public String toString(){
		return Integer.toString(n);
	}
}

public class Worm implements Serializable{
	private static Random rand = new Random(47);
	private Data[] d = {
			new Data(rand.nextInt(10)),
			new Data(rand.nextInt(10)),
			new Data(rand.nextInt(10))
	};
	private Worm next;
	private char c;
	//Value of i == number of segment
	public Worm(int i, char x){
		System.out.println("Worm constructor: "+i);
		c = x;
		if(--i >0){
			next = new Worm(i, (char)(x+1));
		}
	}
	
	public Worm(){
		System.out.println("Default constructor");
	}
	
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append(c);
		result.append("(");
		for(Data dat : d){
			result.append(dat);
		}
		result.append(")");
		if(next != null){
			result.append(next);
		}
		return result.toString();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		Worm w = new Worm(6, 'a');
		System.out.println("w = " + w);
	}
}
