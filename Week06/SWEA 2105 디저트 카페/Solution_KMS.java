import java.util.*;
import java.io.*;

public class Solution_2105_디저트_카페{

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int ans;
	static int endR, endC;
	static int[] dr = {1, 1, -1, -1}; //4시 7시 11시 1시
	static int[] dc = {1, -1, -1, 1};
	static boolean[] visitedNum;
	static int[][] tempMap;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			ans = -1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			tempMap = new int[N][N];
			for (int r = 0; r < N-2; r++) {
				for (int c = 1; c < N-1; c++) {
					visited = new boolean[N][N];
					visitedNum = new boolean[101]; //방문한 곳의 숫자 기억
					endR = r; endC =c; //시작점이 끝 
					dfs(r, c, 0, 0); //4시부터 시작
				}
			}
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int r, int c, int dir, int cnt) {		
		if(dir == 4) 
			return;
		
		if (dir == 3) {
			if (r == endR && c == endC) {
				ans = Math.max(ans, cnt);
				return;
			}
		}
		
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		if(nr < 0 || nr >= N || nc < 0 || nc >= N) return;
		if(visited[nr][nc]) return;
		if(visitedNum[map[nr][nc]]) return;
		
		visited[nr][nc] = true;
		visitedNum[map[nr][nc]] = true;
		
		dfs(nr, nc, dir, cnt+1);
		
		dfs(nr, nc, dir+1, cnt+1);
		
		visited[nr][nc] = false;
		visitedNum[map[nr][nc]] = false;
		
	}
}