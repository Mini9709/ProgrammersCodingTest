class Solution {
    public String solution(int a, int b) {
        String[] weekday = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        int[] month = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int day = 4;
        
        for (int i = 0; i < a - 1; i++) {
            day += month[i];
        }
        
        day = (day + b) % 7;
        
        return weekday[day];
    }
}