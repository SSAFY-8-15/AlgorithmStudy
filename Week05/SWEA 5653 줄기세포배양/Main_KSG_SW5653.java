package week5.sw5653;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_KSG_SW5653 {
	
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	
	static class Cell{
		int i;
		int j;
		int life;
		int level;
		public Cell(int i, int j, int life, int level) {
			this.i = i;
			this.j = j;
			this.life = life;
			this.level = level;
		}
	}
	
	public static final int startR = 151;
	public static final int startC = 151;
	
	public static int endR;
	
	public static int endC;
	
	static final int[][] status = new int[352][352];
	
	static int dL;
	
	static int deadCell;
	
	static Queue<Cell> inactivatedCells = new LinkedList<>();
	static Queue<Cell> readyCells = new LinkedList<>();
	static Queue<Cell> activatedCells = new LinkedList<>();
	
	static Queue<Cell> tmpQueue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			// status 의 의미 = t미만의 값은 빈곳, t인 값은 찬 곳
			deadCell = t*11;
			dL = deadCell-11;
			
			inactivatedCells.clear();
			readyCells.clear();
			activatedCells.clear();
			
			// t 에 따른 status,
			
			// 0 이하 빈곳, 1~10 해당 숫자가 이번턴에 새로 번식, 11 그 이외
			// 11이하 빈곳, 12~21 해당 숫자가 이번턴에   새로 번식, 22 그 이외
			//....
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			endR = startR + n;
			endC = startC + m;
			
			for(int i=startR; i<endR; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=startC; j<endC; j++) {
					int cellValue = Integer.parseInt(st.nextToken());
					if(cellValue > 0) {
						inactivatedCells.add(new Cell(i,j,cellValue+1,cellValue));
						status[i][j] = deadCell;
					}
				}
			}
			
			while(k-- >= 0) {
				
				while(!activatedCells.isEmpty()) {
					Cell c = activatedCells.poll();
					c.life--;
					if(c.life > 0) {
						tmpQueue.add(c);
					}
				}	
				while(!tmpQueue.isEmpty()) {
					Cell c = tmpQueue.poll();
					activatedCells.offer(c);
				}
				
				while(!inactivatedCells.isEmpty()) {
					Cell c = inactivatedCells.poll();
					if(c.life == 0) {
						c.life = c.level-1;
						readyCells.offer(c);
					}else {
						c.life--;
						tmpQueue.offer(c);
					}
				}
				while(!tmpQueue.isEmpty()) {
					inactivatedCells.offer(tmpQueue.poll());
				}
				
				while(!readyCells.isEmpty()) {
					Cell c = readyCells.poll();
					for(int d=0; d<4; d++) {
						int ii = c.i + dr[d];
						int jj = c.j + dc[d];
						if(status[ii][jj] <= dL) {
							// 빈곳
							tmpQueue.offer(new Cell(ii,jj,c.level,c.level));
							status[ii][jj] = dL + c.level;
						}else {
							int currentLevel = status[ii][jj] - dL;
							if(currentLevel < c.level) {
								// 역시 진입가능
								status[ii][jj] = dL + c.level;
								tmpQueue.offer(new Cell(ii,jj,c.level,c.level));
							}
						}
					}
					if(c.level > 1) {
						activatedCells.add(c);
					}
				}
				
				while(!tmpQueue.isEmpty()) {
					Cell c = tmpQueue.poll();
					if(c.level == status[c.i][c.j] - dL) {
						inactivatedCells.add(c);
						status[c.i][c.j] = deadCell;
					}
				}
			}
			int answer = inactivatedCells.size() + activatedCells.size() + readyCells.size();
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
		
		br.close();
	}

}
