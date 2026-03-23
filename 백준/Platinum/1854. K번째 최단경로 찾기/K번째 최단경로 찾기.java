import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int k;
	static PriorityQueue<Integer>[] destinations;
	static ArrayList<int[]>[] route;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		route = new ArrayList[n+1];
		destinations = new PriorityQueue[n+1];
		
		for (int i = 1; i <= n; i++) {
			route[i] = new ArrayList<>();
			destinations[i] = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int prev = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			route[prev].add(new int[] {next, cost});
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		pq.add(new int[] {1, 0});
		destinations[1].add(0);
		
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			
			int next = curr[0];
			int cost = curr[1];

			for (int[] r : route[next]) {
				
				if (destinations[r[0]].size() == k) {
					if (destinations[r[0]].peek() <= r[1] + cost) continue;
					destinations[r[0]].add(r[1] + cost);
					destinations[r[0]].poll();
					pq.add(new int[] {r[0], r[1] + cost});
				} else {
					destinations[r[0]].add(r[1] + cost);
					pq.add(new int[] {r[0], r[1] + cost});
				}
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			if (destinations[i].size() == k) {
				int ans = destinations[i].poll();
				sb.append(ans).append('\n');
			} else {
				sb.append("-1\n");
			}
		}
		
		System.out.println(sb);
	}

}
