package util;

public class Range {

	public static int[] range(int end) {
		int[] array = new int[end];
		for (int i = 0; i < end; i++) {
			array[i] = i;
		}
		return array;
	}

	public static int[] range(int start, int end) {
		int[] array = new int[end - start];
		for (int i = start; i < end; i++) {
			array[i - start] = i;
		}
		return array;
	}

	public static int[] range(int start, int end, int step) {
		int[] array = new int[end - start];
		for (int i = start; i < end; i++) {
			array[i - start] = i * step;
		}
		return array;
	}
}
