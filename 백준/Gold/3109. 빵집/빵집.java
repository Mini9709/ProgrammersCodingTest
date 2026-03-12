import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int r;
	static int c;
	static int[][] map;
	static int[][] directions = {{1, -1}, {1, 0}, {1, 1}};
	static int answer;
	
	public static void dfs(int i, int depth, int x, int y) {
		if (depth == c-1) {
			map[i][0] = i+1;
			answer++;
			return;
		}
		
		for (int[] d : directions) {
			if (map[i][0] != 0) return;
			
			int nx = x + d[0];
			int ny = y + d[1];
			
			if (nx >= 0 && nx < c && ny >= 0 && ny < r) {
				if (map[ny][nx] == -1) {
					continue;
				} else if (map[ny][nx] == 0) {
					if (map[i][0] == 0) {
						map[ny][nx] = i+1;
						dfs(i, depth+1, nx, ny);
					}
				}
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        
        for (int i = 0; i < r; i++) {
        	String str = br.readLine();
        	
        	for (int j = 0; j < c; j++) {
        		char temp = str.charAt(j);
        		
        		if (temp == '.') {
        			map[i][j] = 0;
        		} else {
        			map[i][j] = -1;
        		}
        	}
        }
        
        answer = 0;
        
        for (int i = 0; i < r; i++) {
        	dfs(i, 0, 0, i);
        }
        
        System.out.println(answer);
	}

}
