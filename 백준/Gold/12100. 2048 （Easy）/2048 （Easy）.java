import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int answer;
	static int[][] map;

	// 0 : 위로, 1 : 아래로, 2: 왼쪽으로, 3: 오른쪽으로
	public static void move(int a, int[][] temp) {
		Queue<Integer> q = new ArrayDeque<>();
		
		if (a == 0) {
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					if (temp[y][x] > 0) {
						q.add(temp[y][x]);
					}
					temp[y][x] = 0;
				}
				
				int prev = 0;
				int idx = 0;
				
				while (!q.isEmpty()) {
					int curr = q.poll();
					
					if (prev == 0) {
						prev = curr;
						temp[idx][x] = prev;
					} else {
						if (prev == curr) {
							prev = 0;
							temp[idx++][x] *= 2;
						} else {
							prev = curr;
							temp[++idx][x] = prev;
						}
					}
				}
			}
		} else if (a == 1) {
			for (int x = 0; x < n; x++) {
				for (int y = n-1; y >= 0; y--) {
					if (temp[y][x] > 0) {
						q.add(temp[y][x]);
					}
					temp[y][x] = 0;
				}
				
				int prev = 0;
				int idx = n-1;
				
				while (!q.isEmpty()) {
					int curr = q.poll();
					
					if (prev == 0) {
						prev = curr;
						temp[idx][x] = prev;
					} else {
						if (prev == curr) {
							prev = 0;
							temp[idx--][x] *= 2;
						} else {
							prev = curr;
							temp[--idx][x] = prev;
						}
					}
				}
			}
		} else if (a == 2) {
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < n; x++) {
					if (temp[y][x] > 0) {
						q.add(temp[y][x]);
					}
					temp[y][x] = 0;
				}
				
				int prev = 0;
				int idx = 0;
				
				while (!q.isEmpty()) {
					int curr = q.poll();
					
					if (prev == 0) {
						prev = curr;
						temp[y][idx] = prev;
					} else {
						if (prev == curr) {
							prev = 0;
							temp[y][idx++] *= 2;
						} else {
							prev = curr;
							temp[y][++idx] = prev;
						}
					}
				}
			}
		} else {
			for (int y = 0; y < n; y++) {
				for (int x = n-1; x >= 0; x--) {
					if (temp[y][x] > 0) {
						q.add(temp[y][x]);
					}
					temp[y][x] = 0;
				}
				
				int prev = 0;
				int idx = n-1;
				
				while (!q.isEmpty()) {
					int curr = q.poll();
					
					if (prev == 0) {
						prev = curr;
						temp[y][idx] = prev;
					} else {
						if (prev == curr) {
							prev = 0;
							temp[y][idx--] *= 2;
						} else {
							prev = curr;
							temp[y][--idx] = prev;
						}
					}
				}
			}
		}
	}
			
	public static void dfs(int c, int[] seq) {
		if (c == 5) {
			int[][] temp = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					temp[i][j] = map[i][j];
				}
			}
			
			for (int s : seq) {
				move(s, temp);
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					answer = Math.max(answer, temp[i][j]);
				}
			}
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			seq[c] = i;
			dfs(c+1, seq);
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		answer = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				answer = Math.max(answer, map[i][j]);
			}
		}
		
		int[] seq = new int[5];
		
		dfs(0, seq);
		
		System.out.println(answer);
	}
}
