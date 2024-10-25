class Solution {
    public long solution(long n) {
        long answer = 0;
        double sqrtNumber = Math.sqrt(n);
        long longNumber = (long) sqrtNumber;
        
        if ((double) longNumber == sqrtNumber) {
            answer = (longNumber + 1) * (longNumber + 1);
        } else {
            answer = -1;
        }
        
        return answer;
    }
}