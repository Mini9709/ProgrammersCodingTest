import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int[] a;
	static int[] b;
	static boolean[] isUsed;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		a = new int[n+1];
		b = new int[n+1];
		isUsed = new boolean[n+1];
		Queue<Integer> q = new ArrayDeque<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			b[i] = Integer.parseInt(st.nextToken());
			
			if (b[i] == 1) {
				q.add(i);
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			a[idx] = num;
			isUsed[num] = true;
		}
		
		int start = 1;
		boolean isAble = true;
		a[0] = -1;
		isUsed[0] = true;
		
		if (q.isEmpty()) {
			isAble = false;
		}
		
		while (!q.isEmpty() && isAble) {
			int end = q.poll();
			int currNum = end;
			int maxNum = 0;
			for (int i = start; i <= end; i++) {
				if (a[i] == 0) {
					
					while (isUsed[currNum] && currNum >= start) {
						currNum--;
					}
					
					if (isUsed[currNum]) {
						isAble = false;
						break;
					}
					
					a[i] = currNum--;
				}
				
				maxNum = Math.max(maxNum, a[i]);
				
				if (maxNum > end || (maxNum == i && i != end)) {
					isAble = false;
					break;
				}
			}
			
			start = end+1;
		}
		
		if (isAble) {
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= n; i++) {
				sb.append(a[i] + " ");
			}
			
			System.out.println(sb);
		} else {
			System.out.println(-1);
		}
	}

}
