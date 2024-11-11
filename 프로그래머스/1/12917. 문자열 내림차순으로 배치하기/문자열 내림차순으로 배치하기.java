import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        
        String[] str = s.split("");
        Arrays.sort(str, Collections.reverseOrder());
        
        for (String t : str) {
            answer += t;
        }
        
        return answer;
    }
}