import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int[] dp;
	static int[][] items;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		items = new int[n][3]; // [무게, 만족도, 개수]
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
			items[i][2] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<ArrayList<int[]>> itemLists = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			itemLists.add(new ArrayList<>());

			int temp = items[i][2];
			int c = 1;
			
			while (temp > 0) {
				int weight = items[i][0] * c;
				int value = items[i][1] * c;
				
				if (temp > c) {
					temp -= c;
					c *= 2;
				} else {
					weight = items[i][0] * temp;
					value = items[i][1] * temp;
					temp = 0;
				}
				
				itemLists.get(i).add(new int[] {weight, value});
			}
			
		}
		
		dp = new int[m+1];
		
		for (int i = 0; i < n; i++) {
			for (int[] curr : itemLists.get(i)) {
				for (int j = m; j >= curr[0]; j--) {
					dp[j] = Math.max(dp[j], dp[j-curr[0]] + curr[1]);
				}
			}
		}

		System.out.println(dp[m]);
	}

}