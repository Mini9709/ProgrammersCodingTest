import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int[][] positions;
	static int[] select;
	static long resX;
	static long resY;
	static long answer;
	
	public static void calculate(int depth, int plus, int minus) {
		if (depth == n && plus == n/2 && plus == n/2) {
			long vector = resX*resX + resY*resY;
			answer = Math.min(answer, vector);
			
			return;
		}
		
		if (plus < n/2) {
			resX += positions[depth][0];
			resY += positions[depth][1];
			calculate(depth+1, plus+1, minus);
			resX -= positions[depth][0];
			resY -= positions[depth][1];
		}
		
		if (minus < n/2) {
			resX -= positions[depth][0];
			resY -= positions[depth][1];
			calculate(depth+1, plus, minus+1);
			resX += positions[depth][0];
			resY += positions[depth][1];
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			n = Integer.parseInt(br.readLine());
			positions = new int[n][2];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				positions[i][0] = Integer.parseInt(st.nextToken());
				positions[i][1] = Integer.parseInt(st.nextToken());
			}
			
			answer = Long.MAX_VALUE;
			resX = 0;
			resY = 0;
			
			calculate(0, 0, 0);
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(answer);
			
			System.out.println(sb.toString());
		}

	}

}
