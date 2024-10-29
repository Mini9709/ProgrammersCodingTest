class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        int min, max;
        if (a > b) {
            min = b;
            max = a;
        } else {
            min = a;
            max = b;
        }
        
        for (long i = min; i < max + 1; i++) {
            answer += i;
        }
        
        return answer;
    }
}