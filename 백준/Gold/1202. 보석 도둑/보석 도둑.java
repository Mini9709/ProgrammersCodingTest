import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int jewels = Integer.parseInt(st.nextToken());
        int bags = Integer.parseInt(st.nextToken());

        Long answer = 0L;

        int[][] jewels_list = new int[jewels][2];
        int[] bags_list = new int[bags];

        PriorityQueue<Integer> answer_pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < jewels; i++) {
            st = new StringTokenizer(br.readLine());

            jewels_list[i][0] = Integer.parseInt(st.nextToken());
            jewels_list[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < bags; i++) {
           bags_list[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels_list, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            } else {
                return o1[0] - o2[0];
            }
        });
        Arrays.sort(bags_list);

        int j = 0;
        for (int i = 0; i < bags_list.length; i++) {
            while (j < jewels_list.length) {
                if (bags_list[i] >= jewels_list[j][0]) {
                    answer_pq.add(jewels_list[j][1]);
                    j++; 
                } else {
                    break;
                }
            }

            if (!answer_pq.isEmpty()) {
                answer += answer_pq.poll();
            }
        }

        System.out.println(answer);
    }
}