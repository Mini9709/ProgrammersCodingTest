class Solution {
    public int solution(String s) {
        int answer = 0;
        int i = 0;
        
        while (s.length() > i) {
            answer *= 10;
            System.out.println(answer + " : pre");
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                System.out.println(s.charAt(i));
                answer += Integer.parseInt(s.substring(i,i+1));
                i++;
            } else {
                System.out.println(s.substring(i, i+3));
                switch(s.substring(i, i+3)) {
                    case "zer" -> {answer += 0; i += 4;}
                    case "one" -> {answer += 1; i += 3;}
                    case "two" -> {answer += 2; i += 3;}
                    case "thr" -> {answer += 3; i += 5;}
                    case "fou" -> {answer += 4; i += 4;}
                    case "fiv" -> {answer += 5; i += 4;}
                    case "six" -> {answer += 6; i += 3;}
                    case "sev" -> {answer += 7; i += 5;}
                    case "eig" -> {answer += 8; i += 5;}
                    case "nin" -> {answer += 9; i += 4;}
                }
            }
            System.out.println(answer + " : answer");
        }
        
        return answer;
    }
}