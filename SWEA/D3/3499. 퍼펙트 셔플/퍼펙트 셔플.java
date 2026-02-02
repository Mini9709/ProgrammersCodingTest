import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
class Solution
{
	
	static int n;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			n = Integer.parseInt(br.readLine());
			Stack<String> deck1 = new Stack<>();
			Stack<String> deck2 = new Stack<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int deck2_size = n/2;
			int deck1_size = n - deck2_size;
			
			for (int i = 0; i < deck1_size; i++) {
				deck1.add(st.nextToken());
			}
			
			for (int i = 0; i < deck2_size; i++) {
				deck2.add(st.nextToken());
			}
			
			Stack<String> merge = new Stack<>();
			for (int i = 0; i < n/2; i++) {
				if (deck1_size > deck2_size) {
					merge.add(deck1.pop());
					merge.add(deck2.pop());
				} else {
					merge.add(deck2.pop());
					merge.add(deck1.pop());
				}
			}
			
			if (!deck1.isEmpty()) {
				merge.add(deck1.pop());
			}
			
			
			System.out.printf("#%d ", t);
			while (!merge.isEmpty()) {
				System.out.printf("%s ", merge.pop());
			}
			System.out.println();
		}
	}
}