import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			Set<int[]> set = new HashSet<>();
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				set.add(new int[] {Math.min(a, b), Math.max(a, b)});
			}
			
			int answer = 0;
			
			for (int i = 0; i < 1 << n; i++) {
				boolean isAble = true;
				
				for (int[] a : set) {
					if ((i & 1 << a[0]-1) > 0 && (i & 1 << a[1]-1) > 0) {
						isAble = false;
						break;
					}
				}
				
				if (isAble) {
					answer++;
				}
			}
			
			System.out.printf("#%d %d\n", t, answer);
		}
	}
}
