package study;

import java.io.*;
import java.util.*;

public class Solution_1949 {

	static int N, K;
	static int[][] map;
	static int[][] temp;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static ArrayList<int[]> tops;
	
	static int ans;
	
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int T = 1; T <= TC; T++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			temp = new int[N][N];
			ans = Integer.MIN_VALUE;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < K+1; k++) {
						mapcopy(map);
						getMaxh(temp); //최고높이 좌표들 리스트에넣기
						temp[i][j] -= k;
						for (int idx = 0; idx < tops.size(); idx++) {
							int sr = tops.get(idx)[0];
							int sc = tops.get(idx)[1];
							bfs(sr, sc, 0);
						}
					}
				}
			}
			ans++;
			System.out.println("#"+T+" "+ans);
			
		}
		
	}


	private static void bfs(int sr, int sc, int cnt) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {sr, sc, cnt});
		int total = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cr = cur[0];
			int cc = cur[1];
			int ccnt = cur[2];
			
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if (temp[nr][nc] >= temp[cr][cc]) continue;
				int ncnt = ccnt + 1;
				q.offer(new int[] {nr, nc, ncnt});
				total = Math.max(total, ncnt);
			}
		}
		ans = Math.max(ans, total)+1;
		
	}




	private static void getMaxh(int[][] m) {
		tops= new ArrayList<>();
		int mh = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				mh = Math.max(mh, m[i][j]);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (m[i][j] == mh) {
					tops.add(new int[] {i,j});
				}
			}
		}
	}


	private static void mapcopy(int[][] og) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int t = og[i][j];
				temp[i][j] = t;
			}
		}
	}
	

}
