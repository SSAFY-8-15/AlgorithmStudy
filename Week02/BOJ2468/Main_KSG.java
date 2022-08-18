package week2.boj2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_KSG {

	static int max = 0;
	
	static int[] dr = {1,-1,0,0};
	
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] table = new int[n+2][n+2];
		Set<Integer> set = new HashSet<>();
		for(int i=1; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
				set.add(table[i][j]);
			}
		}
		set.add(0);
		int[][] visited = new int[n+2][n+2];
		int k = 1;
		for(int h : set) {
			int cnt = 0;
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(table[i][j] > h && visited[i][j] < k) {
						cnt++;
						dfs(table,visited,i,j,k,h);
					}
				}
			}
			max = Math.max(max,cnt);
			k++;
		}
		System.out.println(max);
		br.close();
	}

	private static void dfs(int[][] table, int[][] visited, int i, int j, int k, int limit) {
		visited[i][j] = k;
		for(int d=0; d<4; d++) {
			int r = i+dr[d];
			int c = j+dc[d];
			if(table[r][c] > limit && visited[r][c] < k) {
				dfs(table,visited,r,c,k,limit);
			}
		}
		
	}

}
