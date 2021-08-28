import java.util.Collections;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Main size");
			System.exit(1);
		}

		final int N = Integer.parseInt(args[0]);
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