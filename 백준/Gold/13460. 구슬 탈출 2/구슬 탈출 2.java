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
	static char[][] map; // 벽 : -1, 빈 공간 : 0, 빨간 공 : 1, 파란 공 : 2
	static int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
				
				if (map[i][j] == 'R') {
					rx = j;
					ry = i;
					map[i][j] = '.';
				}
				
				else if (map[i][j] == 'B') {
					bx = j;
					by = i;
					map[i][j] = '.';
				}
			}
		}
		
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][][][] visited = new boolean[n][m][n][m];
		q.add(new int[] {rx, ry, bx, by});
		visited[ry][rx][by][bx] = true;
	
		int time = 1;
		answer = -1;
		
		while (time <= 10) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				int[] curr = q.poll();
				int x1 = curr[0];
				int y1 = curr[1];
				int x2 = curr[2];
				int y2 = curr[3];
				
				for (int[] d : directions) {
					int i1 = 1;
					int i2 = 1;
					int nx1 = 0; int ny1 = 0; int nx2 = 0; int ny2 = 0;
					
					// 빨간 공 배치
					while (true) {
						nx1 = x1 + i1*d[0];
						ny1 = y1 + i1*d[1];
						
						if (nx1 >= 0 && nx1 < m && ny1 >= 0 && ny1 < n) {
							if (map[ny1][nx1] == '#') {
								nx1 = nx1 - d[0];
								ny1 = ny1 - d[1];
								break;
							} else if (map[ny1][nx1] == 'O') {
								nx1 = -1;
								ny1 = -1;
								break;
							}
							
							i1++;
						} else {
							break;
						}
					}

					// 파란 공 배치
					while (true) {
						nx2 = x2 + i2*d[0];
						ny2 = y2 + i2*d[1];
						
						if (nx2 >= 0 && nx2 < m && ny2 >= 0 && ny2 < n) {
							if (map[ny2][nx2] == '#') {
								nx2 = nx2 - d[0];
								ny2 = ny2 - d[1];
								break;
							} else if (map[ny2][nx2] == 'O') {
								nx2 = -1;
								ny2 = -1;
								break;
							}
							
							i2++;
						} else {
							break;
						}
					}
					
					// 빨간 공과 파란 공에 따른 상황 설정
					if (nx2 == -1 && ny2 == -1) {
						continue;
					} else if (nx1 == -1 && ny1 == -1) {
						answer = time;
						break;
					} else if (nx1 == nx2 && ny1 == ny2) {
						if (i1 > i2) {
							nx1 -= d[0];
							ny1 -= d[1];
						} else {
							nx2 -= d[0];
							ny2 -= d[1];
						}
					}
					
					// 설정 후 방문여부 확인
					if (!visited[ny1][nx1][ny2][nx2]) {
						visited[ny1][nx1][ny2][nx2] = true;
						q.add(new int[] {nx1, ny1, nx2, ny2});
					}
				}
			}

			// 위치 선정 후 정답일 경우
			if (answer != -1) {
				break;
			}
			
			time++;
		}
		
		System.out.println(answer);
	}
}
