import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	int n = Integer.parseInt(br.readLine());
        	int[][] map = new int[n][n];
        	int answer = 0;
        	
        	for (int i = 0; i < n; i++) {
        		String str = br.readLine();
        		
        		for (int j = 0; j < n; j++) {
        			map[i][j] = str.charAt(j) - '0';
        		}
        	}
        	
        	int mid = n/2;
        	
        	for (int i = 0; i < n; i++) {
        		for (int j = 0; j < n; j++) {
        			if (i <= mid) {
        				if (j <= mid+i && j >= mid-i) {
        					answer += map[i][j];
        				}
        			} else {
        				if (j <= n-1-(i-mid) && j >= i-mid) {
        					answer += map[i][j];
        				}
        			}
        		}
    		}
        	
        	System.out.println("#" + t + " " + answer); 	
        }

	}

}
