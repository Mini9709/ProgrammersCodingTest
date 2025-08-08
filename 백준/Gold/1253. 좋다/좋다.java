import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0 ; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int answer = 0;

        for (int a = 0; a < n; a++) {
            int start = 0;
            int end = n-1;
            while (start != end) {
                if (start == a) {
                    start++;
                } else if (end == a) {
                    end--;
                }

                else if (arr[start] + arr[end] > arr[a]) {
                    end--;
                }
                else if (arr[start] + arr[end] < arr[a]) {
                    start++;
                } else {
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}