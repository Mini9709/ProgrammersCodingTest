import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int chickens;
	static int answer;
	static int[][] map;
	static int[][] chicken;

	static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	// 해당 부분집합이 가지는 원소의 수를 반환하는 메서드
	public static int countBit(int b) {
		int count = 0;
		
		for (int i = 0; i < chickens; i++) {
			if ((b & 1 << i) > 0) {
				count++;
			}
		}
		
		return count;
	}
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        chicken = new int[14][2]; // 치킨집의 좌표를 담는 배열

        chickens = 0; // 치킨집의 개수

        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < n; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		if (map[i][j] == 2) {
        			chicken[++chickens][0] = j;
        			chicken[chickens][1] = i;
        		}
        	}
        }
        
        answer = Integer.MAX_VALUE;
        Queue<int[]> q = new ArrayDeque<>();
        
        for (int i = 1; i < 1 << chickens; i++) {
        	if (countBit(i) == m) {
        		int[][] route = new int[n][n];
        		
        		// bfs 전 시작위치들 초기화
        		for (int j = 1; j <= chickens; j++) {
        			if ((i & 1 << j-1) > 0) {
        				q.add(chicken[j]);
        				route[chicken[j][1]][chicken[j][0]] = 1;
        			}
        		}
        		
        		int sum = 0;
        		
        		// bfs 진행
        		while (!q.isEmpty()) {
        			int[] curr = q.poll();
        			int x = curr[0];
        			int y = curr[1];
        			
        			for (int[] d : directions) {
        				int nx = x + d[0];
        				int ny = y + d[1];
        				
        				if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
        					if (route[ny][nx] == 0) {
        						route[ny][nx] = route[y][x] + 1;
        						q.add(new int[] {nx, ny});
        						
        						if (map[ny][nx] == 1) {
                					sum += route[ny][nx]-1;
                				}
        					}
        				}
        			}
        		}
        		
        		answer = Math.min(answer, sum);
        	}
        }
        
        System.out.println(answer);
	}

}
