import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[][] black;
	static int[][] white;
	static int[][] map;
	static int res1;
	static int res2;
	static boolean[] diag1;
	static boolean[] diag2;
	
	public static void dfs(int idx, int r, boolean isBlack) {
		if (isBlack && idx == black.length) {
			res1 = Math.max(res1, r);
			return;
		}
		if (!isBlack && idx == white.length) {
			res2 = Math.max(res2, r);
			return;
		}

		int x = isBlack ? black[idx][0] : white[idx][0];
		int y = isBlack ? black[idx][1] : white[idx][1];
		
		if (map[y][x] != 0 && !diag1[x+y] && !diag2[x-y+n-1]) {
			diag1[x+y] = true;
			diag2[x-y+n-1] = true;
			
			dfs(idx+1, r+1, isBlack);
			
			diag1[x+y] = false;
			diag2[x-y+n-1] = false;
			dfs(idx+1, r, isBlack);
		} else {
			dfs(idx+1, r, isBlack);
		}
	}
	
	public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        black = new int[n*n/2 + n*n%2][2];
        white = new int[n*n/2][2];
        
        int idx1 = 0;
        int idx2 = 0;
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	for (int j = 0; j < n; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		
        		if ((i+j) % 2 == 0) {
        			black[idx1][0] = j;
        			black[idx1++][1] = i;
        		} else {
        			white[idx2][0] = j;
        			white[idx2++][1] = i;
        		}
        	}
        }
        
        res1 = 0;
        res2 = 0;
        diag1 = new boolean[n*2-1];
        diag2 = new boolean[n*2-1];
        
        dfs(0, 0, true);
        dfs(0, 0, false);
        
        System.out.println(res1 + res2);
	}

}