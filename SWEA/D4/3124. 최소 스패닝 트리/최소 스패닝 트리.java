import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	
	int next;
	int cost;
	
	public Edge(int next, int cost) {
		this.next = next;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

public class Solution {

	static int v;
	static int e;
	static long answer;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			st = new StringTokenizer(br.readLine());
			
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			
			visited = new boolean[v+1];
			ArrayList<Edge>[] edges = new ArrayList[v+1];
			
			for (int i = 0; i <= v; i++) {
				edges[i] = new ArrayList<>();
			}
			
			PriorityQueue<Edge> lines = new PriorityQueue<>();
			
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				edges[x].add(new Edge(y, cost));
				edges[y].add(new Edge(x, cost));
			}
			
			answer = 0;
			visited[1] = true;
			
			for (int i = 0; i < edges[1].size(); i++) {
				lines.add(edges[1].get(i));
			}
			
			while (!lines.isEmpty()) {
				Edge curr = lines.poll();
				
				int next = curr.next;
				int cost = curr.cost;
				
				if (!visited[next]) {
					answer += cost;
					visited[next] = true;
					for (int i = 0; i < edges[next].size(); i++) {
						lines.add(edges[next].get(i));
					}
				}
			}
			
			System.out.println("#" + t + " " + answer);
		}

	}

}
