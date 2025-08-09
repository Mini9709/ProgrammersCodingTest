import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int r, c;
    static boolean[] alphabet;
    static int[][] map;
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int answer = 0;
    static int count = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        alphabet = new boolean[26];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j) - 'A';
            }
        }

        BackTracking(0, 0);

        System.out.println(answer);
    }

    public static void BackTracking(int x, int y) {
        alphabet[map[y][x]] = true;
        count++;
        answer = Math.max(answer, count);

        for (int i = 0; i < 4; i++) {
            int next_x = x + move[i][0];
            int next_y = y + move[i][1];

            if (next_x >= 0 && next_x < c && next_y >= 0 && next_y < r) {
                if (!alphabet[map[next_y][next_x]]) {
                    BackTracking(next_x, next_y);
                    alphabet[map[next_y][next_x]] = false;
                    count--;
                }
            }
        }
    }
}