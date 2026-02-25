import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

class Cell implements Comparable<Cell> {
	
	int x;
	int y;
	int birth;
	int hp;
	
	public Cell(int x, int y, int birth, int hp) {
		this.x = x;
		this.y = y;
		this.birth = birth;
		this.hp = hp;
	}
	
	@Override
	public int compareTo(Cell o) {
		if (this.birth + this.hp != o.birth + o.hp) {
			return (this.birth + this.hp) - (o.birth + o.hp);
		} else {
			return o.hp - this.hp;
		}
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
        	
        	Set<Long> set = new HashSet<>();
        	PriorityQueue<Cell> pq = new PriorityQueue<>();
        	
        	for (int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		for (int j = 0; j < m; j++) {
        			int temp = Integer.parseInt(st.nextToken());
        			
        			if (temp > 0) {
        				Long key = ((long) j << 32) | (i & 0xFFFFFFFFL); // 좌표값을 set에 저장하기 위해 합쳐서 long값으로 치환
        				set.add(key);
        				pq.offer(new Cell(j, i, 0, temp));
        			}
        		}
        	}
        	
        	int answer = 0;
        	
        	while (!pq.isEmpty()) {
        		int size = pq.size();
        		
        		for (int i = 0; i < size; i++) {
        			Cell curr = pq.poll();
            		
            		if (k >= curr.birth + curr.hp + 1) {
            			for (int[] d : directions) {
            				int nx = curr.x + d[0];
            				int ny = curr.y + d[1];
            				Long key = ((long) nx << 32) | (ny & 0xFFFFFFFFL);
            				
            				if (set.add(key)) {
            					pq.offer(new Cell(nx, ny, curr.birth + curr.hp + 1, curr.hp));
            				}
            			}
            		}
            		
            		if (k < curr.birth + curr.hp + curr.hp) {
            			answer += 1;
            		}
        		}
        	}
        	
        	System.out.println("#" + t + " " + answer);
        }
	}
}