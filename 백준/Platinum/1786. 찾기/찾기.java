import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static String str;
	static String pattern;
	static int answer;
	static int[] pi;
	static int[] strPi;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str = br.readLine();
		pattern = br.readLine();
		
		pi = new int[pattern.length()];
		strPi = new int[str.length()];

		int j = 0;
		
		for (int i = 1; i < pattern.length(); i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			
			if (pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}

		j = 0;
		answer = 0;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			if (str.charAt(i) == pattern.charAt(j)) {
				strPi[i] = ++j;
			}
			if (j == pattern.length()) {
				answer++;
				sb.append(i-j+2).append(" ");
				j = pi[j-1];
			}
		}       

		System.out.println(answer);
		System.out.println(sb);
	}

}
