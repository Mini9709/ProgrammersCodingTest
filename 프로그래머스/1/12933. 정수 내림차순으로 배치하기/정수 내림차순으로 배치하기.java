class Solution {
    public long solution(long n) {
        long answer = 0;
        long[] numCount = {0,0,0,0,0,0,0,0,0,0};
        
        while (n > 0) {
            long temp = n % 10;
            numCount[(int) temp] += 1;
            n /= 10;
        }
        
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < numCount[i]; j++) {
                answer *= 10;
                answer += i;
            }
        }
        
        return answer;
    }
}