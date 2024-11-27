class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        
        for(int i = 0; i < t.length() - p.length() + 1; i++)  {
            String temp = t.substring(i, i + p.length());
            answer = (Long.parseLong(p) >= Long.parseLong(temp)) ? answer + 1 : answer;
        }
        
        return answer;
    }
}