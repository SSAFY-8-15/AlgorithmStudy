package Week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15558_점프_게임 {

	static char[][] lines;
	static int N;
	static int clear = 0;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		lines = new char[2][N];
		visited = new boolean[2][N];
		
		for (int i = 0; i < 2; i++) {
			lines[i] = br.readLine().toCharArray();
		}
		dfs(0,0,0,K);
		System.out.println(clear);
	}

	private static void dfs(int i, int j, int time, int k) {
		if(clear == 1) {
			return;
		}
		visited[i][j] = true;
		if (check(i, j+1, time+1, k)) {
			visited[i][j+1] = true;
			dfs(i, j+1, time+1, k);
			visited[i][j+1] = false;
		}
		if (check(i, j-1, time+1, k)) {
			visited[i][j-1] = true;
			dfs(i, j-1, time+1, k);
			visited[i][j-1] = false;
		}
		if (check((i+1)%2, j+k, time+1, k)) {
			visited[(i+1)%2][j+k] = true;
			dfs((i+1)%2, j+k, time+1, k);
			visited[(i+1)%2][j+k] = false;
		}
	}

	private static boolean check(int i, int j, int time, int k) {
		if (j >= N || clear == 1)  {
			clear = 1;
			return false;
		}
		
		if (j < 0 || lines[i][j] == '0' || j < time || visited[i][j]) {
			return false; 
		}
		
		return true;
	}

}
