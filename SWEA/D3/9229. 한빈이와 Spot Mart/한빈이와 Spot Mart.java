import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> snacks = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < n; i++) {
				
				int snack = Integer.parseInt(st.nextToken());
				
				if (snack < m) {
					snacks.add(snack);
				}
				
			}
			
			int size = snacks.size();
			int answer = -1;
			
			for (int i = 0; i < size-1; i++) {
				for (int j = i+1; j < size; j++) {
					if (snacks.get(i) + snacks.get(j) <= m) {	
						answer = Math.max(answer, snacks.get(i) + snacks.get(j));
					}
				}
			}
			
			System.out.printf("#%d %d\n", t, answer);
		}
	}
}
