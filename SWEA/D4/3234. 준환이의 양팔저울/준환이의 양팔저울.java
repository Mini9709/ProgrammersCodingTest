import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int[] weight;
	static int answer;
	static boolean[] visited;
	
	public static void dfs(int x, int left, int right) {
		if (x == n) {
			answer++;
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				
				if (left + weight[i] >= right) {
					dfs(x+1, left + weight[i], right);
				}
				
				if (left >= right + weight[i]) {
					dfs(x+1, left, right + weight[i]);
				}
				
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			n = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			weight = new int[n];
			visited = new boolean[n];
			
			for (int i = 0; i < n; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = 0;
			
			dfs(0, 0, 0);
			
			System.out.println("#" + t + " " + answer);
		}
		
	}

}
