import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int w;
	static int h;
	static int answer;
	static int[][] map;

	static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void play(int c, int[] seq) {
		
		if (c == n) {
			int[][] temp = new int[h][w];
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					temp[i][j] = map[i][j];
				}
			}
			
			for (int x : seq) {
				breakBrick(x, temp);
				moveBrick(temp);
			}
			
			int res = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (temp[i][j] > 0) {
						res++;
					}
				}
			}
			
			answer = Math.min(answer, res);
			return;
		}
		
		for (int i = 0; i < w; i++) {
			seq[c] = i;
			play(c+1, seq);
		}
	}

	public static void breakBrick(int j, int[][] afterMap) {
		
		Queue<int[]> q = new ArrayDeque<>();
		
		for (int i = 0; i < h; i++) {
			if (afterMap[i][j] > 0) {
				q.add(new int[] {j, i});
				break;
			}
		}
		
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int x = curr[0];
			int y = curr[1];
			int count = afterMap[y][x];
			afterMap[y][x] = 0;
			
			for (int[] d : directions) {
				for (int i = 1; i < count; i++) {
					int nx = x + d[0] * i;
					int ny = y + d[1] * i;
					
					if (nx >= 0 && nx < w && ny >= 0 && ny < h && afterMap[ny][nx] > 0) {
						if (afterMap[ny][nx] == 1) {
							afterMap[ny][nx] = 0;
						} else {
							q.add(new int[] {nx, ny});
						}
					}
				}
			}
		}
	}
	
	public static void moveBrick(int[][] afterMap) {
		
		for (int x = 0; x < w; x++) {
			int point = h-1;
			
			for (int y = h-1; y >= 0; y--) {
				if (afterMap[y][x] > 0) {
					afterMap[point][x] = afterMap[y][x];
					
					if (point != y) {
						afterMap[y][x] = 0;
					}
					
					point--;
				}
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
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			map = new int[h][w];
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = Integer.MAX_VALUE;
			int[] seq = new int[n];
			play(0, seq);
			
			System.out.println("#" + t + " " + answer);
		}

	}

}

