import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int s = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		
		String str = br.readLine();
		
		st = new StringTokenizer(br.readLine());
		int countA = Integer.parseInt(st.nextToken());
		int countC = Integer.parseInt(st.nextToken());
		int countG = Integer.parseInt(st.nextToken());
		int countT = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		int checkA = 0;
		int checkC = 0;
		int checkG = 0;
		int checkT = 0;
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'A') {
				checkA += 1;
			} else if (str.charAt(i) == 'C') {
				checkC += 1;
			} else if (str.charAt(i) == 'G') {
				checkG += 1;
			} else if (str.charAt(i) == 'T') {
				checkT += 1;
			}
			
			if (i >= p-1) {
				if (checkA >= countA && checkC >= countC && checkG >= countG && checkT >= countT) {
					answer += 1;
				}
				
				if (str.charAt(i-p+1) == 'A') {
					checkA -= 1;
				} else if (str.charAt(i-p+1) == 'C') {
					checkC -= 1;
				} else if (str.charAt(i-p+1) == 'G') {
					checkG -= 1;
				} else if (str.charAt(i-p+1) == 'T') {
					checkT -= 1;
				}
			}
		}
		
		System.out.println(answer);
	}

}
