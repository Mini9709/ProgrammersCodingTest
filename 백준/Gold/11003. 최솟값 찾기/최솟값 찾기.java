import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int l;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		Deque<int[]> d = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			while(!d.isEmpty() && d.peekLast()[1] > num) {
				d.pollLast();
			}
			
			d.addLast(new int[] {i, num});
			
			if (i >= l) {
				while(!d.isEmpty() && d.peekFirst()[0] <= i-l) {
					d.pollFirst();
				}
			}
			
			sb.append(d.peekFirst()[1]).append(" ");
		}
		
		System.out.println(sb);
	}

}