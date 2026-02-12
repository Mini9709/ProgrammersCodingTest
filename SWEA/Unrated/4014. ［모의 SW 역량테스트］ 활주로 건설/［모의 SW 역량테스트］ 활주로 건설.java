import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int x;
	static int answer;
	static int[][] map;

	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	st = new StringTokenizer(br.readLine());
        	n = Integer.parseInt(st.nextToken());
        	x = Integer.parseInt(st.nextToken());
        	map = new int[n][n];
        	
        	for (int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		for (int j = 0; j < n; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
      	
        	answer = 0;
        	
        	for (int i = 0; i < n; i++) {
        		int curr = map[i][0];
        		int len = 1;
        		boolean isAble = true;
        		
        		for (int j = 1; j < n; j++) {
        			if (curr == map[i][j]) { // 같은 높이
        				len++;
        				curr = map[i][j];
        			} else if (curr == map[i][j] + 1) { // 1 내려감
        				if (len >= 0) {
        					len = 1-x;
        					curr = map[i][j];
        				} else {
        					isAble = false;
        					break;
        				}
        			} else if (curr == map[i][j] - 1) { // 1 올라감
        				if (len >= x) {
        					len = 1;
        					curr = map[i][j];
        				} else {
        					isAble = false;
        					break;
        				}
        			} else { // 높이 차가 2 이상일 때 불가능
        				isAble = false;
    					break;
        			}
        			
        		}
        		
        		if (len < 0) {
        			isAble = false;
        		}
        		
        		if (isAble) {
        			answer++;
        		}
        	}
        	
        	for (int i = 0; i < n; i++) {
        		int curr = map[0][i];
        		int len = 1;
        		boolean isAble = true;
        		
        		for (int j = 1; j < n; j++) {
        			if (curr == map[j][i]) { // 같은 높이
        				len++;
        				curr = map[j][i];
        			} else if (curr == map[j][i] + 1) { // 1 내려감
        				if (len >= 0) {
        					len = 1-x;
        					curr = map[j][i];
        				} else {
        					isAble = false;
        					break;
        				}
        			} else if (curr == map[j][i] - 1) { // 1 올라감
        				if (len >= x) {
        					len = 1;
        					curr = map[j][i];
        				} else {
        					isAble = false;
        					break;
        				}
        			} else { // 높이 차가 2 이상일 때 불가능
        				isAble = false;
    					break;
        			}
        			
        		}
        		
        		if (len < 0) {
        			isAble = false;
        		}
        		
        		if (isAble) {
        			answer++;
        		}
        	}
        	
        	System.out.println("#" + t + " " + answer);
        }

	}

}
