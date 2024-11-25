class Solution {
    public String solution(String s) {
        String answer = "";
        String[] array = s.split("");
        int idx = 0;
        
        for (String t : array) {
            idx = (t.equals(" ") ? 0 : idx + 1);
            answer += (idx % 2 == 0 ? t.toLowerCase() : t.toUpperCase());
        }
        return answer;
    }
}