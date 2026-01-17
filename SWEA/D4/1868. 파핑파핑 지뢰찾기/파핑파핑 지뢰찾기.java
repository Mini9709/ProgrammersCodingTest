import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

        for (int i = 1; i <= t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            int[][] mine_map = new int[n][n];

            for (int y = 0; y < n; y++) {
                st = new StringTokenizer(br.readLine());
                String mines = st.nextToken();

                for (int x = 0; x < n; x++) {
                    if (mines.charAt(x) == '*') {
                        mine_map[y][x] = -1;
                        for (int[] d : directions) {
                            int next_x = x + d[0];
                            int next_y = y + d[1];

                            if (next_x >= 0 && next_x < n && next_y >= 0 && next_y < n && mine_map[next_y][next_x] != -1) {
                                mine_map[next_y][next_x] += 1;
                            }
                        }
                    }
                }
            }

            Queue<Integer[]> q = new LinkedList<>();
            boolean[][] check_map = new boolean[n][n];
            int answer = 0;

            /*
            1. for 문으로 check_map 확인
            2. 해당 지점이 false일 경우 확인한 지점 true로 체크, true일 경우 continue
            3. -1(지뢰)일 경우 continue
            4. 1 이상(주변에 지뢰가 있음) 일 경우 8방향 탐색
            4-1. 0이 있을 경우 : continue
            4-2. 0이 없을 경우 : answer + 1
            5. 0(주변에 지롸가 없음) 일 경우 8방향 bfs 탐색
            5-1. 0일 경우 true 체크, queue 삽입
            5-2. 1이상일 경우 true 체크 후 continue
            */

            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (!check_map[y][x]) {
                        check_map[y][x] = true;

                        if (mine_map[y][x] > 0) { // 해당 지점이 1 이상일 경우
                            int temp = 1;

                            for (int[] d : directions) { // 8방향에 0인 지점이 있는지 확인
                                int next_x = x + d[0];
                                int next_y = y + d[1];
                                if (next_x >= 0 && next_x < n && next_y >= 0 && next_y < n && mine_map[next_y][next_x] == 0) {
                                    temp = 0;
                                    break;
                                }
                            }

                            answer += temp;
                        } else if (mine_map[y][x] == 0) { // 해당 지점이 0일 경우
                            q.add(new Integer[]{x, y});

                            while (!q.isEmpty()) { // bfs
                                Integer[] p = q.poll();
                                int curr_x = p[0];
                                int curr_y = p[1];

                                for (int[] d : directions) {
                                    int next_x = curr_x + d[0];
                                    int next_y = curr_y + d[1];

                                    if (next_x >= 0 && next_x < n && next_y >= 0 && next_y < n && !check_map[next_y][next_x]) {
                                        check_map[next_y][next_x] = true; // 다음 지점 check
                                        if (mine_map[next_y][next_x] == 0) { // 다음 지점이 0일 경우 queue 삽입
                                            q.add(new Integer[]{next_x, next_y});
                                        }
                                    }
                                }
                            }

                            answer += 1;
                        }
                    }
                }
            }

            System.out.println("#" + i + " " + answer);
        }
    }
}