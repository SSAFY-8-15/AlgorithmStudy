package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5653_줄기세포배양 {

	static class Cell{
		int r;
		int c;
		int power;
		int actTime;
		int life;
		
		public Cell(int r, int c, int p, int t, int l) {
			this.r = r;
			this.c = c;
			this.power = p; // 생명력
			this.actTime = t; // t초후 활성화
			this.life = l; // 목숨
		}
	}
	
	static int N, M, K;
	static int[][] map;
	static boolean[][] visited;
	static PriorityQueue<Cell> pq;
	static Queue<Cell> Cells;
	static ArrayList<Cell> idle;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int row;
	static int col;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N + 2*K][M + 2*K];
			int ans = 0;
			Cells = new LinkedList<>();
			pq = new PriorityQueue<>(new Comparator<Cell>() {
				@Override
				public int compare(Cell o1, Cell o2) {
					if (o1.power == o2.power) {
						return o1.actTime - o2.actTime; //시간 오름차순
					}
					return o2.power-o1.power; // 생명력 내림차순
				}
			});
			
			//Queue<Cell> q = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " "); 
				for (int j = 0; j < M; j++) {
					map[i+K][j+K] = Integer.parseInt(st.nextToken());
					if (map[i+K][j+K] > 0 ) {
						Cells.add(new Cell(i+K, j+K, map[i+K][j+K], // 파워
													map[i+K][j+K]+1, // 활성화 시간
													map[i+K][j+K]));// 목숨 life
					}	
				}
			}
			
			idle = new ArrayList<>();
			
			//시뮬 시작
			for (int curTime = 1; curTime <= K; curTime++) {
				int size = Cells.size();
				for (int i = 0; i < size; i++) { //while
					Cell cell = Cells.poll();				
					if (cell.actTime == curTime) pq.add(cell); //시간이 맞으면 active에 다 넣기
					else Cells.add(cell); // 시간이 다르면 다시 큐에
				}
				
				while(!pq.isEmpty()) { // active인 애들만
					Cell cell = pq.poll();
					int r = cell.r;
					int c = cell.c;
					int power = cell.power;
					int actTime = cell.actTime;
					int life = cell.life;
					
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (!check(nr, nc)) return;
						if(map[nr][nc] > 0) continue; //이미 방문 
						map[nr][nc] = curTime; // 방문 체크. 생명력
						
						// 생명력, 활성화 시간, 목숨
						Cells.offer(new Cell(nr, nc, power, curTime+power+1, life));	
					}
					
					idle.add(new Cell(r, c, power, actTime, life));// 한번 써서 안쓰는 애들 넣는 곳
				}
				
				for (int i = idle.size()-1; i > -1 ; i--) { // 삭제는 뒤에서 부터
					idle.get(i).life--;
					if (idle.get(i).life == 0) {
						idle.remove(i);
					}
				}
			}
			ans = Cells.size() + idle.size();
			sb.append("#"+ t + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	private static boolean check(int nr, int nc) {
		if (nr < 0 || nr >= 2*K+N || nc < 0 || nc >= 2*K+M) {
			System.out.println("여기 오면 안됨");
			return false;
		}
		return true;
	}
}
