import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long minNum;
	static long maxNum;
	static int idx;
	
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        minNum = Long.parseLong(st.nextToken());
        maxNum = Long.parseLong(st.nextToken());
        idx = (int) (maxNum-minNum+1);
        boolean[] nums = new boolean[idx];
        
        for (long i = 2; i <= Math.sqrt(maxNum); i++) {
        	long square = i * i;
        	
        	long start = minNum / square;
        	if (minNum % square != 0) {
        		start++;
        	}
        	
        	for (long j = start; j * square <= maxNum; j++) {
        		nums[(int) (j * square - minNum)] = true;
        	}
        }
        
        int answer = 0;
        
        for (int i = 0; i < idx; i++) {
        	if (!nums[i]) {
        		answer++;
        	}
        }
        
        System.out.println(answer);
	}
    
}