import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int[] oper;
	static int[] num;
	static int minNum;
	static int maxNum;
	
	public static int calculate(int a, int o, int b) {
		if (o == 0) {
			return a + b;
		} else if (o == 1) {
			return a - b;
		} else if (o == 2) {
			return a * b;
		} else {
			return a / b;
		}
	}
	
	public static void dfs(int a, int count) {
		
		if (count == n-1) {
			minNum = Math.min(minNum, a);
			maxNum = Math.max(maxNum, a);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (oper[i] != 0) {
				int o = i;
				oper[i]--;
				dfs(calculate(a, o, num[count+1]), count+1);
				oper[i]++;
			}		
		}
	}
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	
        	n = Integer.parseInt(br.readLine());
        	oper = new int[4];
        	num = new int[n];
        	
        	st = new StringTokenizer(br.readLine());
        	
        	for (int i = 0; i < 4; i++) {
        		oper[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	st = new StringTokenizer(br.readLine());
        	
        	for (int i = 0; i < n; i++) {
        		num[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	minNum = Integer.MAX_VALUE;
        	maxNum = Integer.MIN_VALUE;
        	
        	dfs(num[0], 0);

        	System.out.println("#" + t + " " + (maxNum - minNum));
        }
	}
}
