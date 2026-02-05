import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    /*
     * n : 햄스터 우리 개수
     * x : 각 우리 내 햄스터 최대 수
     * m : 경근이의 기록
     * i, r : i번 우리에서 r번 우리까지의 햄스터 수 s마리
     */

    static int n;
    static int x;
    static int m;
    static int[] hamsterHouse;
    static int[][] list;
    static int[] answer;

    public static boolean check() {

        for (int i = 0; i < list.length; i++) {
            int prev = list[i][0];
            int next = list[i][1];
            int sum = 0;

            for (int j = prev; j <= next; j++) {
                sum += hamsterHouse[j];
            }

            if (sum != list[i][2]) {
                return false;
            }
        }

        return true;
    }

    public static void dfs(int idx) {

        if (check()) {
            int prevSum = 0;
            int currSum = 0;
            for (int i = 0; i < n; i++) {
                prevSum += answer[i];
                currSum += hamsterHouse[i];
            }

            if (prevSum <= currSum) {
                answer = hamsterHouse.clone();
            }
        }

        for (int i = idx; i < n; i++) {
            if (hamsterHouse[i] < x) {
                hamsterHouse[i] += 1;
                dfs(i);
                hamsterHouse[i] -= 1;

            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {

            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken()); // 햄스터 우리 개수
            x = Integer.parseInt(st.nextToken()); // 우리 내 햄스터 최대치
            m = Integer.parseInt(st.nextToken()); // 기록 개수

            hamsterHouse = new int[n];
            answer = new int[n];
            answer[0] = -1;

            list = new int[m][3];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                list[i][0] = Integer.parseInt(st.nextToken())-1;
                list[i][1] = Integer.parseInt(st.nextToken())-1;
                list[i][2] = Integer.parseInt(st.nextToken());
            }

            dfs(0);

            System.out.print("#" + t + " ");
            if (answer[0] == -1) {
                System.out.println(-1);
            } else {
                for (int i = 0; i < n; i++) {
                    System.out.print(answer[i] + " ");
                }
                System.out.println();
            }
        }
    }
}
