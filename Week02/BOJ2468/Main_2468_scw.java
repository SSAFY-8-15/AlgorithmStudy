package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2468_scw {

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int N;
	static int[][] map;
	static boolean[][] vst;

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
	
		int maxH = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, map[i][j]);
			}
		}
		
		int safe = 0;
		
		for (int h = 0; h <= maxH; h++) {
			vst = new boolean[N][N];
			int gc = 0; // groupcount
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > h && !vst[i][j]) {
						gc += bps(i, j, h);					
					}
				}
			}
			safe = Math.max(safe, gc);
		}
		
		System.out.println(safe);
	}
	private static int bps(int r, int c, int hh) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r,c});
		vst[r][c] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int rr = cur[0];
			int cc = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = rr + dr[d];
				int nc = cc + dc[d];
				
				if (!check(nr,nc) || vst[nr][nc]) continue;
				if (map[nr][nc] > hh){
					vst[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}

			}
			
		}
		return 1;
		
	}
	private static boolean check(int r, int c) {
		return (r >= 0 && r < N && c >=0 && c < N);
	}

}
