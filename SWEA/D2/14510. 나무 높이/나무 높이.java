import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			
			int n = Integer.parseInt(br.readLine());
			int[] tree = new int[n];
			int[] needWater = new int[n];
			int maxTree = 0;
			int sum = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				int temp = Integer.parseInt(st.nextToken());
				maxTree = Math.max(maxTree, temp);
				tree[i] = temp;
			}
			
			for (int i = 0; i < n; i++) {
				needWater[i] = maxTree - tree[i];
				sum += needWater[i];
			}
			
			Arrays.sort(needWater);
			
			int answer = 0;
			
			
			while (sum > 0) {			
				
				for (int i = 0; i < needWater.length; i++) {
					if (needWater[i] - (answer % 2 + 1) >= 0) {
						if (needWater[i] == 2 && sum == 2 && answer % 2 == 0) {
							continue;
						}	
						needWater[i] -= (answer % 2 + 1);
						sum -= (answer % 2 + 1);
						break;
					}
				}
				
				answer++;
			}
			
			System.out.printf("#%d %d\n", t, answer);
		}

	}

}