import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] paper = new int[101][101];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int endX = startX + 10;
			int endY = startY + 10;
			
			for (int y = startY; y < endY; y++) {
				for (int x = startX; x < endX; x++) {
					if (paper[y][x] == 0) {
						paper[y][x] = 1;
					}
				}
			}
		}
		
		int answer = 0;
		
		for (int i = 0; i < paper.length; i++) {
			for (int j = 0; j < paper[0].length; j++) {
				answer += paper[i][j];
			}
		}
		
		System.out.println(answer);
		
	}
}
