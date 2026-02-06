import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 : 수제 버거 장인 (BOJ 1194)
 * 메모리 : 102,124 kb
 * 실행시간 : 727 ms
 * 아이디어 :
 * 	비트마스크를 이용하여 제한 사항에 포함되지 않는 부분집합들 선별
 */

public class Main {
	
	/*
	 * '.' : 빈칸         '#' : 벽
	 * 'a' : 열쇠         'A' : 문
	 * '0' : 위치         '1' : 출구
	 */
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[n][m];
		int[][][] move = new int[n][m][1 << 6];
		int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		List<int[]> destinations = new ArrayList<>();
		
		int startX, startY;
		startX = startY = 0;
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '0') {
					startX = j;
					startY = i;
				}
				
				if (map[i][j] == '1') {
					destinations.add(new int[] {j, i});
				}
			}
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {startX, startY, 0});
		move[startY][startX][0] = 0;
		
		/*
		 * 1. bfs
		 * 2. 열쇠를 먹으면 해당 열쇠 체크, 열쇠 소유 상황을 비트마스킹
		 * 3. bfs 탐색은 열쇠 소유와 열쇠 미소유 구분하며 체크
		 * 4. 문에 도착하면 해당 열쇠가 있는지 체크
		 * 5. 열쇠 소유 상황이 같으면서 같은 지점에 도착하면 q 삽입x
		 * 6. q 끝나거나 1에 도착하면 종료 후 answer 
		 */
		
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int currX = curr[0];
			int currY = curr[1];
			int keys = curr[2];
			
			for (int[] d : directions) {
				int nextX = currX + d[0];
				int nextY = currY + d[1];
				
				if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
						// 벽 체크
					if (map[nextY][nextX] == '#') {
						continue;
						// 문 체크
					} else if (map[nextY][nextX] >= 'A' && map[nextY][nextX] <= 'F') {
						// 비트마스킹으로 체크
						int door = map[nextY][nextX] - 'A';
						if ((keys & 1 << door) > 0 && (move[nextY][nextX][keys] == 0 | move[nextY][nextX][keys] > move[currY][currX][keys] + 1)) {
							move[nextY][nextX][keys] = move[currY][currX][keys] + 1;
							queue.offer(new int[] {nextX, nextY, keys});
						}
						
					} else {
						// 출구 체크
						if (map[nextY][nextX] >= 'a' && map[nextY][nextX] <= 'f') {
							
							int key = map[nextY][nextX] - 'a'; // 현재 위치에 있는 키
							int temp = keys | 1 << key; // 비트마스킹한 키
							if (move[nextY][nextX][temp] == 0 | move[nextY][nextX][temp] > move[currY][currX][keys] + 1) {
								move[nextY][nextX][temp] = move[currY][currX][keys] + 1;
								queue.offer(new int[] {nextX, nextY, temp});
							}
						} else {
							if (move[nextY][nextX][keys] == 0 | move[nextY][nextX][keys] > move[currY][currX][keys] + 1) {
								move[nextY][nextX][keys] = move[currY][currX][keys] + 1;
								queue.offer(new int[] {nextX, nextY, keys});
						}
						// 방문여부 체크
						// 열쇠 지점 체크
						// 이동 체크
						}
					}
				}
			}
		}
		
		// 가능 여부를 판단하는 로직을 따로 넣어야 하지만 n,m이 최대 50일 때 MAX_VALUE가 나올 수 없으므로 제외
		int answer = Integer.MAX_VALUE;
		
		for (int i = 0; i < 1 << 6; i++) {
			for (int[] des : destinations) {
				if (move[des[1]][des[0]][i] != 0) {
					answer = Math.min(answer, move[des[1]][des[0]][i]);
				}
			}
		}
		
		if (answer == Integer.MAX_VALUE) {
			answer = -1;
		}
		
		System.out.println(answer);
	}
}
