import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int end, weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int points = Integer.parseInt(st.nextToken());
        int lines = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= points; i++) {
            graph.add(new ArrayList<>());
        }

        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < lines; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Node n = new Node(v, w);

            graph.get(u).add(n);
        }

        int[] dp = new int[points+1];
        for (int i = 0; i <= points; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(start, 0));
        dp[start] = 0;

        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            ArrayList<Node> curr_point = graph.get(curr.end);

            for (Node node : curr_point) {
                if (dp[node.end] > node.weight + curr.weight) {
                    dp[node.end] = node.weight + curr.weight;
                    pq.offer(new Node(node.end, dp[node.end]));
                }
            }
        }

        for (int i = 1; i <= points; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dp[i]);
            }
        }
    }
}