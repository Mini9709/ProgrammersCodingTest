import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] topMap = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());		
		
		for (int i = 1; i <= n; i++) {
			topMap[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] answer = new int[n+1];
		Stack<Integer> stack = new Stack<>();
		
		for (int i = n; i > 0; i--) {
			if(topMap[i] >= topMap[i-1]) {
				stack.push(i);
				
			} else {
				answer[i] = i-1;
				while (!stack.isEmpty()) {
					int temp = stack.pop();
					
					if (topMap[temp] >= topMap[i-1]) {
						stack.push(temp);
						break;
					} else {
						answer[temp] = i-1;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < answer.length; i++) {
			sb.append(answer[i] + " ");
		}
		
		System.out.println(sb);
	}
}