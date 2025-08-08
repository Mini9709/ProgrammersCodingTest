import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int number = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());

        int[][] arr = new int[number+1][2];
        for (int i = 1; i <= number; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] bag = new int[number+1][weight+1];

        for (int j = 1; j <= weight; j++){
            for (int i = 1; i <= number; i++) {
                if (bag[i-1][j] == 0) {
                    if (arr[i][0] <= j) bag[i][j] = arr[i][1];
                } else {
                    bag[i][j] = bag[i-1][j];
                    if (arr[i][0] <= j) {
                        bag[i][j] = Math.max(bag[i-1][j], bag[i-1][j-arr[i][0]] + arr[i][1]);
                    }
                }
            }
        }

        System.out.println(bag[number][weight]);
    }
}