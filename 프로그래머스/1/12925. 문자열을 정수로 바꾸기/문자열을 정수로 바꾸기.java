class Solution {
    public int solution(String s) {
        int answer = 0;
        int sign = 1;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(i, i+1).equals("-")) {
                sign = -1;
            } else if (s.substring(i, i+1).equals("+")) {
                sign = 1;
            } else {
                answer *= 10;
                answer += Integer.parseInt(s.substring(i, i+1));
            }
        }
        
        return answer * sign;
    }
}