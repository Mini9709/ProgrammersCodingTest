import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int g;
	static int p;
	static int answer;
	static boolean[] gates;
	static int[] airplanes;
	static int[] parents;
	
	public static int find(int x) {
		if (parents[x] == x) return x;
		else return parents[x] = find(parents[x]);
	}
	
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());
        
        gates = new boolean[g+1];
        gates[0] = true;
        airplanes = new int[p];
        parents = new int[g+1];
        
        for (int i = 1; i <= g; i++) {
        	parents[i] = i;
        }
        
        for (int i = 0; i < p; i++) {
        	airplanes[i] = Integer.parseInt(br.readLine());
        }
        
        answer = 0;
        
        for (int i = 0; i < p; i++) {
        	if (!gates[find(airplanes[i])]) {
        		answer++;
        		gates[find(airplanes[i])] = true;
        		parents[find(airplanes[i])] -= 1;
        	} else {
        		break;
        	}
        }
        
        System.out.println(answer);
	}

}
