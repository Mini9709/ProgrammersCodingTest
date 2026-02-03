import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] fluid = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			fluid[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(fluid);
		
		int s = 0;
		int e = n-1;
		int answer = fluid[s] + fluid[e];
		int answerS = fluid[0];
		int answerE = fluid[n-1];
		
		while (s < e) {
			
			if (Math.abs(answer) > Math.abs(fluid[s] + fluid[e])) {
				answer = fluid[s] + fluid[e];
				answerS = fluid[s];
				answerE = fluid[e];
			}
			
			if (fluid[s] + fluid[e] > 0) {
				e--;
			} else if (fluid[s] + fluid[e] < 0) {
				s++;
			} else {
				s++;
				e--;
			}
		}
		
		System.out.printf("%d %d\n", answerS, answerE);
	}

}
