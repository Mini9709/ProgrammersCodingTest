import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        Queue<Integer[]> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    queue.add(new Integer[]{j, i});
                }
            }
        }

        int answer = -1;

        while (!queue.isEmpty()) {
            answer += 1;
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                Integer[] tomato = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int next_x = tomato[0] + move[j][0];
                    int next_y = tomato[1] + move[j][1];

                    if (next_x >= 0 && next_y >= 0 && next_x < m && next_y < n) {
                        if (map[next_y][next_x] == 0) {
                            map[next_y][next_x] = 1;
                            queue.add(new Integer[]{next_x, next_y});
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    answer = -1;
                }
            }
        }

        System.out.println(answer);
    }
}