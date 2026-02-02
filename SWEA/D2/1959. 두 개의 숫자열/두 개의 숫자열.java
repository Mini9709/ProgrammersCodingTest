import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;


public class Solution
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= T; i++) {
			
			int answer = 0;
			
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int min = Math.min(n, m);
			int max = Math.max(n, m);
			int[]min_num, max_num;
			
			int[] ai = new int[n];
			int[] bj = new int[m];
			
			st = new StringTokenizer(br.readLine());
			for (int a = 0; a < n; a++) {
				ai[a] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int b = 0; b < m; b++) {
				bj[b] = Integer.parseInt(st.nextToken());
			}
			
			if (min == n) {
				min_num = ai;
				max_num = bj;
			} else {
				min_num = bj;
				max_num = ai;
			}
			
			for(int j = 0; j <= max-min; j++) {
				int sum = 0;
				for (int k = 0; k < min; k++) {
					sum += (min_num[k] * max_num[k+j]);
				}
				answer = Math.max(answer, sum);
			}
			
			System.out.println("#"+i+" "+answer);
		}
	}
}