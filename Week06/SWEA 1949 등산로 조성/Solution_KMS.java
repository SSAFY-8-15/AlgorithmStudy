import java.util.*;
import java.io.*;

// 시간: 126ms, 메모리: 31,820 kb
public class Solution_1949_등산로_조성{

	static int[][] map;
	static int N, K, ans;
	static int[][] visited; 
	
	static class Top{
		int r,c,h;
		
		public Top(int r, int c, int h) {
			this.r = r;
			this.c = c;
			this.h = h;
		}
	}
	static List<Top> Tops;
	static int maxHeight;
	static int topsSize;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			Tops = new ArrayList<>();
			maxHeight = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > maxHeight) {
						Tops = new ArrayList<>();
						Tops.add(new Top(i,j,map[i][j]));
						maxHeight = map[i][j];
					}else if (map[i][j] == maxHeight) {
						Tops.add(new Top(i,j,map[i][j]));
					}
				}
			}
			
			topsSize = Tops.size(); 
			ans = 1;
			for (int i = 0; i < topsSize; i++) {
				int r = Tops.get(i).r;
				int c = Tops.get(i).c;
				visited = new int[N][N];
				visited[r][c] = 1;
				dfs(r, c, 1, 0);
			}
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int r, int c, int cnt, int cut) {
		
		ans = Math.max(ans, cnt);
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if (visited[nr][nc] > 0) continue; 
			
			if (map[nr][nc] < map[r][c]) {
				visited[nr][nc] = cnt+1;
				dfs(nr, nc, cnt+1, cut);
				visited[nr][nc] = 0;
			}
			
			//깍기를 사용 안한 경우
			if (cut == 0) {
				for (int k = 1; k <= K; k++) {
					if (map[nr][nc]-k < map[r][c] ) { // 깍았는데 그래도 높거나 같은 경우
						visited[nr][nc] = cnt+1;
						map[nr][nc] -= k; //깍음
						dfs(nr, nc, cnt+1, 1);
						map[nr][nc] += k; //원상 복구
						visited[nr][nc] = 0;
					}
				}
			}	
		}
	}
}
