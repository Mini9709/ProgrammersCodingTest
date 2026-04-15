import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {

	int data;
	int degree = 0;
	ArrayList<Node> nextNode = new ArrayList<>();

	public Node(int data) {
		this.data = data;
	}
	
	@Override
	public int compareTo(Node o) {
		
		return Integer.compare(this.data, o.data);
	}

}

public class Main {

	static int n;
	static int m;
	static Node[] problems;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		problems = new Node[n+1];
		
		for (int i = 1; i <= n; i++) {
			problems[i] = new Node(i);
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int prev = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			
			problems[prev].nextNode.add(problems[next]);
			problems[next].degree++;
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for (int i = 1; i <= n; i++) {
			if (problems[i].degree == 0) {
				pq.add(problems[i]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			sb.append(curr.data).append(" ");
			for (int i = 0; i < curr.nextNode.size(); i++) {
				if (--curr.nextNode.get(i).degree == 0) {
					pq.add(curr.nextNode.get(i));
				}
			}
		}
		
		System.out.println(sb.toString());
	}

}
