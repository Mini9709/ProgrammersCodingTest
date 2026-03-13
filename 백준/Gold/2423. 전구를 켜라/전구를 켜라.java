import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int answer;
	static int[][] dp;
	static char[][] route;
	static boolean[][] visited;
	static int[][] directions = {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new int[n+1][m+1];
        visited = new boolean[n+1][m+1];
        route = new char[n][m];
        
        for (int i = 0; i < n; i++) {
        	String str = br.readLine();
        	
        	for (int j = 0; j < m; j++) {
        		route[i][j] = str.charAt(j);
        	}
        }
        
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        dp[0][0] = 0;
        pq.add(new int[] {0, 0, 0});
        
        while (!pq.isEmpty()) {
        	int[] curr = pq.poll();
            int cost = curr[0];
            int x = curr[1];
            int y = curr[2];

            if (cost > dp[y][x]) continue;
        	
        	for (int d = 0; d < 4; d++) {
        		int nx = x + directions[d][0];
        		int ny = y + directions[d][1];
        		
        		if (nx >= 0 && nx <= m && ny >= 0 && ny <= n && !visited[ny][nx]) {
        			int nextCost = 0;
        			switch (d) {
        				case 0 :
        					if (route[y][x] == '\\') {
        						nextCost = dp[y][x];
        					} else {
        						nextCost = dp[y][x] + 1;
        					}
        					break;
        				case 1 :
        					if (route[y][x-1] == '/') {
        						nextCost = dp[y][x];
        					} else {
        						nextCost = dp[y][x] + 1;
        					}
        					break;
        				case 2 :
        					if (route[y-1][x] == '/') {
        						nextCost = dp[y][x];
        					} else {
        						nextCost = dp[y][x] + 1;
        					}
        					break;
        				case 3 :
        					if (route[y-1][x-1] == '\\') {
        						nextCost = dp[y][x];
        					} else {
        						nextCost = dp[y][x] + 1;
        					}
        					break;
    				}
        			
        			if (dp[ny][nx] > nextCost) {
        	            dp[ny][nx] = nextCost;
        	            pq.add(new int[]{nextCost, nx, ny});
        	        }
    			}
        	}
        }
        
        if ((n + m) % 2 == 0) {
        	System.out.println(dp[n][m]);
        } else {
        	System.out.println("NO SOLUTION");
        }
	}
}
