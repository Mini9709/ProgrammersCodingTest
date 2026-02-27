import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int rx;
	static int ry;
	static int bx;
	static int by;
	static int answer;
	static int[][] map; // 벽 : -1, 빈 공간 : 0, 빨간 공 : 1, 파란 공 : 2
	
	// 0 : 상, 1 : 하, 2 : 좌, 3 : 우
	public static void move(int mv, int[][] temp, int[][] p) {
		
		Queue<int[]> q = new ArrayDeque<>();
		
		if (mv == 0) {
			// 빨간 공, 파란 공 queue에 넣기 (먼저 수행할 공 순서 정하기)
			for (int y = 0; y < n; y++) {
				if (temp[y][p[0][0]] == 1 || temp[y][p[0][0]] == 2) {
					q.add(new int[] {p[0][0], y, temp[y][p[0][0]]}); // x, y, 공 색깔
					temp[y][p[0][0]] = '.';
				}
				
				if (temp[y][p[1][0]] == 1 || temp[y][p[1][0]] == 2) {
					q.add(new int[] {p[1][0], y, temp[y][p[1][0]]});
					temp[y][p[1][0]] = '.';
				}
			}
			
			// 공 움직이기
			while (!q.isEmpty()) {
				int[] curr = q.poll();
				
				for (int y = curr[1]; y >= 0; y--) {
					if (temp[y][curr[0]] == -1 || temp[y][curr[0]] == 1 || temp[y][curr[0]] == 2) {
						p[curr[2]-1][1] = y+1;
						temp[y+1][curr[0]] = curr[2];
						break;
					} else if (temp[y][curr[0]] == 3) {
						p[curr[2]-1][1] = -1;
						p[curr[2]-1][0] = -1;
						break;
					}
				}
			}
		} else if (mv == 1) {
			// 빨간 공, 파란 공 queue에 넣기 (먼저 수행할 공 순서 정하기)
			for (int y = n-1; y >= 0; y--) {
				if (temp[y][p[0][0]] == 1 || temp[y][p[0][0]] == 2) {
					q.add(new int[] {p[0][0], y, temp[y][p[0][0]]}); // x, y, 공 색깔
					temp[y][p[0][0]] = '.';
				}
				
				if (temp[y][p[1][0]] == 1 || temp[y][p[1][0]] == 2) {
					q.add(new int[] {p[1][0], y, temp[y][p[1][0]]});
					temp[y][p[1][0]] = '.';
				}
			}
			
			// 공 움직이기
			while (!q.isEmpty()) {
				int[] curr = q.poll();
				
				for (int y = curr[1]; y < n; y++) {
					if (temp[y][curr[0]] == -1 || temp[y][curr[0]] == 1 || temp[y][curr[0]] == 2) {
						p[curr[2]-1][1] = y-1;
						temp[y-1][curr[0]] = curr[2];
						break;
					} else if (temp[y][curr[0]] == 3) {
						p[curr[2]-1][1] = -1;
						p[curr[2]-1][0] = -1;
						break;
					}
				}
			}			
		} else if (mv == 2) {
			// 빨간 공, 파란 공 queue에 넣기 (먼저 수행할 공 순서 정하기)
			for (int x = 0; x < m; x++) {
				if (temp[p[0][1]][x] == 1 || temp[p[0][1]][x] == 2) {
					q.add(new int[] {x, p[0][1], temp[p[0][1]][x]}); // x, y, 공 색깔
					temp[p[0][1]][x] = '.';
				}
				
				if (temp[p[1][1]][x] == 1 || temp[p[1][1]][x] == 2) {
					q.add(new int[] {x, p[1][1], temp[p[1][1]][x]});
					temp[p[1][1]][x] = '.';
				}
			}
			
			// 공 움직이기
			while (!q.isEmpty()) {
				int[] curr = q.poll();
				
				for (int x = curr[0]; x >= 0; x--) {
					if (temp[curr[1]][x] == -1 || temp[curr[1]][x] == 1 || temp[curr[1]][x] == 2) {
						p[curr[2]-1][0] = x+1;
						temp[curr[1]][x+1] = curr[2];
						break;
					} else if (temp[curr[1]][x] == 3) {
						p[curr[2]-1][1] = -1;
						p[curr[2]-1][0] = -1;
						break;
					}
				}
			}
		} else {
			// 빨간 공, 파란 공 queue에 넣기 (먼저 수행할 공 순서 정하기)
			for (int x = m-1; x >= 0; x--) {
				if (temp[p[0][1]][x] == 1 || temp[p[0][1]][x] == 2) {
					q.add(new int[] {x, p[0][1], temp[p[0][1]][x]}); // x, y, 공 색깔
					temp[p[0][1]][x] = '.';
				}
				
				if (temp[p[1][1]][x] == 1 || temp[p[1][1]][x] == 2) {
					q.add(new int[] {x, p[1][1], temp[p[1][1]][x]});
					temp[p[1][1]][x] = '.';
				}
			}
			
			// 공 움직이기
			while (!q.isEmpty()) {
				int[] curr = q.poll();
				
				for (int x = curr[0]; x < m; x++) {
					if (temp[curr[1]][x] == -1 || temp[curr[1]][x] == 1 || temp[curr[1]][x] == 2) {
						p[curr[2]-1][0] = x-1;
						temp[curr[1]][x-1] = curr[2];
						break;
					} else if (temp[curr[1]][x] == 3) {
						p[curr[2]-1][1] = -1;
						p[curr[2]-1][0] = -1;
						break;
					}
				}
			}
		}
	}
	
	public static void dfs(int c, int[] seq) {
		if (c == 10) {
			int[][] temp = new int[n][m];
			int[][] point = {{rx, ry}, {bx, by}};
			int count = 1;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					temp[i][j] = map[i][j];
				}
			}
			
			for (int s : seq) {
				move(s, temp, point);
				if (point[1][0] == -1 && point[1][1] == -1) {
					return;
				} else if (point[0][0] == -1 && point[0][1] == -1) {
					answer = Math.min(answer, count);
					return;
				}
				
				count++;
			}
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (answer == 1) {
				return;
			}
			
			seq[c] = i;
			dfs(c+1, seq);
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			
			for (int j = 0; j < m; j++) {
				char c = str.charAt(j);
				
				if (c == 'R') {
					rx = j;
					ry = i;
					map[i][j] = 1;
				}
				
				else if (c == 'B') {
					bx = j;
					by = i;
					map[i][j] = 2;
				}
				
				else if (c == '#') {
					map[i][j] = -1;
				}
				
				else if (c == '.') {
					map[i][j] = 0;
				} else {
					map[i][j] = 3;
				}
			}

		}
		
		answer = Integer.MAX_VALUE;
		int[] seq = new int[10];
		
		dfs(0, seq);
		
		if (answer == Integer.MAX_VALUE) {
			answer = -1;
		}
		
		System.out.println(answer);
		
	}

}
