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
		
		int[] nums = new int[n+1];
		int[] sumNums = new int[n+1];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			sumNums[i] = sumNums[i-1] + nums[i];
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			System.out.println(sumNums[y] - sumNums[x-1]);
		}
	}
}
