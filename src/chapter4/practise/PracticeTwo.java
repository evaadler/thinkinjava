package chapter4.practise;

import java.util.Random;

import static util.Print.*;

/**
 * Practice 2
 * @author Administrator
 *
 */
public class PracticeTwo {
	public static void main(String[] args) {
		Random random = new Random(47);
		int i = 25;
		while(i>0){
			int first = random.nextInt();
			int second = random.nextInt();
			System.out.print(first+" "+second+" ");
			if(first>second){
				print("大了");
			}else if(first<second){
				print("小了");
			}else{
				print("接近");
			}
			i--;
		}
	}
}
