import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

class Micro {
	int count;
	int move;
	int maxCount;
	
	public Micro (int count, int move) {
		this.count = count;
		this.move = move;
		maxCount = count;
	}
}

public class Solution {

	static int n;
	static int m;
	static int k;
	static int[][] map;
	static int[][] micro;
	static int[][] directions = {{0, 0}, {0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	static int[] reverse = {0, 2, 1, 4, 3};
	
	public static HashMap<Long, Micro> move(HashMap<Long, Micro> micro) {
		HashMap<Long, Micro> after = new HashMap<>(k);
		Iterator<Map.Entry<Long, Micro>> it = micro.entrySet().iterator();

		while(it.hasNext()) {
			Entry<Long, Micro> curr = it.next();
			
			int x = (int) (curr.getKey() >> 32);
			int y = (int) (curr.getKey() & 0xFFFFFFFFL);
			int count = curr.getValue().count;
			int move = curr.getValue().move;
			curr.getValue().maxCount = count;
			
			int nx = x + directions[move][0];
			int ny = y + directions[move][1];
			Long key = ((long) nx << 32) | (ny & 0xFFFFFFFFL);
			
			if (after.containsKey(key)) {
				if (after.get(key).maxCount < count) {
					after.get(key).move = move;
					after.get(key).maxCount = count;
				}
				after.get(key).count += count;
			} else {
				if (nx == 0 || nx == n-1 || ny == 0 || ny == n-1) {
    				count /= 2;
    				move = reverse[move];
    				
    				if (count == 0) {
    					continue;
    				}
    			}
				after.put(key, new Micro(count, move));
			}
		}
		
		return after;
	}
	
	public static void main(String[] args) throws IOException {
	   	 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= testCase; t++) {
        	st = new StringTokenizer(br.readLine());
        	
        	n = Integer.parseInt(st.nextToken());
        	m = Integer.parseInt(st.nextToken());
        	k = Integer.parseInt(st.nextToken());
        	HashMap<Long, Micro> micro = new HashMap<>(); // 0 : 세로, 1 : 가로, 2 : 미생물 수, 3 : 이동 방향
        	
        	for (int i = 0; i < k; i++) {
        		st = new StringTokenizer(br.readLine());

    			int y = Integer.parseInt(st.nextToken());
    			int x = Integer.parseInt(st.nextToken());
    			
    			Long key = ((long) x << 32) | (y & 0xFFFFFFFFL);
    			
    			micro.put(key, new Micro(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        	}
        	
        	int time = 0;
        	int answer = 0;
        	
        	while (time < m) {
        		micro = move(micro);
        		time++;
        	}
        	
        	for (Entry<Long, Micro> e : micro.entrySet()) {
        		if (e.getValue().count != 0) {
        			answer += e.getValue().count;
        		}
        	}
        	
        	System.out.println("#" + t + " " + answer);
        }
	}

}
