import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int k;
	static int[] nums;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			nums = new int[n];
			
			String str = br.readLine();
			
			for (int i = 0; i < n; i++) {
				char temp = str.charAt(i);
				
				switch (temp) {
					case 'A' : nums[i] = 10; break;
					case 'B' : nums[i] = 11; break;
					case 'C' : nums[i] = 12; break;
					case 'D' : nums[i] = 13; break;
					case 'E' : nums[i] = 14; break;
					case 'F' : nums[i] = 15; break;
					default : nums[i] = temp - '0';
				}
			}
			
			PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
			
			for (int i = 0; i < n/4; i++) {
				int count = 0;
				int sum = 0;
				
				for (int j = 0; j < n; j++) {
					int idx = i + j;
					
					if (idx >= n) {
						idx = idx - n;
					}
					
					if (count < n/4 - 1) {
						sum = sum * 16 + nums[idx];
						count++;
					} else {		
						sum = sum * 16 + nums[idx];
						pq.add(sum);
						count = 0;
						sum = 0;
					}
				}
			}
			
			int time = 0;
			int prev = -1;
			
			while(!pq.isEmpty() && time <= k) {
				int curr = pq.poll();
				
				if (prev != curr) {
					time++;
					prev = curr;
				}
				
				if (time == k) {
					System.out.println("#" + t + " " + curr);
				}
			}
		}
	}
}
