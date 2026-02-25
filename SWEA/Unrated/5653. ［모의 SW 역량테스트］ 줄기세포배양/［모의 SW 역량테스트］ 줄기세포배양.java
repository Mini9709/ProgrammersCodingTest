import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
 
class Cell implements Comparable<Cell> {
 
    int x;
    int y;
    int hp;
    int offTime = 0;
    int onTime = 0;
     
    public Cell(int x, int y, int hp) {
        this.x = x;
        this.y = y;
        this.hp = hp;
    }
     
    @Override
    public int compareTo(Cell o) {
        return o.hp - this.hp;
    }
}
 
public class Solution {
 
    static int n;
    static int m;
    static int k;
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
     
    public static void main(String[] args) throws IOException {
          
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        int testCase = Integer.parseInt(br.readLine());
         
        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
             
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
             
            PriorityQueue<Cell> pq = new PriorityQueue<>();
            Set<Long> set = new HashSet<>();
             
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                 
                for (int j = 0; j < m; j++) {
                    int temp = Integer.parseInt(st.nextToken());
                     
                    if (temp > 0) {
                        Long a = ((long) j << 32) | (i & 0xFFFFFFFFL);
                        pq.offer(new Cell(j, i, temp));
                        set.add(a);
                    }
                }
            }
             
            int time = 0;
            Queue<Cell> q = new ArrayDeque<>();
             
            while (time < k) {
                int size = pq.size();
                 
                for (int i = 0; i < size; i++) {
                    Cell curr = pq.poll();
                    if (curr.offTime == curr.hp) {
                        curr.onTime += 1;
                    } else {
                        curr.offTime += 1;
                    }
                     
                    if (curr.onTime == 1) {
                        for (int[] d : directions) {
                            int nx = curr.x + d[0];
                            int ny = curr.y + d[1];
                             
                            int setSize = set.size();
                            set.add(((long) nx << 32) | (ny & 0xFFFFFFFFL));
                             
                            if (set.size() != setSize) {
                                q.offer(new Cell(nx, ny, curr.hp));
                            }
                        }
                    }
                     
                    if (curr.onTime < curr.hp) {
                        q.offer(curr);
                    }
                }
                 
                while(!q.isEmpty()) {
                    pq.offer(q.poll());
                }
                 
                time++;
            }
             
            System.out.println("#" + t + " " + pq.size());
        }
    }
}