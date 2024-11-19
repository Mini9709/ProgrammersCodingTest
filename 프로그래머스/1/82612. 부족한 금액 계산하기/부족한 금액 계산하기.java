class Solution {
    public long solution(long price, long money, long count) {
        long answer = -1;
        answer = price * (1 + count) * count / 2 - money;
        return (answer > 0) ? answer : 0;
    }
}