import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// 원숭이는 최대 k번 만큼 말처럼 이동가능
	// 말처럼이동은 도착지점 제외 장애물을 뛰어 넘을 수 있음
	// k번 다 쓰면 인접 4칸 이동만 가능
	// 도착지점까지 최소 동작으로 이동하는 값 찾기
	
	static int k;
	static int w;
	static int h;
	static int answer;
	static int[][] map;
	static int[][][] move;
	static int[][] normal = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int[][] horse = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};
	
	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        k = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        
        map = new int[h][w];
        
        for (int i = 0; i < h; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < w; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        move = new int[h][w][k+1];
        
        Queue<int[]> q = new ArrayDeque<>();
        
        q.add(new int[] {0, 0, 0});
        
        while (!q.isEmpty()) {
        	int[] curr = q.poll();
        	int x = curr[0];
        	int y = curr[1];
        	int used = curr[2];
        	
        	for (int[] d : normal) {
        		int nx = x + d[0];
        		int ny = y + d[1];
        		
        		if (nx >= 0 && nx < w && ny >= 0 && ny < h && map[ny][nx] == 0 && move[ny][nx][used] == 0) {
        			move[ny][nx][used] = move[y][x][used] + 1;
        			q.add(new int[] {nx, ny, used});
        		}
        	}
        	
        	if (used < k) {
        		for (int[] d : horse) {
            		int nx = x + d[0];
            		int ny = y + d[1];
            		
            		if (nx >= 0 && nx < w && ny >= 0 && ny < h && map[ny][nx] == 0 && move[ny][nx][used+1] == 0) {
            			move[ny][nx][used+1] = move[y][x][used] + 1;
            			q.add(new int[] {nx, ny, used+1});
            		}
            	}
        	}
        }
        
        answer = -1;
        
        if (h == 1 && w == 1) {
        	answer = 0;
        } else {
        	for (int i = 0; i <= k; i++) {
            	if (move[h-1][w-1][i] != 0) {
            		if (answer == -1) {
            			answer = move[h-1][w-1][i];
            		} else {
            			answer = Math.min(answer, move[h-1][w-1][i]);
            		}
            	}
            }
        }
        
        System.out.println(answer);
	}

}
