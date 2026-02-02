import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int m;
	static int[][] map;
	static int[][] sumMap;
	static int answer;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			map = new int[n+1][n+1];
			sumMap = new int[n+1][n+1];
			
			answer = 0;
			
			for (int y = 1; y <= n; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 1; x <= n; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
					sumMap[y][x] = map[y][x] + sumMap[y-1][x] + sumMap[y][x-1] - sumMap[y-1][x-1];
				}
			}
					
			for (int y = 1; y <= n-m+1; y++) {
				for (int x = 1; x <= n-m+1; x++) {
					int sum = sumMap[y+m-1][x+m-1] - sumMap[y+m-1][x-1] - sumMap[y-1][x+m-1] + sumMap[y-1][x-1];
					answer = Math.max(answer, sum);
				}
			}
			
			System.out.printf("#%d %d\n", t, answer);
		}
	}
}
