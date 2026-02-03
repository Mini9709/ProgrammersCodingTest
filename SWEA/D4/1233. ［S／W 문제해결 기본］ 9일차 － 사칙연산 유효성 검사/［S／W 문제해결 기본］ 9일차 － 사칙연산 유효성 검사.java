import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int t = 1; t <= 10; t++) {
			int n = Integer.parseInt(br.readLine());
			int[][] list = new int[n+1][4];
			int answer = 0;
			
			for (int i = 1; i <= n; i++) { 
				st = new StringTokenizer(br.readLine());
				
				int a = st.countTokens();
				
				for (int j = 0; j < a; j++) {
					String c = st.nextToken();
					
					if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")) {
						list[i][j] = -1;
					} else {
						list[i][j] = Integer.parseInt(c);
					}
				}
			}
			
			Queue<Integer> q = new ArrayDeque<>();
			int count = 0;
			q.add(1);
			
			while (!q.isEmpty()) {
				++count;
				int idx = q.poll();
				
				if (list[idx][1] == -1 && list[idx][2] != 0 && list[idx][3] != 0) {
					q.add(list[idx][2]);
					q.add(list[idx][3]);
				} else if (list[idx][1] != 0){
					continue;
				} else {
					break;
				}
			}
			
			if (count == n) {
				answer = 1;
			}
			
			System.out.printf("#%d %d\n", t, answer);
		}
		
	}

}
