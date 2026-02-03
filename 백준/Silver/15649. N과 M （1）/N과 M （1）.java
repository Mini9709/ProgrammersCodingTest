import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	
	public static void makeArr(int num, int size, int[] arr, boolean[] checkNum) {
		
		arr[size] = num;
		checkNum[num] = true;
		
		if (size == m-1) {
			for (int a : arr) {
				System.out.print(a + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = 1; i <= n; i++) {
			if (!checkNum[i]) {
				makeArr(i, size + 1, arr, checkNum);
				checkNum[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= n; i++) {
			int[] arr = new int[m];
			boolean[] checkNum = new boolean[n+1];
			makeArr(i, 0, arr, checkNum);
		}
	}
}
