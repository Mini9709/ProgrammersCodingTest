import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int data;
    ArrayList<Node> friends = new ArrayList<>();

    public Node(int data) {
        this.data = data;
    }

    public void addFriend(Node friend) {
        friends.add(friend);
    }
}

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Node[] nodes = new Node[n+1];

            for (int i = 1; i <= n; i++) {
                nodes[i] = new Node(i);
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int prev = Integer.parseInt(st.nextToken());
                int next = Integer.parseInt(st.nextToken());

                nodes[prev].addFriend(nodes[next]);
                nodes[next].addFriend(nodes[prev]);
            }

            Queue<Node> q = new ArrayDeque<>();
            boolean[] visited = new boolean[n+1];
            int answer = 0;

            q.add(nodes[1]);
            visited[1] = true;

            for (int i = 0; i < 2; i++) {
                int size = q.size();

                for (int j = 0; j < size; j++) {
                    Node curr = q.poll();

                    for (Node friend : curr.friends) {
                        if (!visited[friend.data]) {
                            visited[friend.data] = true;
                            q.add(friend);
                            answer++;
                        }
                    }
                }
            }

            System.out.println("#"+ t + " " + answer);
        }
    }
}
