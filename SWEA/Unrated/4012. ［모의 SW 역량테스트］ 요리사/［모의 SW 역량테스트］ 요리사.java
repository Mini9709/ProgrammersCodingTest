import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int answer;
	static int[][] map;
	
	public static boolean checkBit(int i) {
		
		int temp = 0;
		
		for (int j = 1; j <= n; j++) {
			if ((i & 1 << j-1) > 0) {
				temp++;
			}
		}
		
		if (temp == n/2) return true;
		else return false;
	}
	
	public static int sumItem(ArrayList<Integer> arr) {
		int sum = 0;
		
		for (int i = 0; i < arr.size()-1; i++) {
			for (int j = i+1; j < arr.size(); j++) {
				sum += map[arr.get(i)][arr.get(j)];
				sum += map[arr.get(j)][arr.get(i)];
			}
		}
		
		return sum;
	}

	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	n = Integer.parseInt(br.readLine());
        	
        	map = new int[n+1][n+1];
        	
        	for (int i = 1; i <= n; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		for (int j = 1; j <= n; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	int[] recipe = new int[1<<n];
        	
        	for (int i = 1; i < 1 << n; i++) {
        		if (checkBit(i)) {
        			ArrayList<Integer> arr = new ArrayList<>();
        			
        			for (int j = 1; j <= n; j++) {
        				if ((i & 1 << j-1) > 0) {
            				arr.add(j);
            			}
            		}
        			
        			recipe[i] = sumItem(arr);
        		}
        	}
        	
        	answer = Integer.MAX_VALUE;
        	
        	for (int i = 1; i < 1 << (n-1); i++) {
        		if (recipe[i] != 0) {
        			answer = Math.min(answer, Math.abs(recipe[i] - recipe[(1 << n)-1 -i]));
        		}
        	}
        	
        	System.out.println("#" + t + " " + answer);
        }

	}

}
