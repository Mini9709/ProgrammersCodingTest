import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int answer = 0;
    static Integer[][] map;

    public static int bfs(int map_x, int map_y) {

        Queue<Integer[]> q = new LinkedList<>();
        Integer[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[map_y][map_x];
        boolean repeat = false;

        for(int y = 0; y < map_y; y++) {
            for(int x = 0; x < map_x; x++) {
                if (map[y][x] > 0 && !visited[y][x] && !repeat) { // 빙산이 있고, 들린 적 없으며, 첫 빙산 발견 시 bfs 탐색
                    q.add(new Integer[]{x, y});
                    visited[y][x] = true;
                    repeat = true;

                    while (!q.isEmpty()) { // 빙산 녹이기 진행
                        Integer[] curr = q.poll();
                        int curr_x = curr[0];
                        int curr_y = curr[1];


                        for (Integer[] d : directions) {
                            int next_x = curr_x + d[0];
                            int next_y = curr_y + d[1];

                            if (map[next_y][next_x] > 0 && !visited[next_y][next_x]) { // 주변에 들리지 않은 빙산이 있을 때 queue에 추가
                                q.add(new Integer[]{next_x, next_y});
                                visited[next_y][next_x] = true;
                            } else if (map[next_y][next_x] == 0 && !visited[next_y][next_x]) { // 주변에 바다가 있을 시 빙산 높이 감소
                                if (map[curr_y][curr_x] > 0) {
                                    map[curr_y][curr_x] -= 1;
                                }
                            }
                        }
                    }
                } else if (map[y][x] > 0 && !visited[y][x] && repeat) { // 두번째 빙산 발견 시
                    return answer;
                }
            }
        }

        if (!repeat) {
            answer = 0;
            return answer;
        } else {
            answer += 1;
            return bfs(map_x, map_y);
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int map_y = Integer.parseInt(st.nextToken());
        int map_x = Integer.parseInt(st.nextToken());
        map = new Integer[map_y][map_x];

        for (int i = 0; i < map_y; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < map_x; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(map_x, map_y));
    }
}