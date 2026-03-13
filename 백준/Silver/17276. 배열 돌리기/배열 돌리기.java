import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;

    public static void rotate(int start, int end, int d) {
        int[] temp_num = {start, (start+end)/2 ,end};

        if (d < 0) {
            d = 360 + d;
        }

        int count = d/45;

        for (int c = 0; c < count; c++) {
            int temp_prev = map[start][start];
            int temp;

            for (int i = 0; i < 2; i++) {
                temp = map[start][temp_num[i+1]];
                map[start][temp_num[i+1]] = temp_prev;
                temp_prev = temp;
            }

            for (int i = 0; i < 2; i++) {
                temp = map[temp_num[i+1]][end];
                map[temp_num[i+1]][end] = temp_prev;
                temp_prev = temp;
            }

            for (int i = 2; i > 0; i--) {
                temp = map[end][temp_num[i-1]];
                map[end][temp_num[i-1]] = temp_prev;
                temp_prev = temp;
            }

            for (int i = 2; i > 0; i--) {
                temp = map[temp_num[i-1]][start];
                map[temp_num[i-1]][start] = temp_prev;
                temp_prev = temp;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < t; test_case++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map = new int[n][n];

            for (int y = 0; y < n; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < n; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            int start = 0, end = n-1;
            int rotate_blocks = n/2;
            for (int i = 0; i < rotate_blocks; i++) {
                rotate(start++, end--, d);
            }

            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    System.out.print(map[y][x] + " ");
                }
                System.out.println();
            }
        }
    }
}