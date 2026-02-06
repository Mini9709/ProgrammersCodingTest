import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 문제 : 보급로(SWEA 1249)
 * 메모리 : 
 * 실행시간 : 
 * 아이디어 :
 * 	bfs를 이용하여 각 지역의 자원을 맵에 기록하고 최소값을 갱신하여 목표 지점의 최소값 산출
 */

public class Solution {
	
	static int n;
	static int[][] map;
	static int[][] move;
	static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			n = Integer.parseInt(br.readLine());;
			
			map = new int[n][n];
			move = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				String temp = br.readLine();
				
				for (int j = 0; j < n; j++) {
					map[i][j] = temp.charAt(j) - '0';
					move[i][j] = -1;
				}
			}
			
			Queue<int[]> queue = new ArrayDeque<>();
			move[0][0] = 0;
			queue.add(new int[] {0, 0});
			
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				int currX = curr[0];
				int currY = curr[1];
				
				for (int[] d : directions) {
					int nextX = currX + d[0];
					int nextY = currY + d[1];
					
					if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
						int rsc = map[nextY][nextX] + move[currY][currX];
						
						if (move[nextY][nextX] == -1 || move[nextY][nextX] > rsc) {
							move[nextY][nextX] = rsc;
							queue.add(new int[] {nextX, nextY});
						}
					}
				}
			}
			
			System.out.printf("#%d %d\n", t, move[n-1][n-1]);
		}
	}

}
