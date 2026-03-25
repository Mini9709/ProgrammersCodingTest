import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	static int n;
	static int prevSize;
	static int nextSize;
	static PriorityQueue<Integer> prev;
	static PriorityQueue<Integer> next;
	
	public static int searchMiddle(int num) {
		if (prevSize == 0) {
			prev.add(num);
			prevSize++;
		} else {
			int temp = prev.peek();
			
			if (num < temp) {
				prev.add(num);
				prevSize++;
			} else {
				next.add(num);
				nextSize++;
			}
		}
		
		if (nextSize > prevSize) {
			prev.add(next.poll());
			prevSize++;
			nextSize--;
		}
		
		if (nextSize < prevSize - 1) {
			next.add(prev.poll());
			prevSize--;
			nextSize++;
		}
		
		return prev.peek();
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		prev = new PriorityQueue<>((o1, o2) -> Integer.compare(o2,  o1));
		next = new PriorityQueue<>((o1, o2) -> Integer.compare(o1,  o2));
		
		prevSize = 0;
		nextSize = 0;
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			int res = searchMiddle(num);
			sb.append(res).append('\n');
		}
		System.out.println(sb);
	}

}