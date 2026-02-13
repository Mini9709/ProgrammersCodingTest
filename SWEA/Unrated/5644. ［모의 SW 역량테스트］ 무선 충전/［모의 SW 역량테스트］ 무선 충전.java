import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int m;
	static int a;
	static int time;
	static int ax, ay, bx, by;
	static int ca, cb;
	static int answer;
	static int[][][] map;
	static int[] aMove;
	static int[] bMove;
	static int[][] bcInfo;
	static int[][] directions = {{0, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // 부동, 상, 우, 하, 좌
	
	
	public static void move(int ab, int o) {
		// 지도 밖으로 나가는 경우가 없음
		if (ab == 0) {
			ax += directions[o][0];
			ay += directions[o][1];
		} else {
			bx += directions[o][0];
			by += directions[o][1];	
		}
	}
	
	public static void charge() {
		int aSelect = 0;
		int bSelect = 0;
		int temp = 0;
		
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < a; j++) {
				if (i != j) {
					aSelect = map[ay][ax][i];
					bSelect = map[by][bx][j];
					temp = Math.max(temp, aSelect + bSelect);
					
				} else {
					temp = Math.max(temp, map[ay][ax][i]);
                    temp = Math.max(temp, map[by][bx][i]);
				}
				
			}
		}
		
		answer += temp;
	}
	
	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	st = new StringTokenizer(br.readLine());
        	
        	m = Integer.parseInt(st.nextToken());
        	a = Integer.parseInt(st.nextToken());
        	map = new int[11][11][a];
        	aMove = new int[m];
        	bMove = new int[m];
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < m; i++) {
        		aMove[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < m; i++) {
        		bMove[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	bcInfo = new int[a][4];
        	for (int i = 0; i < a; i++) {
        		st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < 4; j++) {
        			bcInfo[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	for (int idx = 0; idx < a; idx++) {
        		int x = bcInfo[idx][0];
        		int y = bcInfo[idx][1];
        		
        		for (int i = 1; i <= 10; i++) {
        			for (int j = 1; j <= 10; j++) {
        				if (Math.abs(x-j) + Math.abs(y-i) <= bcInfo[idx][2]) {
        					map[i][j][idx] = bcInfo[idx][3];
        				}
        			}
        		}
        	}
        	
        	time = 0;
        	answer = 0;
        	ax = 1; ay = 1; bx = 10; by = 10;
        	
        	charge();
        	
        	while (time < m) {
        		move(0, aMove[time]);
        		move(1, bMove[time]);
        		charge();
        		time++;
        	}
        	
        	System.out.println("#" + t + " " + answer);
        }

	}

}
