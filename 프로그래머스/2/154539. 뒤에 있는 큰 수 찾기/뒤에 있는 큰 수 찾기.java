import java.util.Stack;

class Solution {
    public int[] solution(int[] numbers) {
    
        int[] answer = new int[numbers.length];
        Stack<Integer[]> stack = new Stack<>();
        
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            
            while (!stack.isEmpty()) {
                if(num > stack.peek()[0]) {
                    Integer[] temp = stack.pop();
                    answer[temp[1]] = num;
                } else {
                    stack.push(new Integer[]{num, i});
                    break;
                }
            }
            
            if (stack.isEmpty()) {
                stack.push(new Integer[]{num, i});
            }
                
        }
        
        while(!stack.isEmpty()) {
            answer[stack.pop()[1]] = -1;
        }
        
        return answer;
    }
}

/*[9, 1] 다음 5
크면 1 나오고 5
작으니 9 그대로 5 들어가기
[9, 5] 다음 3
5보다 작으니 3 들어가기
[9,5,3] 다음 6
3보다 크니까 3 나오고 6 기입
5보다 크니까 5 나오고 6 기입
9보다 작으니 6 들어가기*/