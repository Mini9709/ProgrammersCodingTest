import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int m;
	static int k;
	static int answer;
	static ArrayList<int[]> homes;
	static int[][] map;
	
	public static int checked(int k, double price) {
		
		int res = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int sumPrice = 0;
				int sumCount = 0;
				
				for (int[] h : homes) {
					int x = h[0]-j;
					int y = h[1]-i;
					
					if (Math.abs(x) + Math.abs(y) < k) {
						sumPrice += m;
						sumCount++;
					}
				}
				
				if (sumPrice - price >= 0) {		
					res = Math.max(res, sumCount);
				}
			}
		}
		
		return res;
	}

	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	
        	st = new StringTokenizer(br.readLine());
        	
        	n = Integer.parseInt(st.nextToken());
        	m = Integer.parseInt(st.nextToken());
        	map = new int[n][n];
        	homes = new ArrayList<>();
        	int maxHome = 0;
        	
        	for (int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		for (int j = 0; j < n; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        			
        			if (map[i][j] == 1) {
        				maxHome += m;
        				homes.add(new int[] {j, i});
        			}
        		}
        	}
        	
        	answer = 0;
        	k = 1;
        	
        	while (Math.pow(k, 2) + Math.pow(k-1, 2) <= maxHome) {
        		answer = Math.max(answer, checked(k, Math.pow(k, 2) + Math.pow(k-1, 2)));
        		k++;
        	}
        	
        	System.out.println("#" + t + " " + answer);
        }
	}

}