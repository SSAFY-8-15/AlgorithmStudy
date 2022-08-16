package DIY.problemSolve;

import java.io.*;
import java.util.*;

//67392KB, 276ms
public class Main_KWG_DFS {
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		StringTokenizer st;
		int map[][] = new int[n][n];
		int minHeight = Integer.MAX_VALUE;
		int maxHeight = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (minHeight > map[i][j])
					minHeight = map[i][j];
				if (maxHeight < map[i][j])
					maxHeight = map[i][j];
			}
		}

		int height = minHeight;
		int maxSafeNum = 1;
		for (int i = minHeight; i < maxHeight; i++) {
			int curSafeNum = getSafeNum(map, i);
			if (curSafeNum > maxSafeNum) {
				maxSafeNum = curSafeNum;
				height = i;
			}
		}
		System.out.println(maxSafeNum);
	}

	private static int getSafeNum(int[][] map, int depth) {
		int safezone = 0;
		boolean[][] hasVisited = new boolean[n][n];
		
		for (int startR = 0; startR < n; startR++) {
			for (int startC = 0; startC < n; startC++) {
				if (map[startR][startC] > depth && !hasVisited[startR][startC]) {
					safezone++;
					hasVisited[startR][startC] = true;
					dfs(startR,startC,depth,map,hasVisited);
				}
			}
		}
		return safezone;
	}
	
	private static void dfs(int r, int c, int depth, int[][] map, boolean[][] hasVisited) {
		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (rangeCheck(nr, nc) && map[nr][nc] > depth && !hasVisited[nr][nc]) {
				hasVisited[nr][nc] = true;
				dfs(nr,nc,depth,map,hasVisited);
			}
		}
	}

	private static boolean rangeCheck(int nr, int nc) {
		return nr >= 0 && nr < n && nc >= 0 && nc < n;
	}
}
