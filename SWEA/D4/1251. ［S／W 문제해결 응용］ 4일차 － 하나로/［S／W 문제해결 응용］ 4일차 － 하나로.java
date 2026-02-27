import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {

	int i;
	int j;
	long cost;
	
	public Edge(int i, int j, long cost) {
		this.i = i;
		this.j = j;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge o) {
		return Long.compare(this.cost, o.cost);
	}
	
}

public class Solution {

	static int n;
	static int[] parent;
	static int[][] island;
	static long answer;
	static double e;
	
	public static int find(int x) {
		if (parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x <= y) parent[y] = x;
		else parent[x] = y;
	}
	
	public static void kruscal(int x, int y, long cost) {
		if (find(x) != find(y)) {
			answer += cost;
			union(x, y);
		}
	}
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	n = Integer.parseInt(br.readLine());
        	
        	island = new int[n][2];
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < n; i++) {
        		island[i][0] = Integer.parseInt(st.nextToken());
        	}
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < n; i++) {
        		island[i][1] = Integer.parseInt(st.nextToken());
        	}
        	
        	parent = new int[n];
        	
        	for (int i = 0; i < n; i++) {
        		parent[i] = i;
        	}
        	
        	e = Double.parseDouble(br.readLine());
        	PriorityQueue<Edge> pq = new PriorityQueue<>();
        	
        	answer = 0;
        	
        	for (int i = 0; i < n-1; i++) {
        		for (int j = i+1; j < n; j++) {
        			long lenX = Math.abs((island[i][0]-island[j][0]));
        			long lenY = Math.abs((island[i][1]-island[j][1]));
        			pq.add(new Edge(i, j, lenX*lenX + lenY*lenY));
        		}
        	}
        	
        	while (!pq.isEmpty()) {
        		Edge curr = pq.poll();
        		
        		kruscal(curr.i, curr.j, curr.cost);
        	}
        	
        	answer = Math.round(answer*e);
        	
        	System.out.println("#" + t + " " + answer);
        }
	}

}