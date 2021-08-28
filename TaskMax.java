import java.util.ArrayList;

public class TaskMax implements Runnable {

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