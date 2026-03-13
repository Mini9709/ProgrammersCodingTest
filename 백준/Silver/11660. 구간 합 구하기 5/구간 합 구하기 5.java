import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n+1][n+1];
		int[][] sumMap = new int[n+1][n+1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sumMap[i][j] = sumMap[i][j-1] + map[i][j] + sumMap[i-1][j] - sumMap[i-1][j-1];
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int startY = Integer.parseInt(st.nextToken());
			int startX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			
			System.out.println(sumMap[endY][endX] - sumMap[endY][startX-1] - sumMap[startY-1][endX] + sumMap[startY-1][startX-1]);
		}
	}
}
