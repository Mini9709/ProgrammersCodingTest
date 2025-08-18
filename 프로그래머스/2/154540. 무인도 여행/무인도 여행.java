import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Comparator;

class Solution {
    
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int[][] island_map;
    boolean[][] visited;
    
    public ArrayList<Integer> solution(String[] maps) {
        
        ArrayList<Integer> answer = new ArrayList<>();
        island_map = new int[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()]; 
        
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (maps[i].charAt(j) == 'X') {
                    island_map[i][j] = 0;
                } else {
                    island_map[i][j] = maps[i].charAt(j) - '0';
                }
            }
        }
        
        Queue<Integer[]> q = new LinkedList<>();
        int max_x = island_map[0].length;
        int max_y = island_map.length;
        
        for (int y = 0; y < max_y; y++) {
            for (int x = 0; x < max_x; x++) {
                if (!visited[y][x] && island_map[y][x] != 0) {
                    q.add(new Integer[]{x, y});
                    int count = island_map[y][x];
                    visited[y][x] = true;
                    
                    while (!q.isEmpty()) {
                        Integer[] curr_point = q.poll();
                        
                        for (int i = 0; i < 4; i++) {
                            int next_x = curr_point[0] + directions[i][0];
                            int next_y = curr_point[1] + directions[i][1];
                            
                            if (next_x >= 0 && next_x < max_x && next_y >= 0 && next_y < max_y) {
                                if (!visited[next_y][next_x] && island_map[next_y][next_x] != 0) {
                                    count += island_map[next_y][next_x];
                                    visited[next_y][next_x] = true;
                                    q.add(new Integer[]{next_x, next_y});
                                }
                            }
                        }
                    }
                    
                    answer.add(count);
                }
            }
        }
        answer.sort(Comparator.naturalOrder());
        
        if (answer.isEmpty()) {
            answer.add(-1);
        }
        
        return answer;
    }
}