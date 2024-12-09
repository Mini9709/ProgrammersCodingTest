class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        int[] checkedChar = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            int temp = (int)s.charAt(i) - (int)'a';
            if (checkedChar[temp] == 0) {
                answer[i] = -1;
                checkedChar[temp] = i + 1;
            } else {
                answer[i] = i - checkedChar[temp] + 1;
                checkedChar[temp] = i + 1;
            }
        }
        return answer;
    }
}