import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	
	List<Node> prevNode = new ArrayList<>();
	List<Node> nextNode = new ArrayList<>();
	int data;
	
	public Node() {}
	
	public Node(int data) {
		this.data = data;
	}
	
	public void addNextNode(Node nextNode) {
		this.nextNode.add(nextNode);
	}
	
	public void addPrevNode(Node prevNode) {
		this.prevNode.add(prevNode);
	}
}

public class Solution {
	
	// 자신의 자식 노드와 조상 노드가 모든 노드면 순서를 알 수 있음.
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			int n = Integer.parseInt(br.readLine());
			int m = Integer.parseInt(br.readLine());
			
			Node[] arr = new Node[n+1];
			
			for (int i = 1; i <= n; i++) {
				arr[i] = new Node(i);
			}
			
			for (int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int prevNode = Integer.parseInt(st.nextToken());
				int nextNode = Integer.parseInt(st.nextToken());
				
				arr[prevNode].addNextNode(arr[nextNode]);
				arr[nextNode].addPrevNode(arr[prevNode]);
			}
			
			int answer = 0;
			
			boolean[] isAnswer = new boolean[n+1];
			
			for(int i = 1; i <= n; i++) {
				isAnswer[i] = true;
			}
			
			Queue<Node> queue = new ArrayDeque<>();
			
			/*
			 * 1. 노드를 돌며 루트노드 찾기
			 * 2. 찾으면 q에 돌고 반복문 시행
			 * 3. 자신과 자손노드, 조상노드를 체크
			 * 4. 반복문 종료 후, 모든(인덱스 0 제외) 노드가 체크되어 있는 노드 탐색
			 */
			
			for (int i = 1; i <= n; i++) {
				queue.offer(arr[i]);
				boolean[] checkedNode = new boolean[n+1];
				checkedNode[i] = true;
				
				while (!queue.isEmpty()) {
					Node curr = queue.poll();
					
					for (Node next : curr.nextNode) {
						if (!checkedNode[next.data]) {
							checkedNode[next.data] = true;
							queue.add(next);
						}
					}
				}
				
				queue.offer(arr[i]);
				while (!queue.isEmpty()) {
					Node curr = queue.poll();
					
					for (Node prev : curr.prevNode) {
						if (!checkedNode[prev.data]) {
							checkedNode[prev.data] = true;
							queue.add(prev);
						}
					}
				}
				
				for (int j = 1; j <= n; j++) {
					if (!checkedNode[j]) {
						isAnswer[i] = false;
						break;
					}
				}
			}
			
			for (int i = 1; i <= n; i++) {
				if (isAnswer[i]) {
					answer += 1;
				}
			}
			
			System.out.printf("#%d %d\n", t, answer);
		}

	}

}
