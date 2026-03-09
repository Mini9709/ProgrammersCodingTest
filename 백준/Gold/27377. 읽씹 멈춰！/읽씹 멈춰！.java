import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long n;
	static long s;
	static long t;
	static long answer;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < testCase; tc++) {
			
			n = Long.parseLong(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			
			s = Long.parseLong(st.nextToken());
			t = Long.parseLong(st.nextToken());
			
			answer = 0;
			long temp = t / s;
			
			while (n / 2 > temp) {
				if (n % 2 == 1) {
					n -= 1;
					answer += s;
				} else {
					n /= 2;
					answer += t;
				}
			}
			
			answer += n * s;
			
			System.out.println(answer);
		}
		
	}

}
