import java.util.ArrayList;

public class TaskSum implements Runnable {

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