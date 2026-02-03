import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		int[][][] route = new int[n][m][2];
		int answer = 0;
		int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			
			for(int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		Queue<int[]> q = new ArrayDeque<>();
		route[0][0][0] = 1;
		q.add(new int[]{0, 0, 0});
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int currX = curr[0];
			int currY = curr[1];
			int breakWall = curr[2];
			
			for (int[] d : directions) {
				int nextX = currX + d[0];
				int nextY = currY + d[1];
				
				if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
					if (map[nextY][nextX] == 0) {
						if (route[nextY][nextX][breakWall] == 0) {
							route[nextY][nextX][breakWall] = route[currY][currX][breakWall] + 1;
							q.add(new int[] {nextX, nextY, breakWall});
						}
					} else {
						if (breakWall == 0 && route[nextY][nextX][breakWall] == 0) {
							route[nextY][nextX][breakWall+1] = route[currY][currX][breakWall] + 1;
							q.add(new int[] {nextX, nextY, breakWall+1});
						}
					}
				}
			}
		}
		
		if (route[n-1][m-1][0] > 0 && route[n-1][m-1][1] > 0) {
			answer = Math.min(route[n-1][m-1][0], route[n-1][m-1][1]);
			
		} else if (route[n-1][m-1][0] > 0) {
			answer = route[n-1][m-1][0];
		} else if (route[n-1][m-1][1] > 0) {
			answer = route[n-1][m-1][1];
		} else {
			answer = -1;
		}
		
		System.out.println(answer);
		
		br.close();
	}
}
