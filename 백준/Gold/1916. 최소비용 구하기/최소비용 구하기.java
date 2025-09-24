import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {

        int start, end, cost;

        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(a, b, c));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start_point = Integer.parseInt(st.nextToken());
        int end_point = Integer.parseInt(st.nextToken());


        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dp = new int[n + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[start_point] = 0;

        for(Node node : graph.get(start_point)) {
            pq.offer(node);
        }

        while (!pq.isEmpty()) {
            Node cur_bus = pq.poll();

            if(dp[cur_bus.start] + cur_bus.cost < dp[cur_bus.end]) {
                dp[cur_bus.end] = dp[cur_bus.start] + cur_bus.cost;
                for(Node node : graph.get(cur_bus.end)) {
                    pq.offer(node);
                }
            }
        }

        System.out.println(dp[end_point]);
    }
}