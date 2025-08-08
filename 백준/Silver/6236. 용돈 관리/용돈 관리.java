import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int day = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());

        int[] arr = new int[day];
        int min = 0;
        int max = 0;
        for (int i = 0; i < day; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max += arr[i];
            min = Math.max(arr[i], min);
        }


        while (min <= max) {
            int mid = (min + max) / 2;
            int money = mid;
            int m = 1;
            for (int i = 0; i < day; i++) {
                if (money >= arr[i]) {
                    money -= arr[i];
                } else {
                    money = mid - arr[i];
                    m++;
                }
            }
            if (m <= count) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(min);
    }
}