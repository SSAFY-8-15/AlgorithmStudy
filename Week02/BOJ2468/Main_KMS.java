package Week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2468_안전영역{
	
	static int N;
	static int[][] map;
	static boolean[][] visited; 
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int max = 0;
		int min = 101;
		int maxCnt = 0;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken()); 
				map[i][j] = temp; 
				max = Math.max(max, temp);
				min = Math.min(min, temp);
				
			}
		}// 입력 끝
		if (max == min) {
			System.out.println(1);
			return;
		}
		
		for (int h = min; h < max+1; h++) {
			visited = new boolean[N][N];
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!check(i, j, h)) 
						continue;
					visited[i][j] = true;
					cnt += BFS(i, j, h);
				}
			}
			maxCnt = Math.max(maxCnt, cnt);
		}
		System.out.println(maxCnt);
	}

	private static int BFS(int x, int y, int h) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[]{x, y});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (!check(nr, nc, h)) 
					continue;
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
		return 1;
	}

	private static boolean check(int i, int j, int h) {
		if (i < 0 || i >= N || j < 0 || j >= N || map[i][j] <= h || visited[i][j]) 
			return false;
		return true;
	}
}
