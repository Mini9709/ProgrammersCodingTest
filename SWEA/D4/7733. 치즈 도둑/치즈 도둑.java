import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int[][] map;
	static int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String args[]) throws Exception {
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

			int answer = 0;

			for (int i = 0; i <= 100; i++) {
				Queue<int[]> q = new ArrayDeque<>();
				boolean[][] visited = new boolean[n][n];
				int temp = 0;

				for (int y = 0; y < n; y++) {
					for (int x = 0; x < n; x++) {
						if (!visited[y][x] && map[y][x] > i) {
							q.add(new int[] { x, y });
							visited[y][x] = true;

							while (!q.isEmpty()) {
								int[] curr = q.poll();

								int cx = curr[0];
								int cy = curr[1];

								for (int[] d : directions) {
									int nx = cx + d[0];
									int ny = cy + d[1];

									if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[ny][nx] && map[ny][nx] > i) {
										q.add(new int[] { nx, ny });
										visited[ny][nx] = true;
									}
								}

							}

							temp++;
						}
					}
				}

				answer = Math.max(answer, temp);
			}

			System.out.println("#" + t + " " + answer);
		}

	}
}