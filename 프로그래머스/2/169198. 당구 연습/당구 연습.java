class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
            int x = balls[i][0];
            int y = balls[i][1];
            int ans = Integer.MAX_VALUE;
            
            int[][] arr = {{startX, startY, x, y}, {m-startX, startY, m-x, y}, {startY, startX, y, x}, {n-startY, startX, n-y, x}};
            for (int[] a : arr) {
                if (a[1] == a[3] && a[0] > a[2]) {
                    continue;
                } else {
                    int temp = (a[0]+a[2]) * (a[0]+a[2]) + (a[1]-a[3]) * (a[1]-a[3]);
                    ans = Math.min(ans, temp);
                }
            }
            
            answer[i] = ans;
        }
        
        return answer;
    }
}