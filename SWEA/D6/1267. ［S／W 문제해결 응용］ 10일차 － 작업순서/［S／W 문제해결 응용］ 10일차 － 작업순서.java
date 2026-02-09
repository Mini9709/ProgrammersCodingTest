import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	
	int data;
	int degree;
	ArrayList<Node> child = new ArrayList<>();
	
	public Node(int data) {
		this.data = data;
	}
	
	public void addChild(Node node) {
		child.add(node);
	}
}

public class Solution {

	static int v;
	static int e;
	static Node[] nodes;
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for (int t = 1; t <= 10; t++) {
        	st = new StringTokenizer(br.readLine());
        	
        	v = Integer.parseInt(st.nextToken());
        	e = Integer.parseInt(st.nextToken());
        	
        	nodes = new Node[v+1];
        	
        	for (int i = 0; i <= v; i++) {
        		nodes[i] = new Node(i);
        	}
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < e; i++) {
        		int prev = Integer.parseInt(st.nextToken());
        		int next = Integer.parseInt(st.nextToken());
        		
        		nodes[prev].addChild(nodes[next]);
        		nodes[next].degree++;
        	}        	
        	
        	Queue<Node> queue = new ArrayDeque<>();
        	
        	for (int i = 1; i <= v; i++) {
        		if (nodes[i].degree == 0) {
        			queue.add(nodes[i]);

        		}
        	}
        	
        	StringBuilder sb = new StringBuilder();
        	sb.append("#" + t + " ");
        	
        	while (!queue.isEmpty()) {
        		Node curr = queue.poll();
        		sb.append(curr.data + " ");
        		
        		for (Node n : curr.child) {
        			n.degree--;
        			if (n.degree == 0) {
        				queue.add(n);
        			}
        		}
        	}
 
        	System.out.println(sb.toString());
        }

	}

}
