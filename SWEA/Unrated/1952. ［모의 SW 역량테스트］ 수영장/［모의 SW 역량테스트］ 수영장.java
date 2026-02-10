import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[] price;
	static int[] month;
	static int answer;
	
	public static void dfs(int m, int sum) {
		
		if (m == 12) {
			answer = Math.min(answer, sum);
			return;
		}
		
		int count = month[m];
		
		dfs(m+1, sum + (count*price[0]));
		dfs(m+1, sum + price[1]);
		
		if (m+3 < 12) {
			dfs(m+3, sum + price[2]);
		} else {
			dfs(12, sum + price[2]);
		}
		
		if (m == 0) {
			dfs(12, sum + price[3]);
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	
        	price = new int[4];
        	month = new int[12];
        	st = new StringTokenizer(br.readLine());
        	
        	for (int i = 0; i < 4; i++) {
        		price[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	st = new StringTokenizer(br.readLine());
        	
        	for (int i = 0; i < 12; i++) {
        		month[i] = Integer.parseInt(st.nextToken());
        	}
        	answer = Integer.MAX_VALUE;
        	dfs(0, 0);
        	
        	System.out.println("#" + t + " " + answer);
        }

	}

}
