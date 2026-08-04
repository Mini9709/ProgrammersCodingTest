import java.util.*;

class Solution {
    
    static int answer;
    static int start;
    static int end;
    static boolean[] visited;
    static List<Integer>[] graph;
    
    public int solution(String begin, String target, String[] words) {
        answer = 0;
        graph = new ArrayList[words.length+1];
        start = words.length;
        end = -1;
        visited = new boolean[words.length+1];
        for (int i = 0; i <= words.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < words.length; i++) {
            for (int j = i+1; j < words.length; j++) {
                if (checkWord(words[i], words[j])) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
            if (words[i].equals(target)) end = i;
        }
        
        for (int i = 0; i < words.length; i++) {
            if (checkWord(begin, words[i])) {
                graph[i].add(start);
                graph[start].add(i);
            }
        }
        
        visited[start] = true;
        
        if (end != -1) {
            dfs(0, start);
        }
        
        return answer;
    }
    
    public void dfs(int count, int s) {
        if (count >= answer && answer != 0) return;
        
        if (s == end) {
            answer = answer == 0 ? count : Math.min(answer, count);
        }
        
        for (int n : graph[s]) {
            if (!visited[n]) {
                visited[n] = true;
                dfs(count+1, n);
                visited[n] = false;
            }
        }
    }
    
    public boolean checkWord(String b, String t) {
        int diff = 0;
        for (int i = 0; i < b.length(); i++) {
            if (b.charAt(i) != t.charAt(i)) diff++;
        }
        
        return diff==1;
    }
}

// 각 워드마다 알파벳이 하나만 다를 시 간선으로 연결(l^n^2 = 10*2500)
// 간선이 모두 연결되면 시작점을 begin 노드로 잡고 목적지를 target 노드로 잡음
// 최단거리 산출 dfs or 다익스트라