import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int maxCount;
	static int answer;
	static int[][] map;
	static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
	   	 
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
        	
        	maxCount = 0;
        	answer = Integer.MAX_VALUE;
        	Queue<int[]> q = new ArrayDeque<>();
        	
        	for (int i = 0; i < n; i++) {
        		for (int j = 0; j < n; j++) {
        			q.add(new int[] {j, i});
        			int count = 0;
        			int temp = map[i][j];
        			
        			while (!q.isEmpty()) {
    					int[] curr = q.poll();
    					int x = curr[0];
    					int y = curr[1];
    					
    					for (int[] d : directions) {
    						int nx = x + d[0];
    						int ny = y + d[1];
    						
    						if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[y][x]+1 == map[ny][nx]) {
    							q.add(new int[] {nx, ny});
    						}
    					}
        				
        				count++;
        			}
        			
        			if (maxCount == count) {
        				answer = Math.min(answer, temp);
        			} else if (maxCount < count) {
        				maxCount = count;
        				answer = temp;
        			}
        		}
        	}
        	
        	System.out.println("#" + t + " " + answer + " " + maxCount);
        }

	}

}
