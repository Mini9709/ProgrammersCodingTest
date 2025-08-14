import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<ArrayList<Integer>> graph;
    static int[] visit;
    static boolean answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            visit = new int[v+1];
            answer = true;

            for (int j = 0; j <= v; j++) {
                graph.add(new ArrayList<>());
            }

            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());
                int point1 = Integer.parseInt(st.nextToken());
                int point2 = Integer.parseInt(st.nextToken());

                graph.get(point1).add(point2);
                graph.get(point2).add(point1);
            }

            for (int j = 1; j <= v; j++) {
                if (visit[j] == 0) {
                    dfs(j, 1);
                }
            }

            if (answer) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static void dfs(int point, int color) {
        visit[point] = color;

        for (int i = 0; i < graph.get(point).size(); i++) {
            if (visit[graph.get(point).get(i)] == 0) {
                if (color == 1) {
                    dfs(graph.get(point).get(i), 2);
                } else {
                    dfs(graph.get(point).get(i), 1);
                }
            } else if (visit[graph.get(point).get(i)] == color) {
                answer = false;
                return;
            }
        }
    }
}