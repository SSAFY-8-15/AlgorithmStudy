package study;

import java.util.*;
import java.io.*;

public class Solution_5653 {

	static int N, M, K;
	static int[][] map;
	static boolean[][] vst;
	static Queue<Cell> temp;
	static PriorityQueue<Cell> pq;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	
	static int ans;
	
	static class Cell implements Comparable<Cell>{
		int r, c, hp, cp; // health_point, current_point

		public Cell(int r, int c, int hp, int cp) {
			super();
			this.r = r;
			this.c = c;
			this.hp = hp;
			this.cp = cp;
		}

		@Override
		public int compareTo(Cell o) {
			return -Integer.compare(hp, o.hp);
		}
		
	}
	
	
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int T = 1; T <= TC; T++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N+2*K][M+2*K];
			vst = new boolean[N+2*K][M+2*K];
			
			pq = new PriorityQueue<>();
			temp = new LinkedList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num !=0) {
						map[K+i][K+j] = num;
						pq.offer(new Cell(K+i,K+j,num,num*2));
						vst[K+i][K+j] = true;
						
					}
				}
			}
			
			
			bfs();
			
			
			System.out.println("#"+T+" "+pq.size());
		}
		
	}
	
	
	private static void bfs() {

		for(int i = 0 ; i < K ; i++) {
			while(!pq.isEmpty()) {
				Cell cur = pq.poll();
				
				cur.cp=cur.cp-1;
				if(cur.hp > cur.cp) {
					for(int d=0; d<4; d++) {
						int nr= cur.r + dr[d];
						int nc= cur.c + dc[d];
						if(0>nr || nr>N+2*K || 0>nc || nc> M+2*K) continue;
						
						if(vst[nr][nc]==false) {
							vst[nr][nc]=true;
							temp.offer(new Cell(nr, nc, cur.hp, cur.hp*2));
						}
					}
				}
				// 죽지 않은 경우 
				if(cur.cp != 0) {
					temp.offer(new Cell(cur.r, cur.c, cur.hp, cur.cp));
				}
			}
			
			while(!temp.isEmpty()) {
				pq.offer(temp.poll());
			}
		}

	}

}
