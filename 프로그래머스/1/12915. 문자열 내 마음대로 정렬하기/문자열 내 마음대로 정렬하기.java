import java.util.Arrays;

class Solution {
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings);
        Arrays.sort(strings, (a, b) -> a.charAt(n) - b.charAt(n));
        return strings;
    }
}