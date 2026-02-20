import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	static int startX;
	static int startY;
	static int desX;
	static int desY;
	static int[][] map;
	static boolean[][] visited;
	static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int t = 1; t <= 10; t++) {
        	int testCase = Integer.parseInt(br.readLine());
            map = new int[100][100];
        	
        	for (int i = 0; i < 100; i++) {
        		String str= br.readLine();
        		
        		for (int j = 0; j < 100; j++) {
        			map[i][j] = str.charAt(j) - '0';
        			
        			if (map[i][j] == 2) {
        				startX = j;
        				startY = i;
        			} else if (map[i][j] == 3) {
        				desX = j;
        				desY = i;
        			}
        		}
        	}
        	
        	visited = new boolean[100][100];
        	Queue<int[]> q = new ArrayDeque<>();
        	q.add(new int[] {startX, startY});
        	
        	while (!q.isEmpty()) {
        		int[] curr = q.poll();
        		
        		int x = curr[0];
        		int y = curr[1];
        		
        		for (int[] d : directions) {
        			int nx = x + d[0];
        			int ny = y + d[1];
        			if (nx >= 0 && nx < 100 && nx >= 0 && nx < 100 && !visited[ny][nx] && map[ny][nx] != 1) {
        				visited[ny][nx] = true;
        				q.add(new int[] {nx, ny});
        			}
        		}
        	}
        	
        	int answer = 0;
        	
        	if (visited[desY][desX]) {
        		answer = 1;
        	}
        	
        	System.out.println("#" + testCase + " " + answer);
        }

	}

}
