import java.util.Collections;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		final int N = 10000;
		ArrayList<Integer> data = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			data.add(i + 1); // 1 ~ N
		}
		Collections.shuffle(data);

		int[] result= new int[2];
		Runnable taskSum = new TaskSum(data, result);
		Runnable taskMax = new TaskMax(data, result);
		Thread threadSum = new Thread(taskSum);
		Thread threadMax = new Thread(taskMax);
		threadSum.start();
		threadMax.start();
		try {
			threadSum.join();
			threadMax.join();
		} catch (InterruptedException e) {
			// ignore
		}
		System.out.printf("Threads completed: Sum = %d  Max = %d\n", result[0], result[1]);
	}
}

class TaskSum implements Runnable {

	private ArrayList<Integer> data;
	private int[] result;

	public TaskSum(ArrayList<Integer> data, int[] result) {
		this.data = data;
		this.result = result;
	}
	@Override
	public void run() {
		int sum = 0;
		for (var n: data)
			sum += n;
		result[0] = sum;
	}
}

class TaskMax implements Runnable {

	private ArrayList<Integer> data;
	private int[] result;

	public TaskMax(ArrayList<Integer> data, int[] result) {
		this.data = data;
		this.result = result;
	}
	@Override
	public void run() {
		int max = data.get(0);
		for (var n: data)
			if (n > max) max = n;

		result[1] =  max;
	}
}