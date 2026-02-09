import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int maxCount;
	static int sumLen;
	static int count;
	static int len;
	
	static int[][] map;
	static boolean[][] isUsed;
	static ArrayList<int[]> cells;
	static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void dfs(int cellNum) {
		
		if (cellNum == cells.size()) {
			if (count > maxCount) {
				maxCount = count;
				sumLen = len;
			} else if (count == maxCount) {
				sumLen = Math.min(sumLen, len);
			}
			return;
		}
		
		int x = cells.get(cellNum)[0];
		int y = cells.get(cellNum)[1];
		
		if (x == 0 || x == n-1 || y == 0 || y == n-1) {
			count++;
			dfs(cellNum+1);
			count--;
		}
		
		else {
			for (int[] d : directions) {
				int nextX = x + d[0];
				int nextY = y + d[1];
				
				while (true) {
					if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !isUsed[nextY][nextX]) {
						len++;
						isUsed[nextY][nextX] = true;
						if (nextX == 0 || nextX == n-1 || nextY == 0 || nextY == n-1) {
							count++;
							dfs(cellNum+1);
							count--;
							
							while (nextX != x || nextY != y) {
								len--;
								isUsed[nextY][nextX] = false;
								nextX -= d[0];
								nextY -= d[1];
							}
							break;
						}
						else {
							nextX += d[0];
							nextY += d[1];
						}
					} else {
						nextX -= d[0];
						nextY -= d[1];
						while (nextX != x || nextY != y) {
							len--;
							isUsed[nextY][nextX] = false;
							nextX -= d[0];
							nextY -= d[1];
						}
						
						dfs(cellNum+1);
						break;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	n = Integer.parseInt(br.readLine());
        	
        	map = new int[n][n];
        	isUsed = new boolean[n][n];
        	cells = new ArrayList<>();
        	maxCount = 0;
        	sumLen = 0;
        	count = 0;
        	len = 0;
        	
        	for (int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		for (int j = 0; j < n; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        			
        			if (map[i][j] == 1) {
        				isUsed[i][j] = true;
        				cells.add(new int[] {j, i});
        			}
        		}
        	}
        	
        	dfs(0);
        	
        	System.out.println("#" + t + " " + sumLen);
        }

	}
}
