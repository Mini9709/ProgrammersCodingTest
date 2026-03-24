import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int n;
	static int[] pi;
	static String str;

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		str = br.readLine();
		pi = new int[n];
		
		int j = 0;
		for (int i = 1; i < n; i++) {
			while (j > 0 && str.charAt(i) != str.charAt(j)) {
				j = pi[j-1];
			}
			
			if (str.charAt(i) == str.charAt(j)) {
				j += 1;
				pi[i] = j;
			}
		}
		
		System.out.println(n - pi[n-1]);

	}
}