import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int m;
	static int r;
	static int c;
	static int l;
	static int answer;
	static int[][] map;
	static boolean[][] visited;
	static int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // 상 하 좌 우

	// 상황에 따라 움직일 수 있는 방향을 리턴하는 메서드
	public static int[][] move(int a) {
		
		switch (a) {
		
		case 1 : 
			return directions;
		case 2 :
			return new int[][] {directions[0], directions[1]};
		case 3 :
			return new int[][] {directions[2], directions[3]};
		case 4 :
			return new int[][] {directions[0], directions[3]};
		case 5 :
			return new int[][] {directions[1], directions[3]};
		case 6 :
			return new int[][] {directions[1], directions[2]};
		case 7 :
			return new int[][] {directions[0], directions[2]};
			
		default :
			return null;
		}
	}
	
	// 움직이는 방향의 터널 입구가 연결되어있는지 확인하는 메서드
	public static boolean canMove(int[] directions, int b) {
		int x = directions[0];
		int y = directions[1];
		
		if (x == 0 && y == -1) { // 상
			if (b == 1 || b == 2 || b == 5 || b == 6) {
				return true;
			} else {
				return false;
			}
		} else if (x == 0 && y == 1) { // 하
			if (b == 1 || b == 2 || b == 4 || b == 7) {
				return true;
			} else {
				return false;
			}
		} else if (x == -1 && y == 0) { // 좌
			if (b == 1 || b == 3 || b == 4 || b == 5) {
				return true;
			} else {
				return false;
			}
		} else { // 우
			if (b == 1 || b == 3 || b == 6 || b == 7) {
				return true;
			} else {
				return false;
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	st = new StringTokenizer(br.readLine());
        	
        	n = Integer.parseInt(st.nextToken());
        	m = Integer.parseInt(st.nextToken());
        	r = Integer.parseInt(st.nextToken());
        	c = Integer.parseInt(st.nextToken());
        	l = Integer.parseInt(st.nextToken());
        	
        	map = new int[n][m];
        	
        	for (int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		for (int j = 0; j < m; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	int time = 1;
        	
        	Queue<int[]> q = new ArrayDeque<>();
        	visited = new boolean[n][m];
        	q.add(new int[] {r, c});
        	answer = 1;
        	visited[r][c] = true;
        	
        	while (time < l) {
        		int size = q.size();
        		for (int i = 0; i < size; i++) {
        			int[] curr = q.poll();
        			
        			int r = curr[0];
        			int c = curr[1];
        			
        			for (int[] d : move(map[r][c])) {
        				int nr = r + d[1];
        				int nc = c + d[0];
        				
        				if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && canMove(d, map[nr][nc])) {
        					q.add(new int[] {nr, nc});
        					answer++;
        					visited[nr][nc] = true;
        				}
        			}
        			
        			
        		}
        		time++;
        	}
        	
        	System.out.println("#" + t + " " + answer);
        }

	}

}
