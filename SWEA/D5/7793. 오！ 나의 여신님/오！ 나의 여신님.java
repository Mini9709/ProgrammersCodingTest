import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
 
    static int n;
    static int m;
    static int endX, endY;
    static char[][] map;
    static int[][][] route;
    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        int testCase = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
 
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new char[n][m];
            route = new int[n][m][2];
            Queue<int[]> playerQ = new ArrayDeque<>();
            Queue<int[]> devilQ = new ArrayDeque<>();
 
            for (int i = 0; i < n; i++) {
                String str = br.readLine();
 
                for (int j = 0; j < m; j++) {
                    map[i][j] = str.charAt(j);
                    route[i][j][0] = -1; // 이동한 턴
                    route[i][j][1] = -1; // 아직 침식당하지 않음
                    if (map[i][j] == 'S') {
                        playerQ.add(new int[]{j, i});
                        route[i][j][0] = 0;
                    } else if (map[i][j] == 'D') {
                        endX = j;
                        endY = i;
                    } else if (map[i][j] == '*') {
                        devilQ.add(new int[]{j, i});
                        route[i][j][1] = 0; // 침식당함
                    }
                }
            }
             
            while (!playerQ.isEmpty()) {
                int pSize = playerQ.size();
                int dSize = devilQ.size();
                
                for (int i = 0; i < dSize; i++) {
                    int[] dv = devilQ.poll();
 
                    int dx = dv[0];
                    int dy = dv[1];
 
                    for (int[] d : directions) {
                        int nx = dx + d[0];
                        int ny = dy + d[1];
 
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n && map[ny][nx] != 'X' && map[ny][nx] != 'D') {
                            if (route[ny][nx][1] == -1) {
                                route[ny][nx][1] = route[dy][dx][1] + 1;
                                devilQ.add(new int[]{nx, ny});
                            }
                        }
                    }
                }
 
                for (int i = 0; i < pSize; i++) {
                    int[] p = playerQ.poll();
 
                    int px = p[0];
                    int py = p[1];
 
                    for (int[] d : directions) {
                        int nx = px + d[0];
                        int ny = py + d[1];
 
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n && map[ny][nx] != 'X') {
                            if (route[ny][nx][0] == -1 && route[ny][nx][1] == -1) {
                                route[ny][nx][0] = route[py][px][0] + 1;
                                playerQ.add(new int[]{nx, ny});
                            }
                        }
                    }
                }     
            }
 
            System.out.print("#" + t + " ");
            if (route[endY][endX][0] == -1) {
                System.out.println("GAME OVER");
            } else {
                System.out.println(route[endY][endX][0]);
            }
        }
    }
}