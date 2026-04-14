import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int k;
	static int c;
	static int[] items;
	static long[] sumItems;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		items = new int[n+1];
		sumItems = new long[n+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
			
		}
		
		Arrays.sort(items);
		
		for (int i = 1; i <= n; i++) {
			sumItems[i] = sumItems[i-1] + items[i];
		}
		
		int idx = 1;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= c; i++) {
			if (idx <= n && sumItems[idx] <= i) {
				idx++;
			}
			if (idx+k-1 <= n) {
				sb.append(sumItems[idx+k-1] - sumItems[idx-1]).append('\n');
			} else {
				sb.append(sumItems[n] - sumItems[n-k]).append('\n');
			}
			
		}
		
		System.out.println(sb.toString());
	}

}
