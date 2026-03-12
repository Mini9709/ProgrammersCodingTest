import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static double e;
	static long[][] islands;
	static boolean[] visited;
	static long answer;
	
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	n = Integer.parseInt(br.readLine());
        	islands = new long[n][2];
        	visited = new boolean[n];
        	PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < n; i++) {
        		islands[i][0] = Long.parseLong(st.nextToken());
        	}
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < n; i++) {
        		islands[i][1] = Long.parseLong(st.nextToken());
        	}
        	
        	e = Double.parseDouble(br.readLine());
        	
        	answer = 0;
        	pq.add(new long[] {0, 0});
        	
        	while (!pq.isEmpty()) {
        		long[] curr = pq.poll();
        		int currI = (int) curr[1];
        		
        		if (visited[currI]) continue;
        		
        		answer += curr[0];
        		visited[currI] = true;
    			for (int i = 0; i < n; i++) {
    				if (!visited[i]) {
    					long x = Math.abs(islands[currI][0] - islands[i][0]);
            			long y = Math.abs(islands[currI][1] - islands[i][1]);
            			pq.add(new long[]{(x*x + y*y), i});
    				}
    			}
        	}
        	
        	answer = Math.round(answer * e);
        	
        	System.out.println("#" + t + " " + answer);
        }
	}

}
