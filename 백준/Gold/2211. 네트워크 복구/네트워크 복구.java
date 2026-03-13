import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int[][] network;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        network = new int[n+1][n+1];
        
        for (int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	network[a][b] = c; 
        	network[b][a] = c; 
        }
        
        dp = new int[n+1][2];
        for (int i = 1; i <= n; i++) {
        	dp[i][0] = Integer.MAX_VALUE; // 현재 노드의 코스트
        }
        
        dp[1][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        pq.add(new int[] {1, dp[1][0]});
        
        while(!pq.isEmpty()) {
        	int[] curr = pq.poll();
        	int node = curr[0];
        	int cost = curr[1];
        	
        	if (dp[node][0] < cost) continue;
        	
        	for (int i = 1; i < network[node].length; i++) {
        		if (network[node][i] != 0 && dp[i][0] > dp[node][0] + network[node][i]) {
        			dp[i][0] = dp[node][0] + network[node][i];
        			dp[i][1] = node;
        			pq.add(new int[] {i, dp[i][0]});
        		}
        	}
        }
        
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i <= n; i++) {
        	if (dp[i][1] != 0) {
        		sb.append(i + " " + dp[i][1] + '\n');
        		answer ++;
        	}
        }
        
        System.out.println(answer);
        System.out.println(sb);
	}
}
