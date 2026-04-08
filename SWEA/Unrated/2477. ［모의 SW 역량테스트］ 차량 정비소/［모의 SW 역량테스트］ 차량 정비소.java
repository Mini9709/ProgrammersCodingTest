import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int m;
	static int k;
	static int a;
	static int b;
	static int answer;
	static int[] timeToRecept;
	static int[] timeToRepair;
	static int[] customers;
	static int[][] receptionDesk;
	static int[][] repairDesk;
	static int[][] usedDesk;
	
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			timeToRecept = new int[n+1];
			timeToRepair = new int[m+1];
			customers = new int[k+1];
			answer = 0;
			
			PriorityQueue<int[]> pq1 = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0])); // 접수 창고 대기[고객 인덱스, 도착시간]
			PriorityQueue<int[]> pq2 = new PriorityQueue<>((o1, o2) -> o1[2] != o2[2] ? Integer.compare(o1[2], o2[2]) : Integer.compare(o1[1], o2[1])); // 정비 창고 대기[고객 인덱스, 도착시간]
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				timeToRecept[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++) {
				timeToRepair[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= k; i++) {
				customers[i] = Integer.parseInt(st.nextToken());
				pq1.add(new int[] {i, customers[i]});
			}
			
			int time = 0;
			int count = 0;
			receptionDesk = new int[n+1][2]; // 접수중인 창구[고객 인덱스, 남은 이용시간]
			repairDesk = new int[m+1][2]; // 정비중인 창구[고객 인덱스, 남은 이용시간]
			usedDesk = new int[k+1][2]; // 고객이 사용한 창구[접수창고 인덱스, 정비창고 인덱스]
			
			while (!pq1.isEmpty() || !pq2.isEmpty() || count > 0) {
				for (int i = 1; i <= n; i++) {
					if (receptionDesk[i][1] > 0) {
						receptionDesk[i][1]--;
						if (receptionDesk[i][1] == 0) {
							pq2.add(new int[] {receptionDesk[i][0], i, time});
							count--;
						}
					}
					
					if (receptionDesk[i][1] == 0) {
						if (!pq1.isEmpty() && pq1.peek()[1] <= time) {
							int[] curr = pq1.poll();
							
							receptionDesk[i][0] = curr[0];
							receptionDesk[i][1] = timeToRecept[i];
							count++;
							
							usedDesk[curr[0]][0] = i;
						}
					}
				}
				
				for (int i = 1; i <= m; i++) {
					if (repairDesk[i][1] > 0) {
						repairDesk[i][1]--;
					}
					
					if (repairDesk[i][1] == 0) {
						if (!pq2.isEmpty()) {
							int[] curr = pq2.poll();
							
							repairDesk[i][0] = curr[0];
							repairDesk[i][1] = timeToRepair[i];
							
							usedDesk[curr[0]][1] = i;
						}
					}
				}
	
				time++;
			}
			
			for (int i = 1; i <= k; i++) {
				if (usedDesk[i][0] == a && usedDesk[i][1] == b) {
					answer += i;
				}
			}
			
			if (answer == 0) {
				answer = -1;
			}
			
			System.out.println("#"+ t + " " + answer);
		}

	}

}
