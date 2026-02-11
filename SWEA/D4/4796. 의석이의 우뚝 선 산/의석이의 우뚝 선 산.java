import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		int testCase = in.nextInt();
		
		for (int t = 1; t <= testCase; t++) {
			int n = in.nextInt();
			int answer = 0;
			
			int[] mountains = new int[n];
			for (int i = 0; i < n; i++) {
				mountains[i] = in.nextInt();
			}
			
			
			// 앞에서부터 for문 시작
			// 올라가면 plus++
			// 내려가면 minus--
			// 다시 올라가면 초기화 후 plus++
			// 초기화 전 answer += plus * minus
			int plus = 0;
			int minus = 0;
			int temp = mountains[0];
			
			for (int i = 1; i < n; i++) {
				if (temp < mountains[i]) {
					if (minus == 0) {
						plus++;
					} else {
						answer += plus * minus;
						plus = 1;
						minus = 0;
					}
				} else {
					if (plus > 0) {
						minus++;
					}
				}
				
				temp = mountains[i];
			}
			
			if (plus > 0 && minus > 0) {
				answer += plus * minus;
			}
			
			System.out.println("#" + t + " " + answer);
			
		}
	}

}
