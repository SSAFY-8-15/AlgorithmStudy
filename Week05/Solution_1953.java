package study;

import java.util.*;
import java.io.*;

public class Solution_1953 {

	static class pos{
		int r, c;
		public pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = { 0, 1, 0, -1};
	static int[][] tn = {
			// 시계방향
			{0, 0, 0, 0},
			{1, 1, 1, 1}, // 1
			{1, 0, 1, 0}, // 2
			{0, 1, 0, 1}, // 3
			{1, 1, 0, 0}, // 4
			{0, 1, 1, 0}, // 5
			{0, 0, 1, 1}, // 6
			{1, 0, 0, 1}, // 7
			
			
	};
	
	static int N, M, sR, sC, L;
	static int[][] map;
	static boolean[][] vst;
	static int ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int T = 1; T <= TC; T++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			sR = Integer.parseInt(st.nextToken());
			sC = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
		
			map = new int[N][M];
			vst = new boolean[N][M];
			ans = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = bfs();
			
			System.out.println("#" + T + " " + ans);
		}
		
	}

	private static int bfs() {
		int answer = 1;
		int cnt = 0; //time count
		vst[sR][sC] = true;
		
		Queue<pos> q = new LinkedList<>();
		q.offer(new pos(sR, sC));
		while (!q.isEmpty()) {
			if (cnt == L-1) {
				break;
			}
			
			int size = q.size();
			while (size-- > 0) {
				pos temp = q.poll();
				
				int[] dir = tn[map[temp.r][temp.c]];
				for (int d = 0; d < dir.length; d++) {
					
					if (dir[d] == 0) continue;
					
					int nr = temp.r + dr[d];
					int nc = temp.c + dc[d];
					
					if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;	// 범위 체크
					if (map[nr][nc] == 0) continue;							// 다음칸에 터널이 아예 없는 경우
					if (vst[nr][nc]) continue;							// 이미 방문한 경우
					if (tn[map[nr][nc]][(d + 2) % 4] == 1) { 		// 다음 칸에서 터널이 이어지는 경우에만 진행
						vst[nr][nc] = true;
						q.offer(new pos(nr, nc));
						answer++;
					}
				}
				
			}
			
			cnt++;
		}
		
		return answer;
	}

}
