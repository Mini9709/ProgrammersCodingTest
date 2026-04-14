import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int[][] edges;
	static int[][] distances;
	static int answer;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			edges = new int[n][n];
			distances = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				Arrays.fill(distances[i], Integer.MAX_VALUE);
				distances[i][i] = 0;
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					edges[i][j] = Integer.parseInt(st.nextToken());
					
					if (edges[i][j] == 1) {
						distances[i][j] = 1;
					}
				}
			}

			
			for (int x = 0; x < n; x++) {
				for (int i = 0; i < n; i++) {
					if (i == x || distances[i][x] == Integer.MAX_VALUE) continue;
					for (int j = 0; j < n; j++) {
						if (j == x || i == j || distances[x][j] == Integer.MAX_VALUE) continue;
						
						distances[i][j] = Math.min(distances[i][j], distances[i][x] + distances[x][j]);
					}
				}
			}
			
			answer = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				int temp = 0;
				
				for (int j = 0; j < n; j++) {
					if (distances[i][j] == Integer.MAX_VALUE) {
						temp = Integer.MAX_VALUE;
						break;
					}
					
					temp += distances[i][j];
				}
				
				answer = Math.min(answer, temp);
			}
			
			System.out.println("#" + t + " " + answer);
		}

	}

}
