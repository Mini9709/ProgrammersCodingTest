import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String num1 = st.nextToken();
			String num2 = st.nextToken();
			
			Stack<Integer> stack1 = new Stack<>();
			Stack<Integer> stack2 = new Stack<>();
			
			for (int i = 0; i < num1.length(); i++) {
				stack1.add(num1.charAt(i) - '0');
			}
			
			for (int i = 0; i < num2.length(); i++) {
				stack2.add(num2.charAt(i) - '0');
			}
			
			int temp = 0;
			Stack<Integer> sum = new Stack<>();
			
			while(!stack1.isEmpty() && !stack2.isEmpty()) {
				temp += stack1.pop() + stack2.pop();
				sum.add(temp%10);
				temp = temp/10;
			}
			
			while (!stack1.isEmpty()) {
				temp += stack1.pop();
				sum.add(temp%10);
				temp = temp/10;
			}

			while (!stack2.isEmpty()) {
				temp += stack2.pop();
				sum.add(temp%10);
				temp = temp/10;
			}

			if (temp != 0) {
				sum.add(temp);
			}
			
			StringBuilder sb = new StringBuilder();
			while (!sum.isEmpty()) {
				sb.append(sum.pop());
			}
			
			System.out.printf("#%d %s\n", t, sb);
		}
	}
}