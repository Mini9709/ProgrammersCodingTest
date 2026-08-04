import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        String answer = "";
        long[] changes = new long[bans.length];
        
        for (int i = 0; i < bans.length; i++) {
            changes[i] = changeString(bans[i]);
        }
        
        Arrays.sort(changes);
        
        for (int i = 0; i < bans.length; i++) {
            if (n >= changes[i]) n += 1;
            else break;
        }
        System.out.println(changeString("zz"));
        System.out.println(changeLong(702));
        
        System.out.println(n);
        
        return changeLong(n);
    }
    
    public long changeString(String s) {
        long result = 0;
        for (int i = 0; i < s.length(); i++) {
            result *= 26;
            char c = s.charAt(i);
            result += (c-'a'+1);
        }
        return result;
    }
    
    public String changeLong(long l) {
        StringBuilder sb = new StringBuilder();
        
        while (l-- > 0) {
            long temp = l%26;
            sb.append((char)('a'+temp));
            l /= 26;
        }
        
        return sb.reverse().toString();
    }
}

// 26진법
// 26진법으로 n번째 숫자를 찾기
// bans를 작은 숫자 순으로 정렬
// bans에서 n보다 작은 수 중 가장 큰 수를 이분탐색으로 검색
// 해당 숫자부터 순회를 돌며 n--
// n - (i보다 작은 bans 원소의 수 +1) < i 일 경우 한번 더 n--