import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class DiceNode {
	int data[] = {0, 0, 0, 0, 0, 0, 0};
	int top = 1;
	int left = 4;
	int right = 3;
	int forward = 5;
	int back = 2;
	int floor = 6;
	
	public void move (int m) {
		int temp = top;
		
		if (m == 1) {
			top = left;
			left = floor;
			floor = right;
			right = temp;
			
		} else if (m == 2) {
			top = right;
			right = floor;
			floor = left;
			left = temp;
			
		} else if (m == 3) {
			top = forward;
			forward = floor;
			floor = back;
			back = temp;
			
		} else {
			top = back;
			back = floor;
			floor = forward;
			forward = temp;
		}
	}
}

public class Main {

	static int n;
	static int m;
	static int x;
	static int y;
	static int k;
	static DiceNode dice;
	static int[][] map;
	static int[] order;
	static int[][] directions = {{0, 0}, {1, 0}, {-1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		order = new int[k];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		
		dice = new DiceNode();
		
		for (int i = 0; i < k; i++) {
			int ord = order[i];
			
			int nx = x + directions[ord][0];
			int ny = y + directions[ord][1];
			
			if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
				dice.move(ord);
				if (map[ny][nx] == 0) {
					map[ny][nx] = dice.data[dice.floor];
				} else {
					dice.data[dice.floor] = map[ny][nx];
					map[ny][nx] = 0;
				}
				
				x = nx;
				y = ny;
				System.out.println(dice.data[dice.top]);
			}
		}
	}

}
