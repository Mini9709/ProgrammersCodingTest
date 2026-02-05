import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int k;
	static int[][] magnet;
	static int[][] turn;
	static boolean[] isTurned;
	static boolean[] visited;
	static int[] magnetPoint;
	
	public static int turnPoint(int magnetPoint, int turn) {
		int temp = magnetPoint - turn;
		
		if (temp >= 8) {
			return temp - 8;
		} else if (temp < 0) {
			return temp + 8;
		} else {
			return temp;
		}
	}
	
	public static void checkMagnet(int magnetIdx) {
		
		int prevIdx = magnetIdx - 1;
		int nextIdx = magnetIdx + 1;
		
		isTurned[magnetIdx] = true;
		visited[magnetIdx] = true;

		if (prevIdx >= 0 && prevIdx < 4 && !visited[prevIdx]) {
			int mp = turnPoint(magnetPoint[magnetIdx], 2);
			int pp = turnPoint(magnetPoint[prevIdx], -2);
			
			if (magnet[magnetIdx][mp] != magnet[prevIdx][pp]) {
				checkMagnet(prevIdx);
			}
		}
		
		if (nextIdx >= 0 && nextIdx < 4 && !visited[nextIdx]) {
			int mp = turnPoint(magnetPoint[magnetIdx], -2);
			int np = turnPoint(magnetPoint[nextIdx], 2);

			if (magnet[magnetIdx][mp] != magnet[nextIdx][np]) {
				checkMagnet(nextIdx);
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			
			int k = Integer.parseInt(br.readLine());
			
			magnet = new int[4][8];
			
			
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			turn = new int[k][2];
				
			magnetPoint = new int[]{0, 0, 0, 0};
			
			int answer = 0;
			int[] score = {1, 2, 4, 8};
			
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				turn[i][0] = Integer.parseInt(st.nextToken()) - 1;
				turn[i][1] = Integer.parseInt(st.nextToken());
			}	
			
			for (int i = 0; i < k; i++) {
				visited = new boolean[4];
				isTurned = new boolean[4];

				checkMagnet(turn[i][0]);
				
				for (int idx = 0; idx < 4; idx++) {
					if (isTurned[idx]) {
						if (turn[i][0] % 2 == idx % 2) {
							magnetPoint[idx] = turnPoint(magnetPoint[idx], turn[i][1]);
						} else {
							magnetPoint[idx] = turnPoint(magnetPoint[idx], turn[i][1]*-1);
						}
					}
				}
			}
			
			for (int i = 0; i < 4; i++) {
				if (magnet[i][magnetPoint[i]] == 1) {
					answer += score[i];
				}
			}

			System.out.printf("#%d %d\n", t, answer);
		}
	}
}
