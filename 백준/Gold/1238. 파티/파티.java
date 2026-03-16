import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
	
	static int n;
	static int m;
	static int x;
	static int answer;
	static int[] go;
	static int[] back;
	static ArrayList<int[]>[] route1;
	static ArrayList<int[]>[] route2;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		//------여기에 솔루션 코드를 작성하세요.------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		route1 = new ArrayList[n+1];
		route2 = new ArrayList[n+1];
		
		for (int i = 1; i <= n; i++) {
			route1[i] = new ArrayList<>();
			route2[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int prev = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			route1[prev].add(new int[] {next, time});
			route2[next].add(new int[] {prev, time});
		}
		
		answer = 0;

		go = new int[n+1];
		back = new int[n+1];
		
		Arrays.fill(go, Integer.MAX_VALUE);
		Arrays.fill(back, Integer.MAX_VALUE);
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		pq.add(new int[] {x, 0});
        go[x] = 0;
		back[x] = 0;
		
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int start = curr[0];
			int time = curr[1];
			
			if (go[start] < time) continue;
			
			for (int[] n : route1[start]) {
				if (go[n[0]] > n[1] + time) {
					go[n[0]] = n[1] + time;
					pq.add(new int[] {n[0], go[n[0]]});
				}
			}
		}
		
		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		pq.add(new int[] {x, 0});
		
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int start = curr[0];
			int time = curr[1];
			
			if (back[start] < time) continue;
			
			for (int[] n : route2[start]) {
				if (back[n[0]] > n[1] + time) {
					back[n[0]] = n[1] + time;
					pq.add(new int[] {n[0], back[n[0]]});
				}
			}
		}
		
		for (int i = 1; i <= n; i++) {
			answer = Math.max(answer, go[i] + back[i]);
		}
		
		System.out.println(answer);
	}
}