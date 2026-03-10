import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

@SuppressWarnings("unchecked")
public class Main {

	static int n;
	static int m;
	static int l;
	static int answer;
	static int[] needPotion;
	static int[] resultPotion;
	static boolean[] potion;
	static ArrayList<Integer>[] ingredients;
	
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        needPotion = new int[m];
        resultPotion = new int[m];
        ingredients = new ArrayList[n+1];
        
        for (int i = 1; i <= n; i++) {
        	ingredients[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	needPotion[i] = Integer.parseInt(st.nextToken());
        	
        	for (int j = 0; j < needPotion[i]; j++) {
        		ingredients[Integer.parseInt(st.nextToken())].add(i);
        	}
        	
        	resultPotion[i] = Integer.parseInt(st.nextToken());
        }
        
        l = Integer.parseInt(br.readLine());
        Queue<Integer> q = new ArrayDeque<>();
        potion = new boolean[n+1];
        answer = 0;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < l; i++) {
        	int idx = Integer.parseInt(st.nextToken());
        	q.add(idx);
        	potion[idx] = true;
        }   
        
        while(!q.isEmpty()) {
        	int curr = q.poll();
        	
        	for (int r : ingredients[curr]) {
        		if (!potion[resultPotion[r]] && --needPotion[r] == 0) {
        			potion[resultPotion[r]] = true;
        			q.add(resultPotion[r]);
        		}
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i <= n; i++) {
        	if (potion[i]) {
        		answer++;
        		sb.append(i + " ");
        	}
        }
        
        System.out.println(answer);
        System.out.println(sb);
	}

}