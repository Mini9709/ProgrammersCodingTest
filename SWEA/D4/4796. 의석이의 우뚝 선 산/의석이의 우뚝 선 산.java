import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Scanner;

/*
 * 문제 : 의석이의 우뚝 선 산 (BOJ 4796)
 * 메모리 : 101,896 kb
 * 실행시간 : 654 ms
 * 시간 복잡도 : O(n)
 * 아이디어 :
 * 	앞에서부터 다음 산과의 높이를 확인하여 우뚝 선 산 구간을 체크
 */

public class Solution {

	public static void main(String[] args) throws IOException {

		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		
		st.nextToken();
		int testCase = (int) st.nval;
		
		for (int t = 1; t <= testCase; t++) {
			st.nextToken();
			int n = (int) st.nval;
			int answer = 0;
			
			int[] mountains = new int[n];
			for (int i = 0; i < n; i++) {
				st.nextToken();
				mountains[i] = (int) st.nval;
			}
				
			/*
			 * 1. for문 시작
			 * 2. 최대로 긴 우뚝 선 산 구간을 계산
			 * 3. 우뚝 선 산 구간의 왼쪽 산 개수 * 오른쪽 산 개수 = 해당 구간의 우뚝 선 산 구간 개수
			 * 4. for문 종료 후 answer값 리턴
			 */
			
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
