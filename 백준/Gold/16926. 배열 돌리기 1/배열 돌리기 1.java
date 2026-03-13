import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	
	public static void rotate(int start_x, int start_y, int end_x, int end_y, int[][] num_map) { // 회전 메서드
		int temp1 = num_map[start_y][end_x];
		int temp2 = num_map[start_y][end_x];
		
		for (int i = end_x; i > start_x; i--) {
			temp1 = num_map[start_y][i-1];
			num_map[start_y][i-1] = temp2;
			temp2 = temp1;
		}
		
		for (int i = start_y; i < end_y; i++) {
			temp1 = num_map[i+1][start_x];
			num_map[i+1][start_x] = temp2;
			temp2 = temp1;
		}
		
		for (int i = start_x; i < end_x; i++) {
			temp1 = num_map[end_y][i+1];
			num_map[end_y][i+1] = temp2;
			temp2 = temp1;
		}
		
		for (int i = end_y; i > start_y; i--) {
			temp1 = num_map[i-1][end_x];
			num_map[i-1][end_x] = temp2;
			temp2 = temp1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[][] num_map = new int[n][m];
		
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			
			for (int x = 0; x < m; x++) {
				num_map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = Math.min(n,  m) / 2; // 회전시키는 블럭의 개수
		int start_x = 0; int start_y = 0; int end_x = m-1; int end_y = n-1; // 블럭을 나누기 위한 모서리 4개
		int rotate_block = 0; // 실질적으로 돌아가는 회전 수
		
		for (int j = 0; j < count; j++) {
			int per_rotation = (end_x + end_y - start_x - start_y) * 2; // 블럭의 1바퀴 당 회전 수
			if (per_rotation != 0) { // 1개짜리 블럭일 경우(per_rotation == 0) -> 돌려도 의미가 없으므로 rotate_block을 0으로 초기화
				rotate_block = r % per_rotation;
			}
			
			for (int i = 0; i < rotate_block; i++) {
				rotate(start_x, start_y, end_x, end_y, num_map);
			}
			
			start_x++; start_y++; end_x--; end_y--;
		}		
		
		for (int y = 0; y < n; y++) {	
			for (int x = 0; x < m; x++) {
				System.out.print(num_map[y][x] + " ");
			}
			System.out.println();
		}
		
	}
}