package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_2382_미생물_격리 {
	
	static class MSM{
		int r;
		int c;
		int size;
		int dir;
		public MSM(int r, int c, int size, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.dir = dir;
		}
	}
	
	static int N, M, K;
	static int[][][] map;
	static int ans;
	static PriorityQueue<MSM> pq;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			ans = 0;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 
			M = Integer.parseInt(st.nextToken()); // 격리 시간
			K = Integer.parseInt(st.nextToken()); // 미생물 군집 개수
			pq = new PriorityQueue<>((o1, o2) -> (o2.size - o1.size)); // 군집 크기 내림차순
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				pq.offer(new MSM(r, c, size, dir-1));
			}
			
			//시뮬레이션 시작
			for (int m = 0; m < M; m++) {
				map = new int [N][N][2]; // r, c 좌표의 방향과 크기를 저장
				while (!pq.isEmpty()) {
					MSM msm = pq.poll();
					int nr = msm.r + dr[msm.dir];
					int nc = msm.c + dc[msm.dir];
					int size = msm.size;
					
					//가장자리인 경우
					if (nr == 0 || nr == N-1 || nc == 0 || nc == N-1) {
						size /= 2;
						if (msm.dir == 0) {
							msm.dir = 1;
						}else if(msm.dir == 1) {
							msm.dir = 0;
						}else if(msm.dir == 2) {
							msm.dir = 3;
						}else if(msm.dir == 3) {
							msm.dir = 2;
						}
					}
					
					if (map[nr][nc][0] == 0) { //방문 안 한 경우
						map[nr][nc][0] += size;
						map[nr][nc][1] = msm.dir;
					}
					else {
						map[nr][nc][0] += size;
					}
				}
				
				//print();
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j][0] > 0) {
							pq.offer(new MSM(i, j, map[i][j][0], map[i][j][1]));
						}
					}
					
				}//다시 pq에 넣기
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					ans += map[i][j][0];
				}
			}
			
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
		
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j][0] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
