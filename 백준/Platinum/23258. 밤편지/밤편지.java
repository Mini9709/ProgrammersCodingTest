import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int q;
	static int[][] distances;
	static int[][] questions;
	static int[][] answers;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		distances = new int[n+1][n+1];
		questions = new int[q][4];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= n; j++) {
				int edges = Integer.parseInt(st.nextToken());
				
				if (edges > 0) {
					distances[i][j] = edges;
				} else if (i == j) {
					distances[i][j] = 0;
				} else {
					distances[i][j] = 170325;
				}
			}
		}
		
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			
			questions[i][0] = i;
			questions[i][1] = Integer.parseInt(st.nextToken());
			questions[i][2] = Integer.parseInt(st.nextToken());
			questions[i][3] = Integer.parseInt(st.nextToken());

		}
		
		Arrays.sort(questions, (o1, o2) -> Integer.compare(o1[1], o2[1]));
		int idx = 0;
		answers = new int[q][2];
		
		while (idx < q) {
			if (questions[idx][1] == 1) {
				if (distances[questions[idx][2]][questions[idx][3]] != 170325) {
					answers[idx][0] = questions[idx][0];
					answers[idx][1] = distances[questions[idx][2]][questions[idx][3]];
				} else {
					answers[idx][0] = questions[idx][0];
					answers[idx][1] = -1;
				}
				idx++;
			} else {
				break;
			}
		}
		
		for (int x = 1; x <= n; x++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					distances[i][j] = Math.min(distances[i][j], distances[i][x] + distances[x][j]);
				}
			}
			
			while (idx < q) {
				if (questions[idx][1]-1 == x) {
					if (distances[questions[idx][2]][questions[idx][3]] != 170325) {
						answers[idx][0] = questions[idx][0];
						answers[idx][1] = distances[questions[idx][2]][questions[idx][3]];
					} else {
						answers[idx][0] = questions[idx][0];
						answers[idx][1] = -1;
					}
					idx++;
				} else {
					break;
				}
			}
		}
		
		Arrays.sort(answers, (o1, o2) -> Integer.compare(o1[0], o2[0]));
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < q; i++) {
			sb.append(answers[i][1]).append('\n');
		}
		
		System.out.println(sb);
	}

}