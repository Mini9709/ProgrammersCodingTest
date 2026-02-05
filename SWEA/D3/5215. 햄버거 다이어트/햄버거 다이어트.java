import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int[][] arr = new int[n][2];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[n+1][l+1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= l; j++) {
                    if (arr[i-1][1] <= j) {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-arr[i-1][1]] + arr[i-1][0]);
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }

            System.out.printf("#%d %d\n", t, dp[n][l]);
        }
    }
}
