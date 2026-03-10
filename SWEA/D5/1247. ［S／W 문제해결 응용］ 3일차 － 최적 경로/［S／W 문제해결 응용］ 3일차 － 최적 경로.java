import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int comx;
	static int comy;
	static int homex;
	static int homey;
	static int[][] customers;
	static boolean[] visited;
	static int answer;
	
	public static void dfs(int c, int[] route) {
		
		if (c == n) {
			int temp = 0;
			int x = comx;
			int y = comy;
			
			
			for (int i = 0; i < n; i++) {
				temp += Math.abs(x - customers[route[i]][0]) + Math.abs(y - customers[route[i]][1]);
				x = customers[route[i]][0];
				y = customers[route[i]][1];
			}
			
			temp += Math.abs(x - homex) + Math.abs(y - homey);
			
			answer = Math.min(answer, temp);
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				route[c] = i;
				dfs(c+1, route);
				visited[i] = false;
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
        
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	
        	n = Integer.parseInt(br.readLine());

        	st = new StringTokenizer(br.readLine());
        	comx = Integer.parseInt(st.nextToken());
        	comy = Integer.parseInt(st.nextToken());
        	homex = Integer.parseInt(st.nextToken());
        	homey = Integer.parseInt(st.nextToken());
        	customers = new int[n][2];
        	
        	for (int i = 0; i < n; i++) {
        		customers[i][0] = Integer.parseInt(st.nextToken());
        		customers[i][1] = Integer.parseInt(st.nextToken());
        	}
        	
        	answer = Integer.MAX_VALUE;
        	int[] route = new int[n];
        	visited = new boolean[n];
        	
        	dfs(0, route);
        	
        	System.out.println("#" + t + " " + answer);
        }

	}

}
