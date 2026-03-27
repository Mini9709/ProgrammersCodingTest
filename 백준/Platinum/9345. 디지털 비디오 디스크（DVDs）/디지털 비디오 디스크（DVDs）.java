import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int k;
	static int[][] tree;
	static int[] values;
	
	public static int initMin(int start, int end, int node) {
		if (start == end) {
			values[start] = start;
			return tree[node][0] = start;
		}
		
		int mid = (start + end) / 2;
		
		return tree[node][0] = Math.min(initMin(start, mid, node*2), initMin(mid+1, end, node*2+1));
	}
	
	public static int initMax(int start, int end, int node) {
		if (start == end) {
			return tree[node][1] = start;
		}
		
		int mid = (start + end) / 2;
		
		return tree[node][1] = Math.max(initMax(start, mid, node*2), initMax(mid+1, end, node*2+1));
	}
	
	public static void update(int start, int end, int node, int point, int value) {
		
		if (start > point || end < point) {
			return;
		}
		
		if (start == end) {
			if (start == point) {
				tree[node][0] = value;
				tree[node][1] = value;
				values[point] = value;
			}
			return;
		}
		
		int mid = (start + end) / 2;
		
		update(start, mid, node*2, point, value);
		update(mid+1, end, node*2+1, point, value);
		
		tree[node][0] = Math.min(tree[node*2][0], tree[node*2+1][0]);
		tree[node][1] = Math.max(tree[node*2][1], tree[node*2+1][1]);
	}
	
	public static int searchMin(int start, int end, int node, int left, int right) {
		
		if (end < left || start > right) {
			return Integer.MAX_VALUE;
		}
		
		if (left <= start && right >= end) {
			return tree[node][0];
		}
		
		int mid = (start + end) / 2;
		
		return Math.min(searchMin(start, mid, node*2, left, right), searchMin(mid+1, end, node*2+1, left, right));
	}
	
	public static int searchMax(int start, int end, int node, int left, int right) {
		
		if (end < left || start > right) {
			return Integer.MIN_VALUE;
		}
		
		if (left <= start && right >= end) {
			return tree[node][1];
		}
		
		int mid = (start + end) / 2;
		
		return Math.max(searchMax(start, mid, node*2, left, right), searchMax(mid+1, end, node*2+1, left, right));
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			tree = new int[n*4][2];
			values = new int[n];
			
			initMin(0, n-1, 1);
			initMax(0, n-1, 1);
			
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < k; j++) {
				st = new StringTokenizer(br.readLine());
				
				int q = Integer.parseInt(st.nextToken());
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				
				if (q == 1) {
					if (searchMin(0, n-1, 1, left, right) == left && searchMax(0, n-1, 1, left, right) == right) {
						sb.append("YES").append('\n');
					} else {
						sb.append("NO").append('\n');
					}
				} else {
					int prevLeft = values[left];
					int prevRight = values[right];
					update(0, n-1, 1, right, prevLeft);
					update(0, n-1, 1, left, prevRight);
				}
			}
			
			System.out.print(sb);
		}
	}

}
