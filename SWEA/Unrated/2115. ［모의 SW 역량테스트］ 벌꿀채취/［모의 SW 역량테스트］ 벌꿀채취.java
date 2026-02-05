import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int m;
	static int c;
	static int[][] honey;
	
	public static int gather(int y, int x) {
		int[][] dp = new int[m+1][c+1];
		
		for (int i = 1; i <= m; i++) {
			for (int j = 0; j <= c; j++) {
				if (honey[y][x+i-1] <= j) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-honey[y][x+i-1]] + (int) Math.pow(honey[y][x+i-1], 2));
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		return dp[m][c];
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			honey = new int[n][n];
			int[][] totalPrice = new int[n][n-m+1];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < totalPrice[0].length; x++) {
					totalPrice[y][x] = gather(y, x);
				}
			}
			
			int answer = 0;
			
			int fst = -1;
			int scd = -1;
			
			for (int y = 0; y < n; y++) {
				int maxHoney = -1;
				for (int x = 0; x < totalPrice[0].length; x++) {
					maxHoney = Math.max(maxHoney, totalPrice[y][x]);
				}
				
				if (maxHoney > fst) {
					scd = fst;
					fst = maxHoney;
				} else {
					scd = Math.max(scd, maxHoney);
				}
			}
			
			int maxHoney = -1;
			for (int y = 0; y < n; y++) {
				int maxSum = -1;
				for (int x = 0; x < totalPrice[0].length-m; x++) {
					int sum = -1;
					int temp = totalPrice[y][x];
					
					for (int z = x+m; z < totalPrice[0].length; z++) {
						sum = Math.max(sum, temp+totalPrice[y][z]); 
					}
					
					maxSum = Math.max(maxSum, sum);
				}
				
				maxHoney = Math.max(maxHoney, maxSum);
			}
			
			answer = Math.max(fst+scd, maxHoney);
			
			System.out.println("#" + t + " " + answer);
		}
	}

}