import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int answer;
	static Set<Integer> set;
	static int[][] map;
	static int[][] directions = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
	static int[] countDirections;
	static boolean[][] visited;
	static boolean[] moved;
	
	
	// 4방향 중 하나 선택
	// 선택한 방향 진행 or 좌우로 꺾을지 선택
	// 선택한 방향으로 진행한 칸 수 저장
	// 꺾을 시 꺾은 방향으로 진행한 칸 수 저장
	// 2번째부턴 꺾을 땐 방향 고정
	// 저장한 칸 수 만큼 되돌아가기
	
	public static void move(int x, int y, int[][] d) {	
		int d1 = d[0][0];
		int d2 = d[1][0];
		int m1 = d[0][1];
		int m2 = d[1][1];
		int depth = 1;
		set.add(map[y][x]);

		for (int i = 0; i < m1; i++) {
			x += directions[d1][0];
			y += directions[d1][1];
			
			if (x >= 0 && x < n && y >= 0 && y < n) {
				set.add(map[y][x]);
				if (++depth != set.size()) {
					set.clear();
					return;
				}
			} else {
				set.clear();
				return;
			}
		}

		for (int i = 0; i < m2; i++) {
			x += directions[d2][0];
			y += directions[d2][1];
			
			if (x >= 0 && x < n && y >= 0 && y < n) {
				set.add(map[y][x]);
				if (++depth != set.size()) {
					set.clear();
					return;
				}
			} else {
				set.clear();
				return;
			}
		}
		
		for (int i = 0; i < m1; i++) {
			x -= directions[d1][0];
			y -= directions[d1][1];
			
			if (x >= 0 && x < n && y >= 0 && y < n) {
				set.add(map[y][x]);
				if (++depth != set.size()) {
					set.clear();
					return;
				}
			} else {
				set.clear();
				return;
			}
		}
		
		for (int i = 0; i < m2-1; i++) {
			x -= directions[d2][0];
			y -= directions[d2][1];
			
			if (x >= 0 && x < n && y >= 0 && y < n) {
				set.add(map[y][x]);
				if (++depth != set.size()) {
					set.clear();
					return;
				}
			} else {
				set.clear();
				return;
			}
		}
		
		answer = Math.max(answer, depth);
	}
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	n = Integer.parseInt(br.readLine());
        	map = new int[n][n];
        	
        	for (int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		for (int j = 0; j < n; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	answer = -1;
        	countDirections = new int[] {0, 0, 0, 0};
        	set = new HashSet<>();
        	for (int i = 0; i < n; i++) {
        		for (int j = 0; j < n; j++) {
        			for (int d1 = 1; d1 < n; d1++) {
        				for (int d2 = 1; d2 < n; d2++) {
        					move(j, i, new int[][] {{0 ,d1}, {2, d2}});
        					move(j, i, new int[][] {{0 ,d1}, {3, d2}});
        					move(j, i, new int[][] {{1 ,d1}, {2, d2}});
        					move(j, i, new int[][] {{1 ,d1}, {3, d2}});
        					move(j, i, new int[][] {{2 ,d1}, {0, d2}});
        					move(j, i, new int[][] {{2 ,d1}, {1, d2}});
        					move(j, i, new int[][] {{3 ,d1}, {0, d2}});
        					move(j, i, new int[][] {{3 ,d1}, {1, d2}});
        				}
        			}
        		}
        	}
        	
        	System.out.println("#" + t + " " + answer);
        }

	}

}
