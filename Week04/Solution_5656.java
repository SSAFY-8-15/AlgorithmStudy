package study;

import java.io.*;
import java.util.*;

public class Solution_5656 {

	static int TC, N, W, H;
	static int map[][];
	static int temp[][];
	static int nums[];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int ans;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		for (int T = 1; T <= TC; T++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			temp = new int[H][W];
			nums = new int[N];
			ans = Integer.MAX_VALUE;
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = temp[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			perm(0);
			System.out.println("#" + T + " " + ans);
			
		}
		
	}

	private static void perm(int cnt) { //떨어트릴 위치 중복순열 완탐
		if (cnt == N) {
			game(nums); // 게임돌리고
			ans = Math.min(ans, leftover()); //남은 갯수확인, 최소값 업데이트
			resetMap(); //원래 맵으로 되돌려놓기
			
			return;
		}
		
		for (int i = 0; i < W; i++) {
			nums[cnt] = i;
			perm(cnt+1);
		}
	}

	private static void game(int[] narr) {
		int r = 0;
		int c = 0;
		
		for (int i = 0; i < N; i++) { // 공 떨어트리는 횟수만큼
			for (int j = 0; j < H; j++) {
				if (map[j][narr[i]] != 0) { //위에서부터 내려오면서 폭탄을 만났을 때
					r = j;
					c = narr[i];
					break;
				}
			}
			bfs(r, c); //터트림
			movedown(); //남은 애들 내리기
		}
		
	}

	private static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] {r, c, map[r][c]});
		map[r][c] = 0; // 처음으로 맞은 공 0으로 처리
		
		while (!q.isEmpty()) {
			int cur[] = q.poll();
			int range = cur[2];
			
			for (int i = 1; i < range; i++) {
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d]*i;
					int nc = cur[1] + dc[d]*i;
					
					if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == 0) { //범위 밖, 빈칸 무시
						continue;
					}
					
					if (map[nr][nc] != 0) { //터트릴 또다른 폭탄있는거임
						q.add(new int[] {nr, nc, map[nr][nc]});
						
						map[nr][nc] = 0; //터졌으니까 0
					}
				}
			}
		}
	}

	private static void movedown() {
		for (int c = 0; c < W; c++) { //왼쪽부터 열 하나씩
			
			int r = H-1; // 맨 아래부터
			while(r > 0) {
				if (map[r][c] == 0) { //빈칸이면
					int nr = r-1;
					while (nr > 0 && map[nr][c] == 0) { // 그위도 빈칸?
						nr--;
					}
				
					map[r][c] = map[nr][c]; //내리기
					map[nr][c] = 0; //원래자리는 0
				}
				r--;
			}
		}
	}

	private static int leftover() { //남은 블럭계산
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] != 0) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}

	private static void resetMap() { //deepcopy
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = temp[i][j]; //원래대로 초기화
			}
		}
	}

}
