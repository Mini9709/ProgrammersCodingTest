import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static int n;
	static char[][] map;
	static boolean[][] visited;
	static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        
        for (int i = 0; i < n; i++) {
        	String str = br.readLine();
        	
        	for (int j = 0; j < n; j++) {
        		map[i][j] = str.charAt(j);
        	}
        }
        
        Queue<int[]> q = new ArrayDeque<>();
        visited = new boolean[n][n];
        int answer1 = 0;
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		if (!visited[i][j]) {
        			q.add(new int[] {j, i});
        			visited[i][j] = true;
        			
        			while (!q.isEmpty()) {
        	        	int[] curr = q.poll();
        	        	
        	        	int x = curr[0];
        	        	int y = curr[1];
        	        	
        	        	for (int[] d : directions) {
        	        		int nx = x + d[0];
        	        		int ny = y + d[1];
        	        		
        	        		if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[ny][nx] && map[y][x] == map[ny][nx]) {
        	        			visited[ny][nx] = true;
        	        			q.add(new int[] {nx, ny});
        	        		}
        	        	}
        	        }
        			
        			answer1++;
        		}
        	}
        }
        
        visited = new boolean[n][n];
        int answer2 = 0;
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		if (!visited[i][j]) {
        			q.add(new int[] {j, i});
        			visited[i][j] = true;
        			
        			while (!q.isEmpty()) {
        	        	int[] curr = q.poll();
        	        	
        	        	int x = curr[0];
        	        	int y = curr[1];
        	        	
        	        	for (int[] d : directions) {
        	        		int nx = x + d[0];
        	        		int ny = y + d[1];
        	        		
        	        		if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[ny][nx]) {
        	        			if ((map[y][x] == 'R' || map[y][x] == 'G') && (map[ny][nx] == 'R' || map[ny][nx] == 'G')) {
        	        				visited[ny][nx] = true;
            	        			q.add(new int[] {nx, ny});
        	        			} else if (map[y][x] == map[ny][nx]) {
        	        				visited[ny][nx] = true;
            	        			q.add(new int[] {nx, ny});
        	        			}
        	        		}
        	        	}
        	        }
        			
        			answer2++;
        		}
        	}
        }
        
        System.out.println(answer1 + " " + answer2); 
	}

}
