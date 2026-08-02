class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int[] parent = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (computers[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        
        int p = -1;
        for (int i = 0; i < n; i++) {
            if (p < find(parent, i)) {
                p = find(parent, i);
                answer++;
            }
        }
        
        return answer;
    }
    
    public int find(int[] parent, int a) {
        if (a == parent[a]) return a;
        else return parent[a] = find(parent, parent[a]);
    }
    
    public void union (int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        
        if (a == b) return;
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
}