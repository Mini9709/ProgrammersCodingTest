import java.util.*;
class Solution {
    public double solution(int[] arr) {
        double answer = 0;
        answer = Arrays.stream(arr).average().orElse(0);
        return answer;
    }
}