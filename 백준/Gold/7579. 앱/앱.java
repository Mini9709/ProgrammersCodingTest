import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int answer;
	static int maxCost;
	static int[][] cost;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		cost = new int[n][2];
		maxCost = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			cost[i][0] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			cost[i][1] = Integer.parseInt(st.nextToken());
			maxCost += cost[i][1];
		}
		
		dp = new int[n+1][maxCost+1];
		answer = Integer.MAX_VALUE;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= maxCost; j++) {
				if (cost[i-1][1] <= j) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i-1][1]] + cost[i-1][0]);
				} else {
					dp[i][j] = dp[i-1][j];
				}
				
				if (dp[i][j] >= m) {
					answer = Math.min(answer, j);
				}
			}
		}
		
		System.out.println(answer);
	}

}
