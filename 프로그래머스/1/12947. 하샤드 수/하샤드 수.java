class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        int harshad = 0;
        int y = x;
        
        while (x > 0) {
            int temp = x % 10;
            harshad += temp;
            x /= 10;
        }
        
        if (y % harshad == 0) {
            answer = true;
        } else {
            answer = false;
        }
        
        return answer;
    }
}