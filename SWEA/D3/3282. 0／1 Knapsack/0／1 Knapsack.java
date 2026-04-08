import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int k;
	static int[][] items;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			items = new int[n][2];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				items[i][0] = Integer.parseInt(st.nextToken());
				items[i][1] = Integer.parseInt(st.nextToken());
			}
			
			dp = new int[n+1][k+1];
			
			for (int i = 1; i <= n; i++) {
				for (int j = 0; j <= k; j++) {
					if (items[i-1][0] <= j) {
						dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-items[i-1][0]] + items[i-1][1]);
					} else {
						dp[i][j] = dp[i-1][j];
					}
				}
			}
			
			System.out.println("#" + t + " " + dp[n][k]);
		}

	}

}
