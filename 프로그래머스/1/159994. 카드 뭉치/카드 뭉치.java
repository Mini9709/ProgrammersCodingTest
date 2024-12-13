import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";
        int i = 0;
        Queue<String> deque1 = new LinkedList<>(Arrays.asList(cards1));
        Queue<String> deque2 = new LinkedList<>(Arrays.asList(cards2));
        
        while(i < goal.length) {
            String s = goal[i];
            if (!deque1.isEmpty() && deque1.peek().equals(s)) {
                deque1.poll();
            } else if (!deque2.isEmpty() && deque2.peek().equals(s)) {
                deque2.poll();
            } else {
                break;
            }
            i++;
        }
        
        return (i == goal.length) ? "Yes" : "No";
    }
}