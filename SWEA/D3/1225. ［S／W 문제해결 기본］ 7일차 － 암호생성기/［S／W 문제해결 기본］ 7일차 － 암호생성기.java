import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			int testCase = Integer.parseInt(br.readLine());
			
			int[] numList = new int[8];
			StringTokenizer st = new StringTokenizer(br.readLine());
			Queue<Integer> q = new LinkedList<>();
			int minNum = Integer.MIN_VALUE;
			
			for (int i = 0; i < numList.length; i++) {
				numList[i] = Integer.parseInt(st.nextToken());
				minNum = Math.min(minNum, numList[i]);
			}
			
			while (minNum > 15) {
				for (int i : numList) {
					i -= 15;
				}
				minNum -= 15;
			}
			
			for (int i : numList) {
				q.add(i);
			}
			
			int subNum = 1;
			
			while (true) {
				int temp = q.poll(); 
				int num = temp - subNum > 0 ? temp - subNum : 0;
				
				q.add(num);
				
				if (num == 0) {
					break;
				}
				
				if(subNum == 5) {
					subNum = 1;
				} else {
					subNum++;
				}
			}
			
			System.out.printf("#%d ", testCase);
			while (!q.isEmpty()) {
				System.out.printf("%d ", q.poll());
			}
			
			System.out.println();
		}
	}
}