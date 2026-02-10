import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int k;
	static int[][] map;
	static int maxHeight;
	static int answer;
	static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static boolean[][] visited;
	
	public static void dfs(int x, int y, boolean isUsed, int sum) {
		
		boolean isMoved = false;
		
		for (int[] d : directions) {
			int nextX = x + d[0];
			int nextY = y + d[1];
			
			if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !visited[nextY][nextX]) {
				if (map[nextY][nextX] < map[y][x]) {
					isMoved = true;
					
					visited[nextY][nextX] = true;
					dfs(nextX, nextY, isUsed, sum + 1);
					visited[nextY][nextX] = false;	
				} else if (map[nextY][nextX] - k  < map[y][x] && !isUsed) {
					int temp = map[nextY][nextX];
					isMoved = true;
					
					map[nextY][nextX] = map[y][x] - 1;
					visited[nextY][nextX] = true;
					dfs(nextX, nextY, true, sum + 1);
					map[nextY][nextX] = temp;
					visited[nextY][nextX] = false;
				}
			} 
		}
		
		if (!isMoved) {
			answer = Math.max(answer, sum);
		}
	}

	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	
        	st = new StringTokenizer(br.readLine());
        	n = Integer.parseInt(st.nextToken());
        	k = Integer.parseInt(st.nextToken());
        	
        	map = new int[n][n];
        	maxHeight = 0;
        	answer = 0;
        	
        	for (int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		for (int j = 0; j < n; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        			
        			maxHeight = Math.max(maxHeight, map[i][j]);
        		}
        	}
        	
        	for (int i = 0; i < n; i++) {
        		for (int j = 0; j < n; j++) {
        			if (maxHeight == map[i][j]) {
        				visited = new boolean[n][n];
        				visited[i][j] = true;
        				
        				dfs(j, i, false, 1);
        			}
        		}
        	}
        	
        	System.out.println("#" + t + " " + answer);
        }

	}

}
