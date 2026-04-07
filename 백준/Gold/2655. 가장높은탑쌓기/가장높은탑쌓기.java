import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] up;
	static int[][] bricks;
	static int[][] dp;
	static int maxHeight;
	static int count;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		bricks = new int[n+1][3];
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			bricks[i][0] = Integer.parseInt(st.nextToken()); // 밑면
			bricks[i][1] = Integer.parseInt(st.nextToken()); // 높이
			bricks[i][2] = Integer.parseInt(st.nextToken()); // 무게
		}
		
		dp = new int[n+1][n+1]; // [탑을 쌓을 때 필요한 최대 개수][기반이 되는 벽돌의 인덱스]
		up = new int[n+1];
		
		for (int i = 0; i <= n; i++) {
			dp[1][i] = bricks[i][1];
		}
		
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = dp[i-1][j];
				
				for (int k = 1; k <= n; k++) {
					if (bricks[j][0] > bricks[k][0] && bricks[j][2] >= bricks[k][2]) {
						dp[i][j] = Math.max(dp[i][j], dp[i-1][k] + bricks[j][1]);
						if (dp[i][j] == dp[i-1][k] + bricks[j][1]) {
							up[j] = k;
						}
					}
				}
			}
		}
		
		int idx = 0;
		maxHeight = 0;
		count = 0;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= n; i++) {
			if (maxHeight < dp[n][i]) {
				idx = i;
				maxHeight = dp[n][i];
			}
		}
		
		Stack<Integer> stack = new Stack<>();
		while (up[idx] != 0) {
			stack.add(idx);
			idx = up[idx];
			count++;
		}
		
		stack.add(idx);
		count++;
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append('\n');
		}
		
		System.out.println(count);
		System.out.println(sb);
	}

}
