import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int maxNum;
	static int[] nums;
	static int[] res;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		maxNum = 0;
		nums = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			maxNum = Math.max(maxNum, nums[i]);
		}
		
		res = new int[maxNum+1];
				
		for (int num = 2; num <= maxNum; num++) {
			if (res[num] == 0) {
				res[num] = num;
				int temp = num * 2;
				
				while (temp <= maxNum) {
					if (res[temp] == 0) {
						res[temp] = num;
					}
					temp += num;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int a : nums) {
			int num = a;
			
			while (num > 1) {
				sb.append(res[num] + " ");
				num /= res[num];
			}
			
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

}
