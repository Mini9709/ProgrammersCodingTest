import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int len;
	static int[] nums;
	static char[] oper;
	static int answer;
	
	public static void calNums(int n1, char c1, int n2, char c2, int n3, int endIndex) {
		if (endIndex == nums.length-1) {
			int case1 = calculate(calculate(n1, c1, n2), c2, n3);
			int case2 = calculate(n1, c1, calculate(n2, c2, n3));
			answer = Math.max(answer, case1);
			answer = Math.max(answer, case2);
			return;
		}
		
		calNums(calculate(n1, c1, n2), c2, n3, oper[endIndex], nums[endIndex+1], endIndex+1);
		
		int temp = calculate(n1, c1, calculate(n2, c2, n3));
		if (endIndex == nums.length-2) {
			int case3 = calculate(temp, oper[endIndex], nums[endIndex+1]);
			answer = Math.max(answer, case3);
			return;
		} else {
			calNums(temp, oper[endIndex], nums[endIndex+1], oper[endIndex+1], nums[endIndex+2], endIndex+2);
		}
	}

	public static int calculate(int a, char c, int b) {
		if (c == '+') {
			return a + b;
		} else if (c == '-') {
			return a - b;
		} else {
			return a * b;
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		len = Integer.parseInt(br.readLine());
		
		String calStr = br.readLine();
		nums = new int[len/2+1];
		oper = new char[len/2];
		answer = Integer.MIN_VALUE;
		
		for (int i = 0; i < len; i++) {
			if (i % 2 == 0) {
				nums[i/2] = calStr.charAt(i) - '0';
			} else {
				oper[i/2] = calStr.charAt(i);
			}
		}
		
		if(len == 1) {
			answer = nums[0];
		} else if (len == 3) {
			answer = calculate(nums[0], oper[0], nums[1]);
		} else {
			calNums(nums[0], oper[0], nums[1], oper[1], nums[2], 2);
		}	
		
		System.out.println(answer);
	}
}
