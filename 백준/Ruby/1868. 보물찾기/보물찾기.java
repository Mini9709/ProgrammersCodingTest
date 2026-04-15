import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static ArrayList<Integer>[] graph;

    static int dfs(int cur, int parent) {
        int mask = 0;
        int maxOverlap = 0;

        for (int next : graph[cur]) {
            if (next == parent) continue;

            int childMask = dfs(next, cur);

            int overlap = mask & childMask;
            if (overlap != 0) {
                maxOverlap = Math.max(maxOverlap, 31 - Integer.numberOfLeadingZeros(overlap));
            }

            mask |= childMask;
        }

        // 현재 정점을 하나 포함시키는 효과
        mask += (1 << maxOverlap);

        // carry 처리 후, 그 아래 비트들은 의미 없으므로 정리
        if (maxOverlap > 0) {
            mask = (mask >> maxOverlap) << maxOverlap;
        }

        return mask;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        int resultMask = dfs(1, 0);
        int answer = 31 - Integer.numberOfLeadingZeros(resultMask);
        System.out.println(answer);
    }
}