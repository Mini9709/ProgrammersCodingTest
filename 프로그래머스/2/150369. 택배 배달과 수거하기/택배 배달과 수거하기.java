class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int total_d = 0;
        int total_p = 0;
        int most_far_d = 0;
        int most_far_p = 0;
        
        for (int i = 0; i < n; i++) {
            total_d += deliveries[i];
            total_p += pickups[i];
            if (deliveries[i] != 0) {
                most_far_d = i+1;
            }
            if (pickups[i] != 0) {
                most_far_p = i+1;
            }
        }
        
        while (total_d > 0 || total_p > 0) {
            int curr_cap = cap;
            answer += Math.max(most_far_p, most_far_d) * 2;
            total_d -= cap;
            total_p -= cap;
            int curr_d = most_far_d-1;
            int curr_p = most_far_p-1;
            
            for (int i = curr_d; i >= 0; i--) {
                if (deliveries[i] > curr_cap) {
                    deliveries[i] -= curr_cap;
                    break;
                } else {
                    curr_cap -= deliveries[i];
                    deliveries[i] = 0;
                    most_far_d--;
                }
            }
            
            curr_cap = cap;
            for (int i = curr_p; i >= 0; i--) {
                if (pickups[i] > curr_cap) {
                    pickups[i] -= curr_cap;
                    break;
                } else {
                    curr_cap -= pickups[i];
                    pickups[i] = 0;
                    most_far_p--;
                }
            }
        }
        
        return answer;
    }
}