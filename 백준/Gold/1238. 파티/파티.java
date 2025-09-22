import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {

		int start;
		int end;
		int time;

		public Node(int start, int end, int time) {
			this.start = start;
			this.end = end;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {

			return time - o.time;
		}

	}

	public static void main(String[] args) throws IOException {

		ArrayList<ArrayList<Node>> road_graph;
		int answer = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int students = Integer.parseInt(st.nextToken());
		int roads = Integer.parseInt(st.nextToken());
		int destination = Integer.parseInt(st.nextToken());

		road_graph = new ArrayList<>();

		for (int i = 0; i <= students; i++) {

			road_graph.add(new ArrayList<>());
		}

		for (int i = 0; i < roads; i++) {

			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			Node n = new Node(start, end, time);
			road_graph.get(start).add(n);
			road_graph.get(end).add(n);
		}

		int[] dp_go = new int[students + 1];
		int[] dp_come = new int[students + 1];
		for (int i = 0; i < dp_go.length; i++) {
			dp_go[i] = Integer.MAX_VALUE;
			dp_come[i] = Integer.MAX_VALUE;
		}

		boolean[] visited = new boolean[students + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>();

		Node start_points = new Node(destination, destination, 0);
		dp_go[destination] = 0;
		dp_come[destination] = 0;
		pq.offer(start_points);

		while (!pq.isEmpty()) {

			Node curr_node = pq.poll();
			ArrayList<Node> curr_village = road_graph.get(curr_node.end);
			visited[curr_node.end] = true;

			for (Node o : curr_village) {
				if (!visited[o.end] && o.start == curr_node.end) {
					if (dp_come[o.end] > dp_come[curr_node.end] + o.time) {
						dp_come[o.end] = dp_come[curr_node.end] + o.time;
						pq.offer(new Node(o.start, o.end, dp_come[o.end]));
					}
				}
			}
		}

		visited = new boolean[students + 1];
		pq.offer(start_points);

		while (!pq.isEmpty()) {

			Node curr_node = pq.poll();
			ArrayList<Node> curr_village = road_graph.get(curr_node.start);
			visited[curr_node.start] = true;

			for (Node o : curr_village) {
				if (!visited[o.start] && o.end == curr_node.start) {
					if (dp_go[o.start] > dp_go[curr_node.start] + o.time) {
						dp_go[o.start] = dp_go[curr_node.start] + o.time;
						pq.offer(new Node(o.start, o.end, dp_go[o.start]));
					}
				}
			}
		}

		for (int i = 1; i < dp_go.length; i++) {
			answer = Math.max(answer, dp_come[i] + dp_go[i]);
		}

		System.out.println(answer);
	}
}