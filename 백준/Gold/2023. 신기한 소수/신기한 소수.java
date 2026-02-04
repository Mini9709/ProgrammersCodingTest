import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
	
	public static boolean ifPrime(int num) {
		
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(2);
		q.offer(3);
		q.offer(5);
		q.offer(7);
		
		for (int i = 1; i < n; i++) {
			int size = q.size();
			
			for (int j = 0; j < size; j++) {
				int curr = q.poll() * 10;
				for (int k = 0; k < 10; k++) {
					int next = curr + k;
					if (ifPrime(next)) {
						q.add(next);
					}
				}
			}
		}
		
		while (!q.isEmpty()) {
			System.out.println(q.poll());
		}	
	}

}
