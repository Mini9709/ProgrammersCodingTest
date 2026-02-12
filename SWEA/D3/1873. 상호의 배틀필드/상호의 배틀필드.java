import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int h;
	static int w;
	static int currX;
	static int currY;
	static int n;
	static char[][] map;
	static char[] order;
	static int[][] directions = {{1, 0}, {-1, 0} ,{0, 1}, {0, -1}};
	
	// 포탄 쏘는 메서드
	public static void shoot(int x, int y) {
		if (map[y][x] == '^') {
			while (true) {
				x += directions[3][0];
				y += directions[3][1];
				
				if (x >= 0 && x < w && y >= 0 && y < h) {
					if (map[y][x] == '*') {
						map[y][x] = '.';
						return;
					} else if (map[y][x] == '#') {
						return;
					}
				} else {
					return;
				}
			}
			
		} else if (map[y][x] == 'v') {
			while (true) {
				x += directions[2][0];
				y += directions[2][1];
				
				if (x >= 0 && x < w && y >= 0 && y < h) {
					if (map[y][x] == '*') {
						map[y][x] = '.';
						return;
					} else if (map[y][x] == '#') {
						return;
					}
				} else {
					return;
				}
			}
			
		} else if (map[y][x] == '<') {
			while (true) {
				x += directions[1][0];
				y += directions[1][1];
				
				if (x >= 0 && x < w && y >= 0 && y < h) {
					if (map[y][x] == '*') {
						map[y][x] = '.';
						return;
					} else if (map[y][x] == '#') {
						return;
					}
				} else {
					return;
				}
			}
			
		} else {
			while (true) {
				x += directions[0][0];
				y += directions[0][1];
				
				if (x >= 0 && x < w && y >= 0 && y < h) {
					if (map[y][x] == '*') {
						map[y][x] = '.';
						return;
					} else if (map[y][x] == '#') {
						return;
					}
				} else {
					return;
				}
			}
			
		}
	}
	
	public static void move (int x, int y, char o) {
		if (o == 'U') {
			map[y][x] = '^';
			
			x += directions[3][0];
			y += directions[3][1];
			
			if (x >= 0 && x < w && y >= 0 && y < h && map[y][x] == '.') {
				map[y][x] = '^';
				map[currY][currX] = '.';
				currY = y;
				currX = x;
			}
			
		} else if (o == 'D') {
			map[y][x] = 'v';
			
			x += directions[2][0];
			y += directions[2][1];
			
			if (x >= 0 && x < w && y >= 0 && y < h && map[y][x] == '.') {
				map[y][x] = 'v';
				map[currY][currX] = '.';
				currY = y;
				currX = x;
			}
		} else if (o == 'L') {
			map[y][x] = '<';
			
			x += directions[1][0];
			y += directions[1][1];
			
			if (x >= 0 && x < w && y >= 0 && y < h && map[y][x] == '.') {
				map[y][x] = '<';
				map[currY][currX] = '.';
				currY = y;
				currX = x;
			}
		} else {
			map[y][x] = '>';
			
			x += directions[0][0];
			y += directions[0][1];
			
			if (x >= 0 && x < w && y >= 0 && y < h && map[y][x] == '.') {
				map[y][x] = '>';
				map[currY][currX] = '.';
				currY = y;
				currX = x;
			}
		}
	}

	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	st = new StringTokenizer(br.readLine());
        	
        	h = Integer.parseInt(st.nextToken());
        	w = Integer.parseInt(st.nextToken());
        	
        	map = new char[h][w];
        	
        	for (int i = 0; i < h; i++) {
        		String str = br.readLine();
        		for (int j = 0; j < w; j++) {
        			map[i][j] = str.charAt(j);
        			
        			if (map[i][j] == '<' || map[i][j] == '>' || map[i][j] == '^' || map[i][j] == 'v') {
        				currX = j;
        				currY = i;
        			}
        		}
        	}
        	
        	n = Integer.parseInt(br.readLine());
        	order = new char[n];
        	String str = br.readLine();
        	
        	for (int i = 0; i < n; i++) {
        		order[i] = str.charAt(i);
        	}
        	
        	for (int i = 0; i < n; i++) {
        		// 스테이지 시작
        		if (order[i] == 'S') {
        			shoot(currX, currY);
        		} else {
        			move(currX, currY, order[i]);
        		}
        	}
        	
        	System.out.print("#" + t + " ");
        	for (int i = 0; i < h; i++) {
        		for (int j = 0; j < w; j++) {
        			System.out.print(map[i][j]);
        		}
        		System.out.println();
        	}
        }
	}

}
