import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][][] dp = new int[101][101][101];

    public static int memo(int a, int b, int c) {
        int x = a + 50;
        int y = b + 50;
        int z = c + 50;

        if (dp[x][y][z] != 0) {
            return dp[x][y][z];
        }
        else if (x <= 50 || y <= 50 || z <= 50) {
            dp[x][y][z] = 1;
        } else if (x > 70 || y > 70 || z > 70) {
            dp[x][y][z] = memo(20, 20, 20);
        } else if (x < y && y < z) {
            dp[x][y][z] = memo(a, b, c-1) + memo(a, b-1, c-1) - memo(a, b-1, c);
        } else {
            dp[x][y][z] = memo(a-1, b, c) + memo(a-1, b-1, c) + memo(a-1, b, c-1)- memo(a-1, b-1, c-1);
        }

        return dp[x][y][z];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a= Integer.parseInt(st.nextToken());
        int b= Integer.parseInt(st.nextToken());
        int c= Integer.parseInt(st.nextToken());

        while (!(a == -1 && b == -1 && c == -1)) {
            int answer = memo(a, b, c);
            System.out.println("w(" + a + ", " + b + ", " + c + ") = " + answer);
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
        }
    }
}