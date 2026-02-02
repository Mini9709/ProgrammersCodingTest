import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());

            int[][] firstArray = new int[n][n];
            int[][] rotate_90 = new int[n][n];
            int[][] rotate_180 = new int[n][n];
            int[][] rotate_270 = new int[n][n];

            for (int a = 0; a < n; a++) {
                st = new StringTokenizer(br.readLine());
                for (int b = 0; b < n; b++) {
                    firstArray[a][b] = Integer.parseInt(st.nextToken());
                }
            }

            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    rotate_90[x][n-1-y] = firstArray[y][x];
                    rotate_180[n-1-y][n-1-x] = firstArray[y][x];
                    rotate_270[n-1-x][y] = firstArray[y][x];
                }
            }

            System.out.println("#" + i);
            for (int c = 0; c < n; c++) {
                StringBuilder[] rot_str = {new StringBuilder(), new StringBuilder(), new StringBuilder()};
                for (int d = 0; d < n; d++) {
                    rot_str[0].append(rotate_90[c][d]);
                    rot_str[1].append(rotate_180[c][d]);
                    rot_str[2].append(rotate_270[c][d]);
                }

                System.out.println(rot_str[0] + " " + rot_str[1] + " " + rot_str[2]);
            }
        }
    }
}