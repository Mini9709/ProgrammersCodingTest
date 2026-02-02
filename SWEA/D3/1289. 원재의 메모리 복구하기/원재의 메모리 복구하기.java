import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			String str = br.readLine();
			
			char defaultNum = '1';
			int answer = 0;
			for (int i = 0; i < str.length(); i++) {
				
				if (str.charAt(i) == defaultNum) {
					answer += 1;
					if (defaultNum == '1') {
						defaultNum = '0';
					} else {
						defaultNum = '1';
					}
					
				}
			}
			
			System.out.printf("#%d %d\n", t, answer);
		}
	}
}