class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int w = 0;
        int h = 0;
        for (int i = 0; i < sizes.length; i++) {
            int max = (sizes[i][0] > sizes[i][1]) ? sizes[i][0] : sizes[i][1];
            int min = (sizes[i][0] > sizes[i][1]) ? sizes[i][1] : sizes[i][0];
            w = (w > max) ? w : max;
            h = (h > min) ? h : min;
        }
        
        answer = w * h;
        return answer;
    }
}