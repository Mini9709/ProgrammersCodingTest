/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	
	static int answer;
	static int startX;
	static int startY;
	static int desX;
	static int desY;
	
	static int[][] map;
	static boolean[][] visited;
	static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		for (int testCase = 1; testCase <= 10; testCase++) {		
			
			int t = Integer.parseInt(br.readLine());
			
			map = new int[16][16];
	
			for (int y = 0; y < 16; y++) {
				String str = br.readLine();
				
				for (int x = 0; x < 16; x++) {
					map[y][x] = str.charAt(x) - '0';
					
					if(map[y][x] == 2) {
						startX = x;
						startY = y;
					} else if (map[y][x] == 3) {
						desX = x;
						desY = y;
					}
				}
			}
			
			Queue<Integer[]> q = new ArrayDeque<>();
			visited = new boolean[16][16];
			
			q.add(new Integer[] {startX, startY});
			visited[startY][startX] = true;
			answer = 0;
			
			
			while (!q.isEmpty()) {
				Integer[] curr = q.poll();
				
				for (int[] d : directions) {
					int nextX = curr[0] + d[0];
					int nextY = curr[1] + d[1];
					
					if (nextX >= 0 && nextX < 16 && nextY >= 0 && nextY < 16 && !visited[nextY][nextX] && map[nextY][nextX] != 1) {
						if (map[nextY][nextX] == 3) {
							answer = 1;
							q.clear();
							break;
						} else {
							visited[nextY][nextX] = true;
							q.add(new Integer[] {nextX, nextY});
						}
					}
				}
			}
			
			System.out.println("#" + t + " " + answer);
		}
	}

}
