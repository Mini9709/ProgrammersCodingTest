import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static Queue<int[]> q;
	static int n;
	static int answer = 0;
	
	public static void recurHanoi(int start, int end, int middle, int count) {
		if (count == 1) {
			q.offer(new int[] {start, end});
			answer++;
			return;
		}
		
		recurHanoi(start, middle, end, count-1);
		recurHanoi(start, end, middle, 1);
		recurHanoi(middle, end, start, count-1);		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		q = new ArrayDeque<>();

		if (n > 20) {
			BigInteger ans = BigInteger.valueOf(2);
			System.out.println(ans.pow(n).subtract(BigInteger.ONE));
		} else {
			recurHanoi(1, 3, 2, n);
			System.out.println(answer);
			
			while(!q.isEmpty()) {
				int[] temp = q.poll();
				System.out.println(temp[0] + " " + temp[1]);
			}
		}
	}
}