import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 : 정사각형 방 (SWEA 1861)
 * 메모리 : 102,012 kb
 * 실행시간 : 632 ms
 * 시간 복잡도 : n^3
 * 아이디어 :
 * 	위치마다 이동할 수 있는 경우를 체크해서 answer 리턴
 */

public class Solution {

	static int n;
	static int maxCount;
	static int answer;
	static int[][] map;
	static int[][] start;
	static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	n = Integer.parseInt(br.readLine());
        	map = new int[n][n];
        	start = new int[n*n+1][2];
        	
        	for (int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		for (int j = 0; j < n; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        			start[map[i][j]][0] = j;
        			start[map[i][j]][1] = i;
        		}
        	}
        	
        	maxCount = 0; // 가장 많은 이동 수
        	answer = Integer.MAX_VALUE; // 가장 많은 방문 수를 가진 지점
        	Queue<int[]> q = new ArrayDeque<>();
        	int idx = 1;
        	
        	while(idx <= n*n) {
        		int[] num = start[idx];
        		
        		int x = num[0];
        		int y = num[1];
        		q.add(new int[] {x, y});
        		int count = 0;
    			int temp = map[y][x];
    			
    			while (!q.isEmpty()) { // bfs 진행
					int[] curr = q.poll();
					int j = curr[0];
					int i = curr[1];
					
					for (int[] d : directions) {
						int nj = j + d[0];
						int ni = i + d[1];
						
						if (nj >= 0 && nj < n && ni >= 0 && ni < n && map[i][j]+1 == map[ni][nj]) {
							q.add(new int[] {nj, ni});
						}
					}
    				
    				count++;
    				idx++;
    			}
    			
    			if (maxCount == count) {
    				answer = Math.min(answer, temp);
    			} else if (maxCount < count) {
    				maxCount = count;
    				answer = temp;
    			}
        	}
        	
        	System.out.println("#" + t + " " + answer + " " + maxCount);
        }

	}

}
