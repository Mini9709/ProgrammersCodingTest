import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int n;
    static int answer;
    static int[][] map;
    static int[][] points;
    static int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		map = new int[4001][4001];
        
        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());

            points = new int[n+1][5];
            int remain = n;

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken()) * 2;
                int y = Integer.parseInt(st.nextToken()) * 2;
                int m = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                map[y+2000][x+2000] = e;

                points[i] = new int[] {x+2000, y+2000, m, e, 1};
            }

            answer = 0;

            while (remain > 0) {
                for (int i = 1; i <= n; i++) {
                    int[] curr = points[i];

                    int x = curr[0];
                    int y = curr[1];

                    if (curr[4] > 0) {
                        int nextX = x + directions[curr[2]][0];
                        int nextY = y + directions[curr[2]][1];

                        map[y][x] = 0;

                        if (nextX >= 0 && nextX <= 4000 && nextY >= 0 && nextY <= 4000) { // 범위 안에 들어왔을 때
                            if (map[nextY][nextX] > 0) { // 이동한 자리에 원자가 있을 때
                                map[nextY][nextX] += curr[3];
                                curr[4] = 0;
                                remain--;

                            } else { // 안만났을 때
                                map[nextY][nextX] = curr[3];
                                curr[0] = nextX;
                                curr[1] = nextY;
                            }
                        } else { // 범위 밖으로 나갔을 때
                            curr[4] = 0;
                            remain--;
                        }
                    }
                }

                for (int i = 1; i <= n; i++) {
                    int[] curr = points[i];

                    int x = curr[0];
                    int y = curr[1];

                    if (curr[4] > 0 && map[y][x] > curr[3]) {
                        answer += map[y][x];
                        curr[4] = 0;
                        remain --;

                        map[y][x] = 0;
                    }
                }

            }

            System.out.println("#" + t + " " + answer);
        }

    }

}