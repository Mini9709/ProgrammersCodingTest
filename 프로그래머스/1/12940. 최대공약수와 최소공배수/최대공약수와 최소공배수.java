class Solution {
    public int[] solution(int n, int m) {
        
        int[] answer = new int[2];
        int min = (n > m ? m : n);
        int max = (n > m ? n : m);
        int temp;
        
        while (min != 0) {
            temp = min;
            min = max % min;
            max = temp;
        }
        
        answer[0] = max;
        answer[1] = n * m / max;
        
        return answer;
    }
}