import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int zeroCount;
	static boolean[][] isEmpty;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[3][3];
		isEmpty = new boolean[3][3];
		zeroCount = 0;
		
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 0) {
					isEmpty[i][j] = true;
					zeroCount += 1;
				}
			}
		}

		int maxCount = 0;
		
		if ((isEmpty[0][0] && isEmpty[1][1] && isEmpty[2][2]) || (isEmpty[2][0] && isEmpty[1][1] && isEmpty[0][2])) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					maxCount += map[i][j];
				}
			}
			
			maxCount /= 2;
		} else {
			for (int i = 0; i < 3; i++) {
				int count1 = 0;
				int count2 = 0;
				
				for (int j = 0; j < 3; j++) {
					count1 += map[i][j];
					count2 += map[j][i];
				}
				
				maxCount = Math.max(maxCount, Math.max(count1, count2));
			}
			
			maxCount = Math.max(maxCount, Math.max(map[0][0] + map[1][1] + map[2][2], map[2][0] + map[1][1] + map[0][2]));
		}
		
		while (zeroCount > 0) {
			for (int i = 0; i < 3; i++) {
				int x = -1;
				int y = -1;
				int res = maxCount;
				
				for (int j = 0; j < 3; j++) {
					if (map[i][j] == 0) {
						if (x == -1 && y == -1) {
							x = j;
							y = i;
						} else {
							x = -1;
							y = -1;
							break;
						}
					}
					
					res -= map[i][j];
				}
				
				if (x != -1) {
					map[y][x] = res;
					zeroCount--;
				}
			}
			
			for (int j = 0; j < 3; j++) {
				int x = -1;
				int y = -1;
				int res = maxCount;
				
				for (int i = 0; i < 3; i++) {
					if (map[i][j] == 0) {
						if (x == -1 && y == -1) {
							x = j;
							y = i;
						} else {
							x = -1;
							y = -1;
							break;
						}
					}
					
					res -= map[i][j];
				}
				
				if (x != -1) {
					map[y][x] = res;
					zeroCount--;
				}
			}
			
			int x = -1;
			int y = -1;
			int res = maxCount;
			
			for (int i = 0; i < 3; i++) {
				if (map[i][i] == 0) {
					if (x == -1 && y == -1) {
						x = i;
						y = i;
					} else {
						x = -1;
						y = -1;
						break;
					}
				}
				
				res -= map[i][i];
			}
			
			if (x != -1) {
				map[y][x] = res;
				zeroCount--;
			}
			
			x = -1;
			y = -1;
			res = maxCount;
			
			for (int i = 0; i < 3; i++) {
				if (map[i][2-i] == 0) {
					if (x == -1 && y == -1) {
						x = i;
						y = i;
					} else {
						x = -1;
						y = -1;
						break;
					}
				}
				
				res -= map[i][i];
			}
			
			if (x != -1) {
				map[y][x] = res;
				zeroCount--;
			}
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
