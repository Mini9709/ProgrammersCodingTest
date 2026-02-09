import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int r, c, t;
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int answer = 0;
    static Queue<Integer[]> q;

    public static void split_dust() {
        int count = q.size();

        for (int i = 0; i < count; i++) {
            Integer[] curr = q.poll();
            int split = curr[2] / 5;

            for (int[] d : directions) {
                int next_x = curr[0] + d[0];
                int next_y = curr[1] + d[1];

                if (next_x >= 0 && next_x < c && next_y >= 0 && next_y < r && map[next_y][next_x] != -1) {
                    map[next_y][next_x] += split;
                    map[curr[1]][curr[0]] -= split;
                }
            }
        }
    }

    public static void work_purifier(int up, int down) {
        int temp_prev = map[up][1];
        int temp;

        for (int x = 0; x < c-1; x++) {
            temp = map[up][x+1];
            if (map[up][x] == -1) {
                map[up][x+1] = 0;
            } else {
                map[up][x+1] = temp_prev;
            }
            temp_prev = temp;
        }

        for (int y = up; y > 0; y--) {
            temp = map[y-1][c-1];
            map[y-1][c-1] = temp_prev;
            temp_prev = temp;
        }

        for (int x = c-1; x > 0 ; x--) {
            temp = map[0][x-1];
            map[0][x-1] = temp_prev;
            temp_prev = temp;
        }

        for (int y = 0; y < up ; y++) {
            temp = map[y+1][0];
            if (temp != -1) {
                map[y+1][0] = temp_prev;
                temp_prev = temp;
            } else {
                answer -= temp_prev;
            }
        }

        temp_prev = map[down][1];
        for (int x = 0; x < c-1; x++) {
            temp = map[down][x+1];
            if (map[down][x] == -1) {
                map[down][x+1] = 0;
            } else {
                map[down][x+1] = temp_prev;
            }
            temp_prev = temp;
        }

        for (int y = down; y < r-1; y++) {
            temp = map[y+1][c-1];
            map[y+1][c-1] = temp_prev;
            temp_prev = temp;
        }

        for (int x = c-1; x > 0 ; x--) {
            temp = map[r-1][x-1];
            map[r-1][x-1] = temp_prev;
            temp_prev = temp;
        }

        for (int y = r-1; y > down ; y--) {
            temp = map[y-1][0];
            if (temp != -1) {
                map[y-1][0] = temp_prev;
                temp_prev = temp;
            } else {
                answer -= temp_prev;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        int up = 0, down = 0;
        q = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                    q.add(new Integer[]{j, i, map[i][j]});
                    answer += map[i][j];
                }
                if (map[i][j] == -1) {
                    if (up == 0) {
                        up = i;
                    } else {
                        down = i;
                    }
                }
            }
        }

        for (int i = 0; i < t; i++) {
            split_dust();
            work_purifier(up, down);
            for (int y = 0; y < r; y++) {
                for (int x = 0; x < c; x++) {
                    if (map[y][x] > 0) {
                        q.add(new Integer[] {x, y, map[y][x]});
                    }
                }
            }
        }

        System.out.println(answer);
    }
}