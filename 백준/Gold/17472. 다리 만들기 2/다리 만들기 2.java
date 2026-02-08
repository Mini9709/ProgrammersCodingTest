import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] map;
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] route;
    static int island;
    static int[] parent;
    static int answer;
    /*
    다리는 꺾이면 안됨
    다리 길이는 2이상
    다리의 연결 방향성은 같아야 함(양 끝점 포함)
    교차점도 중복해서 계산 (길이 4이고 교차된 다리 2개의 총길이는 4+4 = 8)

     */

    /*
    1. 각 섬 별 거리 최소 거리 계산
    2. 각 섬을 루트노드로 시작해서 다른 섬으로 이동할 때 사용하는 다리 길이 값의 합을 산출
    3. 산출한 값 중 최소값을 리턴
     */
    public static int find(int[] parent, int x) {
        if(parent[x] == x) return x;
        else return find(parent, find(parent, parent[x]));
    }

    public static void union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }

    public static int kruskal(ArrayList<int[]> sortRoute, int[] parent) {
        int cost = 0;
        for (int i = 0; i < sortRoute.size(); i++) {
            if (find(parent, sortRoute.get(i)[1]) != find(parent, sortRoute.get(i)[2])) {
                cost += sortRoute.get(i)[0];
                union(parent, sortRoute.get(i)[1], sortRoute.get(i)[2]);
            }
        }

        for (int i = 1; i < island-1; i++) {
            if (find(parent, i) != find(parent, i+1)) {
                return -1;
            }
        }

        return cost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == 1) {
                    temp = -1;
                }
                map[i][j] = temp;
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        island = 1;
        ArrayList<int[]> islands = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == -1) {
                    queue.add(new int[] {j, i});
                    islands.add(new int[] {j, i});

                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int currX = curr[0];
                        int currY = curr[1];
                        map[currY][currX] = island;

                        for (int[] d : directions) {
                            int nextX = currX + d[0];
                            int nextY = currY + d[1];

                            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && map[nextY][nextX] == -1) {
                                queue.add(new int[] {nextX, nextY});
                                islands.add(new int[] {nextX, nextY});
                            }
                        }
                    }

                    island++;
                }
            }
        }

        route = new int[island][island]; // 섬 개수 + 1

        for (int i = 0; i < islands.size(); i++) {
            int currX = islands.get(i)[0];
            int currY = islands.get(i)[1];

            for (int[] d : directions) {
                int nextX = currX + d[0];
                int nextY = currY + d[1];
                int len = 0;

                while (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
                    if (map[nextY][nextX] == map[currY][currX]) {
                        break;
                    } else if (map[nextY][nextX] == 0) {
                        len++;
                        nextX += d[0];
                        nextY += d[1];
                    } else if (len > 1){
                        if (route[map[nextY][nextX]][map[currY][currX]] == 0) {
                            route[map[nextY][nextX]][map[currY][currX]] = len;
                            route[map[currY][currX]][map[nextY][nextX]] = len;
                        } else {
                            int currNum = route[map[nextY][nextX]][map[currY][currX]];
                            route[map[nextY][nextX]][map[currY][currX]] = Math.min(currNum, len);
                            route[map[currY][currX]][map[nextY][nextX]] = Math.min(currNum, len);
                        }
                        break;
                    } else {
                        break;
                    }
                }
            }
        }

        ArrayList<int[]> sortRoute = new ArrayList<>();
        int[] parent = new int[island];
        for (int y = 1; y < route.length; y++) {
            for (int x = y; x < route.length; x++) {
                if (route[y][x] > 1) {
                    sortRoute.add(new int[] {route[y][x], x, y});
                }
            }
        }

        sortRoute.sort((o1, o2) -> o1[0] - o2[0]);

        for (int i = 0; i < island; i++) {
            parent[i] = i;
        }

        answer = kruskal(sortRoute, parent);

        System.out.println(answer);
    }
}
