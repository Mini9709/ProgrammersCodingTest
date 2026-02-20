import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int startX;
	static int startY;
	static int answer;
	static Set<Integer> set;
	static int[][] map;
	static int[][] directions = {{1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
	
	public static void move(int x, int y, int d, int count) {
		
		if (d == 3 && startX == x+1 && startY == y-1) {
			answer = Math.max(answer, count);
			return;
		}
		
		int nx = x + directions[d][0];
		int ny = y + directions[d][1];
		
		if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
			set.add(map[ny][nx]);

			if (set.size() == count+1) {
				move(nx, ny, d, count+1);
				
				if (d != 3) {
					
					move(nx, ny, d+1, count+1);
				}
				
				set.remove(map[ny][nx]);
			}
		}
	}
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			n = Integer.parseInt(br.readLine());
			
			map = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = -1;
			
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < n; x++) {
					set = new HashSet<>();
					set.add(map[y][x]);
					startX = x;
					startY = y;
					
					move(startX, startY, 0, 1);
				}
			}
			
			System.out.println("#" + t + " " + answer);
		}

	}

}
