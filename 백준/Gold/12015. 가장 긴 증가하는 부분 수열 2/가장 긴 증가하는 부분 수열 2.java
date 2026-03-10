import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] numList;
	static int[] sequance;
	static int answer;
	
	public static void binarySearch(int start, int end, int num) {
		if (start == end) {
			sequance[start] = num;
			return;
		}
		
		int mid = (start + end) / 2;
		if (sequance[mid] > num) {
			binarySearch(start, mid, num);
		} else if (sequance[mid] < num){
			binarySearch(mid+1, end, num);
		} else {
			return;
		}
	}
	
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        numList = new int[n];
        sequance = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
        	numList[i] = Integer.parseInt(st.nextToken());
        }
        
        int idx = 0;
        sequance[idx] = numList[0];
        for (int i = 1; i < n; i++) {
        	if (sequance[idx] < numList[i]) {
        		sequance[++idx] = numList[i];
        	} else {
        		binarySearch(0, idx, numList[i]);
        	}
        }
        
        System.out.println(idx+1);
	}

}