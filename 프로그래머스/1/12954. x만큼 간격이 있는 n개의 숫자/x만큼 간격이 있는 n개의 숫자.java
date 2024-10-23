class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        long number = 0;
        for (int i = 0; i < n; i++){
            number += x;
            answer[i] = number;
        }
        return answer;
    }
}