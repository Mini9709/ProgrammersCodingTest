class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        for (int i = left; i <= right; i++) {
            int count = 1;
            for (int j = 1; j <= i/2; j++) {
                if (i % j == 0) {
                    count++;
                }
            }
            System.out.println(answer);
            if (count % 2 == 0) {
                answer += i;
            } else {
                answer -= i;
            }
        }
        return answer;
    }
}