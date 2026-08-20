import java.util.*;

class Solution {
    public int solution(int[][] cost, int[][] hint) {
        int answer = 0;
        
        for (int i = 0; i < cost.length; i++) {
            answer += cost[i][0];
        }
        
        for (int i = 1; i < 1 << hint.length; i++) {
            int[] hasHint = new int[cost.length];
            int result = 0;
            
            for (int j = 0; j < hint.length; j++) {
                if ((i & 1 << j) > 0) {
                    result += hint[j][0];
                    for (int k = 1; k < hint[j].length; k++) {
                        hasHint[hint[j][k]-1] += 1;
                    }
                }
            }
            
            for (int k = 0; k < cost.length; k++) {
                if (hasHint[k] >= cost[k].length) {
                    hasHint[k] = cost[k].length-1;
                }
                result += cost[k][hasHint[k]];
            }
            
            answer = Math.min(answer, result);
        }
        
        return answer;
    }
}