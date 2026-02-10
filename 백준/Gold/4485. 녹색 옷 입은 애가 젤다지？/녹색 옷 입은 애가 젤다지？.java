import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[][] map;
	static int[][] price;
	static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        int t = 0;
        
        while (n != 0) {
        	t++;
        	map = new int[n][n];
        	price = new int[n][n];
        	
        	for (int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		for (int j = 0; j < n; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        			price[i][j] = -1;
        		}
        	}
        	
        	Queue<int[]> q = new ArrayDeque<>();
        	
        	q.add(new int[] {0, 0});
        	price[0][0] = map[0][0];
        	
        	while (!q.isEmpty()) {
        		int[] curr = q.poll();
        		
        		int x = curr[0];
        		int y = curr[1];
        		
        		for (int[] d : directions) {
        			int nextX = x + d[0];
        			int nextY = y + d[1];
        			
        			if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
        				int nextPrice = price[y][x] + map[nextY][nextX];
        				
        				if (price[nextY][nextX] == -1 | price[nextY][nextX] > nextPrice) {
        					price[nextY][nextX] = nextPrice;
        					q.add(new int[] {nextX, nextY});
        				}
        			}
        		}
        	}
        	
        	System.out.println("Problem " + t + ": " + price[n-1][n-1]);
        	n = Integer.parseInt(br.readLine());
        }

	}

}
