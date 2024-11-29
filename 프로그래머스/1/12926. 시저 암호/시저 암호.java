class Solution {
    public String solution(String s, int n) {
        String answer = "";
        
        char[] ch = s.toCharArray();
        
        for (char c : ch) {
            if (c == ' ') {
                answer += c;
            } else {
                if (c >= 'a' && c <= 'z') {
                    c += (c+n > 'z') ? n - 26 : n;
                } else {
                    c += (c+n > 'Z') ? n - 26 : n;
                }
                answer += c;
            }
        }
        
        return answer;
    }
}