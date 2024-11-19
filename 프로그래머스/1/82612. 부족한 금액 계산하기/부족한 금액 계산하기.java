class Solution {
    public long solution(int price, int money, int count) {
        long answer = -1;
        answer = (long)price * (long)(1 + count)*count/2 - money;
        System.out.println(answer);
        if (answer < 0) {
            answer = 0;
        }
        return answer;
    }
}