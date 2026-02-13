import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    
    static int n;
    static int turn;
    static int answer;
    static int[] arr;

    public static void numGame(int num) {
        if (num < 10) {
        	arr[num] = 0;
            return;
        } else {
        	String str = String.valueOf(num);
            int len = str.length();
            
            for (int i = 1; i < 1 << len-1; i++) {
                int res = 1;
                int startIdx = 0;
                
                for (int j = 1; j <= len-1; j++) {    
                    if ((i & 1 << j-1) > 0) {
                        res *= Integer.parseInt(str.substring(startIdx, j));
                        startIdx = j;
                    }
                }
                
                res *= Integer.parseInt(str.substring(startIdx, len));

                arr[num] = Math.max(arr[num], arr[res]+1);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());
            turn = 0;
            answer = 0;
            arr = new int[100000];
            
            for (int i = 1; i <= n; i++) {
            	numGame(i);
            }
            
            System.out.println("#" + t + " " + arr[n]);
        }
    }

}