package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656_벽돌_깨기 {

	static int N, W, H;
	static int[][] map;
	static int[] nums;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			nums = new int[N];
			ans = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}// 입력 끝
			
			//중복 순열
			nPIr(0);
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}

	private static void nPIr(int cnt) {
		if (cnt == N) {
			//맵 복사
			int[][] tempMap = new int[H][W];
			for (int i = 0; i < H; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, W);
			}
			
			int zeroCnt = breakStone(nums, tempMap);
			ans = Math.min(zeroCnt, ans);
			return;
		}
		
		for (int i = 0; i < W; i++) {
			nums[cnt] = i;
			nPIr(cnt+1);
		}
	}

	private static int breakStone(int[] nums, int[][] tempMap) {
		//w열 벽돌 부시기
		for (int i = 0; i < nums.length; i++) {
			int c = nums[i]; // 행
			int r = 0;
			
			//1. 0이 아닌 행 찾기
			while(r < H) {
				if (tempMap[r][c] != 0) break;
				r++;
			}
			if (r == H) continue; // 다음 열
			
			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[] {r, c, tempMap[r][c]});
			while(!q.isEmpty()) {
				int cur[] = q.poll();
				int h = cur[0];
				int w = cur[1];
				int cnt = cur[2]-1; //부시는 칸 개수
				
				//2. 3 방향 터뜨리기
				for(int d = 0; d < 4; d++) {
					int nr = h, nc = w;
					for (int j = 0; j < cnt; j++) {// 벽돌 사이즈 만큼 
						nr += dr[d];
						nc += dc[d];
						
						if (nr < 0 || nr == H || nc < 0 || nc == W) {
							break;
						}
						
						//2-1 한 방향으로 터뜨리는 도중 2이상의 벽돌 크기가 나오면 그것도 큐에 넣기
						if (tempMap[nr][nc] > 1) {
							q.offer(new int[] {nr, nc, tempMap[nr][nc]});
						}
						tempMap[nr][nc] = 0; // 부셨다.
					}
				}
				tempMap[h][w] = 0; //자기 칸 부셨다.
			}
			
			//밑으로 내리기
			updateMap(tempMap);
		}
		
		int count = 0;
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(tempMap[i][j] != 0) count++;
			}
		}
		return count;
	}

	private static void updateMap(int[][] tempMap) {
		for (int i = 0; i < W; i++) {
			ArrayList<Integer> arr = new ArrayList<>();
			for (int j = H-1; j > -1; j--) {
				if (tempMap[j][i] > 0) {
					arr.add(tempMap[j][i]);
				}
			}
			
			int h = H-1;
			for (int j = 0; j < arr.size(); j++) {
				tempMap[h][i] = arr.get(j); // 아래에서 부터 채우기
				h--;
			}
			
			for (int j = 0; j < H - arr.size(); j++) {
				tempMap[j][i] = 0; // 나머지 0으로 채우기
			}		
		}
	}
}
