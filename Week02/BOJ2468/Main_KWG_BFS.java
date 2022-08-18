package DIY.problemSolve;

import java.io.*;
import java.util.*;

// 56628KB, 328ms
public class Main_KWG_BFS {
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
		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };
		for (int startC = 0; startC < n; startC++) {
			for (int startR = 0; startR < n; startR++) {
				if (map[startR][startC] > depth && !hasVisited[startR][startC]) {
					safezone++;
					hasVisited[startR][startC] = true;
					Queue<int[]> q = new LinkedList<int[]>();
					q.offer(new int[] { startR, startC });
					while (!q.isEmpty()) {
						int[] cur = q.poll();
						for (int i = 0; i < 4; i++) {
							int nr = cur[0] + dr[i];
							int nc = cur[1] + dc[i];
							if (rangeCheck(nr, nc) && map[nr][nc] > depth && !hasVisited[nr][nc]) {
								hasVisited[nr][nc] = true;
								q.offer(new int[] { nr, nc });
							}
						}
					}

				}

			}
		}
		return safezone;
	}

	private static boolean rangeCheck(int nr, int nc) {
		return nr >= 0 && nr < n && nc >= 0 && nc < n;
	}

}