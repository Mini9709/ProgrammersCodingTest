import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] map = new int[1001];
        int top_height = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            map[l] = h;
            top_height = Math.max(top_height, h);
        }

        int prev = 0;
        int answer = 0;
        int top_x = 0;

        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) {
                if (prev == 0) {
                    prev = i;
                }
                if (map[i] >= map[prev]) {
                    answer += (i - prev) * map[prev];
                    prev = i;
                }
                if (map[prev] == top_height) {
                    top_x = i;
                    break;
                }
            }
        }

        prev = 0;
        for (int i = map.length-1; i >= top_x; i--) {
            if (map[i] != 0) {
                if (prev == 0) {
                    prev = i;
                }
                if (map[i] >= map[prev]) {
                    answer += (prev - i) * map[prev];
                    prev = i;
                }
                if (map[prev] == top_height) {
                    answer += (i - top_x + 1) * top_height;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}