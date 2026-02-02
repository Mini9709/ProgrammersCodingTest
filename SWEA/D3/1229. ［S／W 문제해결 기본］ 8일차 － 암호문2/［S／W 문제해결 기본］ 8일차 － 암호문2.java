import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			int len = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			LinkedList<Integer> map = new LinkedList<>();
			
			for (int i = 0; i < len; i++) {
				map.add(Integer.parseInt(st.nextToken()));
			}
			
			int orderCount = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < orderCount; i++) {
				String order = st.nextToken();
				
				if (order.equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int j = 0; j < y; j++) {
						map.add(x+j, Integer.parseInt(st.nextToken()));
					}
					
				} else if (order.equals("D")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int j = 0; j < y; j++) {
						map.remove(x);
					}
				}
			}
			
			System.out.print("#" + t + " ");
			for (int i = 0; i < 10; i++) {
				System.out.print(map.get(i) + " ");
			}
			System.out.println();
		}
	}
}
