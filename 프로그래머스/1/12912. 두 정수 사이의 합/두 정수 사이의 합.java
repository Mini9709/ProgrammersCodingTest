class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        long sum = a + b;
        long sub = Math.abs(a - b) + 1;
        
        answer = sum * sub / 2;
        return answer;
    }
}