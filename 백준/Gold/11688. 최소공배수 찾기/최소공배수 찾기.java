import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static long a;
	static long b;
	static long l;
	static long answer;
	static ArrayList<Long> factors;
	
	public static long calculate(long a, long b) {
		if (a == 0 || b == 0) {
			return Math.max(a, b);
		} else {
			
			long minNum = Math.min(a, b);
			long maxNum = Math.max(a, b);
			
			return calculate(minNum, maxNum%minNum);
		}
	}
	
	public static void searchFactor(long l) {
		
		for (int i = 1; i <= Math.sqrt(l); i++) {
			if (l % i == 0) {
				factors.add((long) i);
				factors.add((long) l/i);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		a = Long.parseLong(st.nextToken());
		b = Long.parseLong(st.nextToken());
		l = Long.parseLong(st.nextToken());
		factors = new ArrayList<>();
		
		answer = -1;
		
		long rcd = calculate(a, b);
		long lcd = a * b / rcd;
		
		if (l % lcd == 0) {
			answer = l / lcd;
			searchFactor(lcd);
			long temp = 1;
			
			for (long f : factors) {
				if (calculate(f, answer) == 1) {
					temp = Math.max(temp, f);
				}
			}
			
			answer *= lcd / temp;
		}
		
		System.out.println(answer);
	}

}

