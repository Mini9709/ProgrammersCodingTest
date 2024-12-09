import java.util.*;
class Solution {
    public Set<Integer> solution(int[] numbers) {
        Set<Integer> numSet = new TreeSet<>();
        
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                numSet.add(numbers[i] + numbers[j]);
            }
        }

        return numSet;
    }
}