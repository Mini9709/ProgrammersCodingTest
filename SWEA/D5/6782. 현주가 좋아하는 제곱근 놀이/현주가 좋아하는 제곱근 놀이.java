import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
        	
        	long n = Long.parseLong(br.readLine());
        	
        	int answer = 0;
        	
        	while (n > 2) {
        		long nSqrt = (long) Math.sqrt(n);
        		
        		if (n == Math.pow(nSqrt, 2)) {
        			answer += 1;
        			n = nSqrt;
        		} else {
        			answer += Math.pow(nSqrt+1, 2) - n;
        			answer += 1;
        			n = nSqrt+1;
        		}
        	}
        	
        	System.out.println("#" + t + " " + answer);
        }
	}
}