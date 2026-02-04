import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int answer;
	
	public static void bfs(int rain) {
		
		Queue<int[]> q = new ArrayDeque<>();
		int temp = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && map[i][j] - rain > 0) {
					q.offer(new int[] {i, j});
					visited[i][j] = true;
					
					while (!q.isEmpty()) {
						int[] curr = q.poll();
						int currI = curr[0];
						int currJ = curr[1];
						
						for (int[] d : directions) {
							int nextI = currI + d[0];
							int nextJ = currJ + d[1];
							
							if (nextI >= 0 && nextI < n && nextJ >= 0 && nextJ < n && !visited[nextI][nextJ] && map[nextI][nextJ] - rain > 0) {
								visited[nextI][nextJ] = true;
								q.offer(new int[] {nextI, nextJ});
							}
						}
					}
					
					temp++;	
				}
			}
		}
		
		answer = Math.max(answer, temp);
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		int maxHeight = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		}
		
		answer = 1;
		
		for (int i = 1; i <= maxHeight; i++) {
			if (i < maxHeight) {
				visited = new boolean[n][n];
				bfs(i);
			}
		}
		
		System.out.println(answer);
	}

}
