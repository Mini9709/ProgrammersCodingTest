import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= t; test_case++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int s = 0;
            int[] height_arr = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                height_arr[i] = Integer.parseInt(st.nextToken());
                s += height_arr[i];
            }

            int[][] dp = new int[n+1][s+1];
            int answer = -b; // 미리 높이 b를 차감

            /*
            1. dp[i][j] 이차배열 -> dp[키를 합친 인원의 수][키 총합의 한도]
            2. dp 시행
            3. i = n 이고 dp[i][j] >= b 인 시점에서 break 후 정답 제출
            */

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= s; j++) {
                    if (height_arr[i-1] <= j) {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-height_arr[i-1]] + height_arr[i-1]);
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }

                    if (i == n && dp[i][j] >= b) {
                        answer += dp[i][j];
                        break;
                    }
                }
            }
            System.out.println("#" + test_case + " " + answer);
        }
    }
}