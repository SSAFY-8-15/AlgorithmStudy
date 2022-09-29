package SWEA;

import java.util.*;
import java.io.*;

public class Solution_1953_탈주범_검거 {
	
	static int[][] map;
	static int [][] visited;
	static int N, M, R, C, L; //R, C : 맨홀뚜껑의 위치, L : 탈출 후 소요된 시간
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	   
	static HashMap<Integer, int[]> hash = new HashMap<>(); // 7가지 방향 정의
	static HashMap<Integer, int[]> dirCheckHash = new HashMap<>(); // 방향이 n일 때 그 방향으로 갈 수 있는 지 체크;
	
	static int ans = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		hash.put(1, new int[] {0, 1, 2, 3}); // 상 하 좌 우
		hash.put(2, new int[] {0, 2}); // 상 하
		hash.put(3, new int[] {1, 3}); // 좌 우
		hash.put(4, new int[] {0, 1}); // 상 우
		hash.put(5, new int[] {2, 1}); // 하 우
		hash.put(6, new int[] {2, 3}); // 하 좌
		hash.put(7, new int[] {0, 3});	// 상 좌
		
		dirCheckHash.put(0, new int[] {1, 2, 5, 6}); // 상으로 갈 수 있는 터널의 종류
		dirCheckHash.put(1, new int[] {1, 3, 6, 7}); // 우로 갈 수 있는 터널의 종류 
		dirCheckHash.put(2, new int[] {1, 2, 4, 7}); // 하로 갈 수 있는 터널의 종류 
		dirCheckHash.put(3, new int[] {1, 3, 4, 5}); // 좌로 갈 수 있는 터널의 종류 
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			ans = 0;
			map = new int[N][M];
			visited = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs(R, C, 1);
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int x, int y, int t) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y, t});
		visited[R][C] = t;
		ans++;
		while(!q.isEmpty()) {
			int []cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int time = cur[2];
			int type = map[r][c];
			int[]dirs = hash.get(type);
			if (time == L) return;
			
			for (int d : dirs) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!check(nr, nc)) continue;
				if (visited[nr][nc] > 0) continue;
				if (map[nr][nc] == 0) continue;
				if (!dirCheck(d, map[nr][nc])) continue; //d 방향일 때 갈 수 있는 터널인지 체크
				
				ans++;
				visited[nr][nc] = time+1;
				q.offer(new int[] {nr, nc, time+1});
			}
		}
		
	}
	private static boolean dirCheck(int d, int nextType) {
		int[] dirList = dirCheckHash.get(d);
		for (int dir : dirList) {
			if (dir == nextType) return true;
		}
		return false;
	}

	private static boolean check(int nr, int nc) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < M) return true;
		return false;
	}
}
