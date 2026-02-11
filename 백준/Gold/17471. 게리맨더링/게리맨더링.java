import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] people;
	static int[] totalPeople;
	static boolean[][] lines;
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        people = new int[n+1];
        totalPeople = new int[1<<n];
        lines = new boolean[n+1][n+1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
        	people[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 1; i <= n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int c = Integer.parseInt(st.nextToken());
        	
        	for (int j = 0; j < c; j++) {
        		lines[i][Integer.parseInt(st.nextToken())] = true;
        	}
        }
        
        for (int i = 1; i < (1 << n)-1; i++) {
    		Queue<Integer> area = new ArrayDeque<>();
    		
    		int sum = 0;
    		boolean finish = false;
    		boolean[] visited = new boolean[n+1];
    		
    		for (int j = 1; j <= n; j++) {
    			if ((i & 1 << j-1) > 0 && !visited[j]) {
    				if (!finish) {

    					area.add(j);
    					visited[j] = true;
						sum += people[j];
        				// bfs 돌리고 해당되는 비트가 있을 때 queue 삽입 반복
        				// bfs 완료 후 종료되지 않으면 -1 리턴 아닐 경우 합 리턴
        				
        				while(!area.isEmpty()) {
        					Integer curr = area.poll();
        					
        					for (int k = 1; k <= n; k++) {
        						if ((i & 1 << k-1) > 0 && lines[curr][k] && !visited[k]) {
        							visited[k] = true;
        							sum += people[k];
        							area.add(k);
        						}
        					}
        				}
        				
        				finish = true;
    				} else {
    					sum = -1;
    				}
    			}
    		}
    		
    		totalPeople[i] = sum;	
        }
        
        int answer = -1;
        
        for (int i = 1; i < (1 << n-1); i++) {
        	if (totalPeople[i] != -1 && totalPeople[(1 << n)-1-i] != -1) {
        		if (answer == -1) {
        			answer = Math.abs(totalPeople[i] - totalPeople[(1 << n)-1-i]);
        		} else {
        			answer = Math.min(answer, Math.abs(totalPeople[i] - totalPeople[(1 << n)-1-i]));
        		}
        	}
        }
        
        System.out.println(answer);

	}

}
