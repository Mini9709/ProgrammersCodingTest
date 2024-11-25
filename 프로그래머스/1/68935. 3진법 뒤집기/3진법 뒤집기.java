import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        int idx = 0;
        int[] three = new int[99];
        
        while (n > 0) {
            three[idx] = n % 3;
            n /= 3;
            System.out.println(three[idx]);
            idx += 1;
        }
        
        for (int i = 0; i < idx; i++) {
            answer += three[idx-i-1] * Math.pow(3, i);
        }
        
        return answer;
    }
}