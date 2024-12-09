import java.util.*;
class Solution {
    public List<Integer> solution(int[] numbers) {
        Set<Integer> numSet = new HashSet<>();
        
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                numSet.add(numbers[i] + numbers[j]);
            }
        }
        
        List<Integer> numList = new ArrayList<>(numSet);
        
        Collections.sort(numList);
        return numList;
    }
}