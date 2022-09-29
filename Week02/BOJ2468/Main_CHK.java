package java0815;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2468 {
	static int[][] map;
	static boolean[][] visited;
	static int N,rain;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		
		int rainMax = -1;
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				rainMax = Math.max(rainMax, temp);
				
			}
		}
		
		int cnt, ans=0;
		for(int m = 0; m<=rainMax; m++) {
			visited = new boolean[N][N];
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j] || map[i][j]<=m) continue;
					cnt++;
					bfs(i,j,m);
				}
			}
			ans = Math.max(ans, cnt);
		}
		System.out.println(ans);
		
	}
	private static void bfs(int x, int y, int m) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(x, y));
		visited[x][y] = true;
		
		while(!queue.isEmpty()) {
			Point nowPoint = queue.poll();
			for(int d = 0; d<4; d++) {
				int nx = nowPoint.x + dx[d];
				int ny = nowPoint.y + dy[d];
				
				if(nx>=0 && ny>=0 && nx<N && ny<N) {
					if(!visited[nx][ny] && map[nx][ny]>m) {
						queue.add(new Point(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
		}
		
	}
}

class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
