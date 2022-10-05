package SWEA1949;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_CHK {
	static int N, K, save, maxHeight, ans;
	static ArrayList<Point> maxHeightPointArr;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Point{
		int x;
		int y;
		int len;
		public Point(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			maxHeight = -1;
			maxHeightPointArr = new ArrayList<>();
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					//가장 높은 산봉우리
					if(maxHeight==map[i][j]) {
						maxHeightPointArr.add(new Point(i,j));
					}
					else if(maxHeight<map[i][j]) {
						maxHeightPointArr.clear();
						maxHeightPointArr.add(new Point(i,j));
						maxHeight = map[i][j];
					}
				}
			}
			
			ans = -1;
			//K깎을곳 하나 고르기 ->다시원상복구시켜ㅕㅕㅕ
			for(int i = 0; i<N; i++) {
				for (int j = 0; j < N; j++) {
					save = map[i][j];
					
					//0~K까지 깎을 수 있다..
					for(int q = 0; q<=K; q++) {
						if(map[i][j]<=q) map[i][j] = 0;
						else map[i][j] -= q;
						
						for(Point start : maxHeightPointArr) {//산봉우리 하나씩 시작
							bfs(start.x, start.y);
						}
						map[i][j] = save; //복구
					}

				}
			}
			System.out.println("#"+t+" "+ans);
			
		}
	}
	private static void bfs(int startX, int startY) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(startX, startY, 1));
		
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			
			ans = Math.max(ans, now.len);
			
			for(int d = 0; d<4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				if(nx>=0 && ny>=0 && nx<N && ny<N && map[nx][ny]<map[now.x][now.y]) {
					queue.add(new Point(nx,ny,now.len+1));
				}
			}
		}		
	}
}
