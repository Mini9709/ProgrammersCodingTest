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

            int answer = 0;

            for (int i = 0; i < 1 << n; i++) {
                int sumKcal = 0;
                int sumValue = 0;
                for (int j = 0; j < n; j++) {
                    if ((i & 1 << j) > 0) {
                        sumValue += arr[j][0];
                        sumKcal += arr[j][1];
                    }
                }

                if (sumKcal <= l) {
                    answer = Math.max(answer, sumValue);
                }
            }

            System.out.println("#" + t + " " + answer);
        }
    }
}