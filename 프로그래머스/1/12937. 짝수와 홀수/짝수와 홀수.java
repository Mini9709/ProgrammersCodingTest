class Solution {
    public String solution(int num) {
        String answer = "";
        if (Math.abs(num) % 2 == 1){
            answer = "Odd";
        } else {
            answer = "Even";
        }
        return answer;
    }
}