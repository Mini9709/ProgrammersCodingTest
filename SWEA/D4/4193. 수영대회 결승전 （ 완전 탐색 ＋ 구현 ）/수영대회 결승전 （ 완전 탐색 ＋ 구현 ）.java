import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= t; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int[][] swimming_pool = new int[n][n];

            for(int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());

                for(int k = 0; k < n; k++) {
                    swimming_pool[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            st = new StringTokenizer(br.readLine());
            int start_y = Integer.parseInt(st.nextToken());
            int start_x = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int end_y = Integer.parseInt(st.nextToken());
            int end_x = Integer.parseInt(st.nextToken());

            int time = 0; // 시간초
            int[][] time_map = new int[n][n]; // 도착 시 시간 배열
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    time_map[y][x] = -1;
                }
            }

            Queue<Integer[]> q = new LinkedList<>();
            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            q.add(new Integer[] {start_x, start_y});
            time_map[start_y][start_x] = 0;

            /*
             * 1. 시작지점에서 4방향 탐색
             * 2. bfs 탐색
             * 3. 도착 시 기존 지점 수 + 1
             * 4. 2 지점이 존재하고 통과하지 못하면 queue에 기존 지점 다시 삽입
             * 5. 2 지점은 time%3 == 2인 경우 통과 가능
             * 6. q 한 사이클 후 time +1
             * 7. endpoint 도착 시 break 후 종료
             * 8. endpoint에 도착하지 못하고 q 소진 시 -1 출력
             */

            while (!q.isEmpty()){
                int length_q = q.size();
                for(int a = 0; a < length_q; a++) {
                    Integer[] p = q.poll(); // 현재 queue에서 지점 추출
                    int curr_x = p[0];
                    int curr_y = p[1];

                    for (int[] d : directions) { // 4방향 bfs
                        int next_x = curr_x + d[0];
                        int next_y = curr_y + d[1];

                        if (next_x >= 0 && next_x < n && next_y >= 0 && next_y < n && time_map[next_y][next_x] == -1) {
                            if (swimming_pool[next_y][next_x] == 0) { // 다음 지역이 바다일 때
                                time_map[next_y][next_x] = time + 1;
                                q.add(new Integer[] {next_x, next_y});
                            } else if (swimming_pool[next_y][next_x] == 2) { // 다음 지역이 소용돌이일 때
                                if (time % 3 == 2) { // 지나갈 수 있을 떄
                                    time_map[next_y][next_x] = time + 1;
                                    q.add(new Integer[] {next_x, next_y});
                                } else { // 지나갈 수 없을 때
                                    q.add(new Integer[] {curr_x, curr_y});
                                }
                            }
                        }
                        
                    }
                }
                time += 1;
                if (time_map[end_y][end_x] != -1) {
                    break;
                }
            }
            System.out.println("#" + i + " " + time_map[end_y][end_x]);
        }
    }
}