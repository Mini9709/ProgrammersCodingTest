import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int find(int[] parent, int x) {
		if (parent[x] == x) return x;
		else return find(parent, parent[x]);
	}
	
	public static void union(int[] parent, int x, int y) {
		x = find(parent, x);
		y = find(parent, y);
		
		if (x < y) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        int l = Integer.parseInt(br.readLine());
        
        boolean[][] lines = new boolean[n+1][n+1];
        boolean[] visited = new boolean[n+1];
        int[] parent = new int[n+1];
        int answer = 0;
        
        for (int i = 0; i < l; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int c1 = Integer.parseInt(st.nextToken());
        	int c2 = Integer.parseInt(st.nextToken());
        	
        	lines[c1][c2] = true;
        	lines[c2][c1] = true;
        }
        
        for (int i = 1; i <= n; i++) {
        	parent[i] = i;
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        
        for (int i = 1; i <= n; i++) {
        	if (!visited[i]) {
				q.add(i);
				visited[i] = true;
				
				while (!q.isEmpty()) {
					int curr = q.poll();
					
					for (int j = i+1; j <= n; j++) {
						if (lines[curr][j] && !visited[j]) {
							visited[j] = true;
							union(parent, i, j);
							q.add(j);
						}
					}	
				}
        	}
        }
        
        for (int i = 2; i <= n; i++) {
        	if (parent[i] == parent[1]) {
        		answer++;
        	}
        }
        
        System.out.println(answer);
	}

}
