import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;
	static int answer;
	static int[] parent;
	
	public static int find(int x) {
		if (parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		x = parent[x];
		y = parent[y];
		
		if (x < y) parent[y] = x;
		else parent[x] = y;
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        answer = 0;
        parent = new int[n];
        
        for (int i = 0; i < n; i++) {
        	parent[i] = i;
        }
        
        for (int i = 1; i <= m; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	if (find(x) == find(y) && answer == 0) {
        		answer = i;
        	} else {
        		union(x, y);
        	}
        	
        }
        
        System.out.println(answer);
	}

}
