import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    static int[][] map_fly;
    static int[][] direction_plus = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] direction_x = {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

    public static int fly_spray (int m, int x, int y) {
        int sum_plus = map_fly[y][x];
        int sum_x = map_fly[y][x];

        for (int i = 1; i < m; i++) {
            for (int d = 0; d < 4; d++) {
                int plus_next_x = x + direction_plus[d][0]*i;
                int plus_next_y = y + direction_plus[d][1]*i;
                int x_next_x = x + direction_x[d][0]*i;
                int x_next_y = y + direction_x[d][1]*i;

                if (plus_next_y >= 0 && plus_next_y < map_fly.length && plus_next_x >= 0 && plus_next_x < map_fly[0].length) {
                    sum_plus += map_fly[plus_next_y][plus_next_x];
                }
                if (x_next_y >= 0 && x_next_y < map_fly.length && x_next_x >= 0 && x_next_x < map_fly[0].length) {
                    sum_x += map_fly[x_next_y][x_next_x];
                }
            }
        }

        return Math.max(sum_plus, sum_x);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= T; i++) {
            int answer = 0;
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            map_fly = new int[n][n];
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());

                for (int k = 0; k < n; k++) {
                    map_fly[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    answer = Math.max(answer, fly_spray(m, x, y));
                }
            }

            System.out.println("#" + i + " " + answer);
        }
    }
}