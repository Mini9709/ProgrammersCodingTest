import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int d;
    static int[][] map;
    static int maxTurn;
    static int answer;

    /*
    1. 적 스캔
    2. 사거리 내 가장 가까운 적 선별(중첩돼도 ㅇㅋ)
    3. 가장 가까운 적이 여럿이면 그 중 왼쪽에 있는 적(x값)
    4. 처치하고 적 턴
    5. 한 칸씩 내려오고 성에 도착하면 소멸
    6. 킬 수 확인
     */

    public static int startGame(int a, int b, int c) {

        boolean[][] dead = new boolean[n][m];
        int turn = 0;
        int kill = 0;

        // 목표 탐색
        while (turn < maxTurn) {

            int[] attackA = {-1, -1, 11};
            int[] attackB = {-1, -1, 11};
            int[] attackC = {-1, -1, 11};

            for (int y = 0; y < n; y++) {
                for (int x = 0; x < m; x++) {

                    if (y + turn < n) {
                        int distanceA = n - y + Math.abs(a - x) - turn;
                        int distanceB = n - y + Math.abs(b - x) - turn;
                        int distanceC = n - y + Math.abs(c - x) - turn;

                        if (map[y][x] == 1 && !dead[y][x] && distanceA <= d) {
                            if (distanceA < attackA[2] || (distanceA == attackA[2] && x < attackA[0])) {
                                attackA[0] = x;
                                attackA[1] = y;
                                attackA[2] = distanceA;
                            }
                        }

                        if (map[y][x] == 1 && !dead[y][x] && distanceB <= d) {
                            if (distanceB < attackB[2] || (distanceB == attackB[2] && x < attackB[0])) {
                                attackB[0] = x;
                                attackB[1] = y;
                                attackB[2] = distanceB;
                            }
                        }

                        if (map[y][x] == 1 && !dead[y][x] && distanceC <= d) {
                            if (distanceC < attackC[2] || (distanceC == attackC[2] && x < attackC[0])) {
                                attackC[0] = x;
                                attackC[1] = y;
                                attackC[2] = distanceC;
                            }
                        }
                    }
                }
            }

            for (int y = 0; y < n; y++) {
                for (int x = 0; x < m; x++) {
                    if (map[y][x] == 1 && !dead[y][x]) {
                        if (attackA[0] == x && attackA[1] == y) {
                            kill++;
                            dead[y][x] = true;
                        } else if (attackB[0] == x && attackB[1] == y) {
                            kill++;
                            dead[y][x] = true;
                        } else if (attackC[0] == x && attackC[1] == y) {
                            kill++;
                            dead[y][x] = true;
                        }
                    }
                }
            }

            turn++;
        }

        return kill;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        maxTurn = -1;
        answer = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1 && maxTurn == -1) {
                    maxTurn = n-i;
                }
            }
        }

        for (int a = 0; a < m-2; a++) {
            for (int b = a+1; b < m-1; b++) {
                for (int c = b+1; c < m; c++) {
                    answer = Math.max(answer, startGame(a, b, c));
                }
            }
        }

        System.out.println(answer);
    }
}
