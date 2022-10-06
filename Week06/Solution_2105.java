package study;

import java.io.*;
import java.util.*;

public class Solution_2105 {

	static int N;
	static int[][] map;
	static boolean[] nums;
	static int ans;
	
	static int sr;
	static int sc;
	
	static int[] dr = {1, 1, -1, -1}; //다이아몬드방향
	static int[] dc = {1, -1, -1, 1};
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int T = 1; T <= TC; T++) {
		
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			nums = new boolean[101];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
			
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					ans = -1;
					sr = i;
					sc = j;
					dfs(i, j, 0, 0);
				}
			}
			
			System.out.println("#" + T + " " + ans);
			
		}
		
	}


	private static void dfs(int r, int c, int cnt, int curD) {
		if (!inRange(r, c)) return;
		
		if (cnt > 1 && r==sr && c==sc) {
			ans = Math.max(ans, cnt);
			return;
		}
		
		for (int d = curD; d <= curD+1; d++) {
			if (d > 3) break;
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (inRange(nr,nc) && !nums[map[nr][nc]]) {
				nums[map[nr][nc]] = true;
				dfs(nr, nc, cnt+map[nr][nc], d);
				nums[map[nr][nc]] = false;
			}
		}
	}


	private static boolean inRange(int nr, int nc) {
		return (nr >= 0 && nr < N && nc >= 0 && nc < N);
	}

}
