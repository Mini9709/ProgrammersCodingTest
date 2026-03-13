import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge {

	int next;
	int cost;
	
	public Edge(int next, int cost) {
		this.next = next;
		this.cost = cost;
	}
}

public class Main {

	static int n;
	static int m;
	static int t;
	static int s;
	static int g;
	static int h;
	static ArrayList<Edge>[] edges;
	static int[] destinations;
	static int[] dp;
	
    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCase; tc++) {
        	st = new StringTokenizer(br.readLine());
        	
        	n = Integer.parseInt(st.nextToken());
        	m = Integer.parseInt(st.nextToken());
        	t = Integer.parseInt(st.nextToken());
        	edges = new ArrayList[n+1];
        	destinations = new int[t];
        	
        	st = new StringTokenizer(br.readLine());
        	
        	s = Integer.parseInt(st.nextToken());
        	g = Integer.parseInt(st.nextToken());
        	h = Integer.parseInt(st.nextToken());
        	
        	for (int i = 1; i <= n; i++) {
        		edges[i] = new ArrayList<>();
        	}
        	
        	for (int i = 0; i < m; i++) {
        		st = new StringTokenizer(br.readLine());
        		int prev = Integer.parseInt(st.nextToken());
        		int next = Integer.parseInt(st.nextToken());
        		int cost = Integer.parseInt(st.nextToken());
        		
        		if (prev == g && next == h || prev == h && next == g) {
        			edges[prev].add(new Edge(next, cost*2-1));
            		edges[next].add(new Edge(prev, cost*2-1));
        		} else {
            		edges[prev].add(new Edge(next, cost*2));
            		edges[next].add(new Edge(prev, cost*2));
        		}
        	}
        	
        	for (int i = 0; i < t; i++) {
        		destinations[i] = Integer.parseInt(br.readLine());
        	}
        	
        	Arrays.sort(destinations);
        	
        	PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        	dp = new int[n+1];
        	Arrays.fill(dp, Integer.MAX_VALUE);
        	dp[s] = 0;
        	pq.add(new int[] {s, dp[s]});
        	
        	
        	while(!pq.isEmpty()) {
        		int[] curr = pq.poll();
        		int currIdx = curr[0];
        		int currCost = curr[1];
        		
        		if (currCost > dp[currIdx]) continue;
        		
        		for (Edge ne : edges[currIdx]) {
        			if (dp[ne.next] > dp[currIdx] + ne.cost) {
        				dp[ne.next] = dp[currIdx] + ne.cost;
        				pq.add(new int[] {ne.next, dp[ne.next]});
        			}
        		}
        	}
        	
        	StringBuilder sb = new StringBuilder();
        	
        	for (int i = 0; i < t; i++) {
        		if (dp[destinations[i]] % 2 == 1 && dp[destinations[i]] != Integer.MAX_VALUE) {
        			sb.append(destinations[i] + " ");
        		}
        	}
        	
        	System.out.println(sb);
        }
	}

}
