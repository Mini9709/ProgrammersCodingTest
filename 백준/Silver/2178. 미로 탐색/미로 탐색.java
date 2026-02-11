import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        int[][] map = new int[n][m];
        boolean[][] visit = new boolean[n][m];
        int[][] arr = new int[n][m];
        
        for (int i = 0; i < n; i++) {
        	String str = br.readLine();
        	
        	for (int j = 0; j < m; j++) {
        		map[i][j] = str.charAt(j) - '0';
        	}
        }
        
        Queue<int[]> q = new ArrayDeque<>();
        
        visit[0][0] = true;
        arr[0][0] = 1;
        q.offer(new int[]{0, 0});
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];

            for (int[] d : directions) {
                int ny = y + d[0];
                int nx = x + d[1];

                if (ny >= 0 && ny < n && nx >= 0 && nx < m) {
                    if (!visit[ny][nx] && map[ny][nx] == 1) {
                        visit[ny][nx] = true;
                        arr[ny][nx] = arr[y][x] + 1;
                        q.offer(new int[]{ny, nx});
                    }
                }
            }
        }
        
        System.out.println(arr[n-1][m-1]);
	}
}
