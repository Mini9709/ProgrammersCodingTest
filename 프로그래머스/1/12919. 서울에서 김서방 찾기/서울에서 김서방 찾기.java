class Solution {
    public String solution(String[] seoul) {
        String answer = "";
        int idx = 0;
        
        for (String str : seoul) {
            if (str.equals("Kim")) {
                answer = "김서방은 " + idx + "에 있다";
            } else {
                idx += 1;
            }
        }
        
        return answer;
    }
}