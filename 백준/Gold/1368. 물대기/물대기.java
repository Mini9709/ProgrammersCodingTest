import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] parent;
	static int[] cost;
	static boolean visited[];
	static int[][] connections;
	static int answer;
	
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        cost = new int[n];
        connections = new int[n][n];
        visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        
        for (int i = 0; i < n; i++) {
        	cost[i] = Integer.parseInt(br.readLine());
        	pq.add(new int[] {cost[i], i});
        }

        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	for (int j = 0; j < n; j++) {
        		connections[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        while (!pq.isEmpty()) {
        	int[] curr = pq.poll();
        	int c = curr[0];
        	int x = curr[1];
        	
        	if (!visited[x]) {
        		visited[x] = true;
        		answer += Math.min(c, cost[x]);
        		
        		for (int nx = 0; nx < n; nx++) {
        			if (!visited[nx]) {
        				pq.offer(new int[] {connections[x][nx], nx});
        			}
        		}
        	}
        }
        
        System.out.println(answer);
	}

}
