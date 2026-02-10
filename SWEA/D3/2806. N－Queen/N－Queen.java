import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int answer;
    static int n;
    static int[][] chess_board;

    public static void backtracking(int x, int y) {
        if (y == n-1) {
            answer++;
        } else {
            chess_board[y][x] = 1;
            checkboard(x, y, 0, -(y+1));

            for (int i = 0; i < n; i++) {
                if (chess_board[y+1][i] == 0) {
                    backtracking(i, y+1);
                }
            }

            chess_board[y][x] = 0;
            checkboard(x, y, -(y+1), 0);
        }
    }

    public static void checkboard(int x, int y, int pre_num, int check_num) {
        for (int i = y+1; i < n; i++) {
            if (chess_board[i][x] == pre_num) {
                chess_board[i][x] = check_num;
            }
            if (x+y-i >= 0 && x+y-i < n) {
                if (chess_board[i][x+y-i] == pre_num) {
                    chess_board[i][x+y-i] = check_num;
                }
            }
            if (x-y+i >= 0 && x-y+i < n) {
                if (chess_board[i][x-y+i] == pre_num) {
                    chess_board[i][x-y+i] = check_num;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	
        	n = Integer.parseInt(br.readLine());

            chess_board = new int[n][n];
            answer = 0;

            for (int i = 0; i < n; i++) {
                backtracking(i, 0);
            }

            System.out.println("#" + t + " " + answer);
        }
        
    }

}
