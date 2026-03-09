import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static String str;
	static char[] texts;
	static int[] isUsed;
	static int[] answer;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	str = br.readLine().toLowerCase();
        	String temp = br.readLine().toLowerCase();
        	
        	texts = new char[temp.length()+1];
        	isUsed = new int[str.length()];
        	answer = new int[4];
        	
        	for (int i = 1; i < texts.length; i++) {
        		texts[i] = temp.charAt(i-1);
        	}
        	
        	int idx = 1;
        	for (int i = 1; i < texts.length; i++) {
        		for (int j = 0; j < isUsed.length; j++) {
        			if (texts[i] == str.charAt(j)) {
        				if (j == 0 || (j > 0 && isUsed[j-1] > isUsed[j])) {
       						isUsed[j]++;
       						
       						if (j == isUsed.length-1 && idx < 4) {
       							answer[idx++] = i;
       						}
       						
       						break;
        				}
        			}
        		}
        	}
        	
        	answer[0] = isUsed[isUsed.length-1];
        	System.out.print(answer[0] + " ");
        	for (int i = 1; i <= Math.min(answer[0], 3); i++) {
        		
        		System.out.print(answer[i] + " ");
        	}
        	System.out.println();
        }
	}
}
