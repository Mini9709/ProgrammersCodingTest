import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int[] lst;
	static int[][] range;
	static int[][] tree;
	
	public static int initMin(int start, int end, int node) { // start ~ end : lst의 범위, node : 트리의 인덱스
		if (start == end) {
			return tree[node][0] = lst[start];
		}
		
		int mid = (start + end) / 2;
		
		return tree[node][0] = Math.min(initMin(start, mid, node*2), initMin(mid + 1, end, node*2 + 1));
	}
	
	public static int initMax(int start, int end, int node) { // start ~ end : lst의 범위, node : 트리의 인덱스
		if (start == end) {
			return tree[node][1] = lst[start];
		}
		
		int mid = (start + end) / 2;
		
		return tree[node][1] = Math.max(initMax(start, mid, node*2), initMax(mid + 1, end, node*2 + 1));
	}
	
	public static int searchMin(int start, int end, int node, int left, int right) { // left ~ right : 구하는 범위
		if (left > end || right < start) {
			return Integer.MAX_VALUE;
		}
		
		if (left <= start && right >= end) {
			return tree[node][0];
		}
		
		int mid = (start + end) / 2;
		
		return Math.min(searchMin(start, mid, node*2, left, right), searchMin(mid + 1, end, node*2 + 1, left, right));
	}
	
	public static int searchMax(int start, int end, int node, int left, int right) { // left ~ right : 구하는 범위
		if (left > end || right < start) {
			return Integer.MIN_VALUE;
		}
		
		if (left <= start && right >= end) {
			return tree[node][1];
		}
		
		int mid = (start + end) / 2;
		
		return Math.max(searchMax(start, mid, node*2, left, right), searchMax(mid + 1, end, node*2 + 1, left, right));
	}
	
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lst = new int[n];
        tree = new int[n*4][2];
        range = new int[m][2];
        
        for (int i = 0; i < n; i++) {
        	lst[i] = Integer.parseInt(br.readLine());
        }
        
        for (int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	range[i][0] = Integer.parseInt(st.nextToken())-1;
        	range[i][1] = Integer.parseInt(st.nextToken())-1;
        }
        
        initMin(0, n-1, 1);
        initMax(0, n-1, 1);
        
        for (int i = 0; i < m; i++) {
        	System.out.println(searchMin(0, n-1, 1, range[i][0], range[i][1]) + " " + searchMax(0, n-1, 1, range[i][0], range[i][1]));
        }
	}

}
