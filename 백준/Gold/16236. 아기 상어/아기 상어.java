import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int x;
	static int y;
	static int dx;
	static int dy;
	static int size;
	static int eat;
	static int answer;
	static int[][] map;
	static int[][] distance;
	static int[][] directions = {{1, 0}, {-1, 0} ,{0, 1}, {0, -1}};
	
	public static boolean search(int startX, int startY) {
		
		Queue<int[]> q = new ArrayDeque<>();
        Queue<int[]> fq = new ArrayDeque<>();
        distance = new int[n][n];
        q.add(new int[] {startX, startY});
        
		while (!q.isEmpty()) {
        	int qSize = q.size();
        	
        	for (int i = 0; i < qSize; i++) {
        		int[] curr = q.poll();
            	int cx = curr[0];
            	int cy = curr[1];
            	
            	for (int[] d : directions) {
            		int nx = cx + d[0];
            		int ny = cy + d[1];
            		
            		if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[ny][nx] != 9 && distance[ny][nx] == 0 && map[ny][nx] <= size) {
            			distance[ny][nx] = distance[cy][cx] + 1;
            			q.add(new int[] {nx, ny});
            			
            			if (map[ny][nx] > 0 && map[ny][nx] < size) {
            				fq.add(new int[] {nx, ny});
            			}
            		}
            	}
        	}
        	
        	if (!fq.isEmpty()) {
        		answer += distance[fq.peek()[1]][fq.peek()[0]];
        		
        		while (!fq.isEmpty()) {
            		int[] fish = fq.poll();
            		if (dx == -1) {
            			dx = fish[0];
            			dy = fish[1];
            		} else {
            			if (dy > fish[1] || (dy == fish[1] && dx > fish[0])) {
            				dx = fish[0];
                			dy = fish[1];
            			} 
            		}
            	}
        		
        		return true;
        	}
        }
		
		return false;
	}
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        ArrayList<int[]> fishes = new ArrayList<>();;
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	for (int j = 0; j < n; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		
        		if (map[i][j] == 9) {
        			x = j;
        			y = i;
        		} else if (map[i][j] != 0) {
        			fishes.add(new int[] {j, i});
        		}
        	
        	}
        }
        
        size = 2;
        eat = 0;
        answer = 0;

        dx = -1;
        dy = -1;
        
       while (search(x, y)) {
    	   eat++;
    	   map[y][x] = 0;
    	   map[dy][dx] = 9;
    	   if (eat == size) {
    		   size++;
    		   eat = 0;
    	   }
    	   x = dx;
    	   y = dy;
    	   dx = -1;
           dy = -1;
       }
       
       System.out.println(answer);
	}

}
