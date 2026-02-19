import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int v;
	static int e;
	static long answer;
	static int[] parent;
	
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
	
	public static void kruscal(int x, int y, int cost) {
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
			st = new StringTokenizer(br.readLine());
			
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			parent = new int[v+1];
			
			for (int i = 0; i <= v; i++) {
				parent[i] = i;
			}
			
			PriorityQueue<int[]> lines = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
			answer = 0;
			
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				lines.add(new int[] {x, y, cost});
			}
			
			for (int i = 0; i < e; i++) {
				int[] curr = lines.poll();
				kruscal(curr[0], curr[1], curr[2]);
			}
			
			System.out.println("#" + t + " " + answer);
		}

	}

}
