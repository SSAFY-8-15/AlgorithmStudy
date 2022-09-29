package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1767_프로세서_연결하기 {

	static class Core{
		int r;
		int c;
		int d;
		boolean[] dirCheck = {true, true, true, true};
		
		public Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[][] map;
	static int N;
	static List<Core> coreList;
	static int coreCnt, realCoreCnt;
	static boolean[] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int maxCore = -1;
	static int ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			coreList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i == 0 || i == N-1 || j == 0 || j == N-1 || map[i][j] == 0)// 가장자리나 프로세서가 없을 때
						continue;
					coreList.add(new Core(i, j));
				}
			}// 입력 끝
			
			ans = Integer.MAX_VALUE;
			maxCore = -1;
			coreCnt = coreList.size();
			visited = new boolean[coreCnt];
			
			subset(0);
			
			if (ans == Integer.MAX_VALUE) {
				ans = 0;
			}
			
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}

	private static void subset(int cnt) {
		if (cnt == coreCnt) {
			List<Core> availCores = new ArrayList<>();
			for (int i = 0; i < coreCnt; i++) {
				if (visited[i]) {
					availCores.add(coreList.get(i));
				}
			}
			
			npr(availCores, 0, 0, 0); // 못 찾을 때만 들어감
			return;
		}
		
		visited[cnt] = true;
		subset(cnt+1);
		
		visited[cnt] = false;
		subset(cnt+1);
	}

	private static void npr(List<Core> availCores, int cnt, int coreCnt, int sum) {
		int size = availCores.size();
		
		if(maxCore > size-cnt+coreCnt) {
			return; // 남은 코어를 모두 택해도 현재 최대값보다 적으면 리턴, 프루닝
		}
		
		if (cnt == size) {
			if (maxCore < coreCnt) { //놓을 수 있는 코어의 수가 클 때
				maxCore = coreCnt;
				ans = sum;
	
			}else if(maxCore == coreCnt) {// 놓을 수 있느 코어가 같을 때
				ans = Math.min(ans, sum); // 최소 전선 길이 합
			}
			return;
		}
		
		Core co = availCores.get(cnt);
		for (int d = 0; d < 4; d++) {
			
			int r = co.r;
			int c = co.c;
			int lineCnt = 0;
			int tempCoreCnt = 0;
			
			while(true) {
				r = r + dr[d];
				c = c + dc[d];
				
				if (r < 0 || r == N || c < 0 || c == N) {
					tempCoreCnt++;
					break; //위부 전원 연결 가능
				}
				
				if (map[r][c] != 0) {
					rollback(r, c, d, lineCnt);//맵 되돌리기
					lineCnt = 0;
					break; //다른 프로세서가 있거나 이미 선이 깔려 있을 때
				}
				
				lineCnt += 1;
				map[r][c] = 2;// 전원 깔았다는 표시
			}
			npr(coreList, cnt+1, coreCnt+tempCoreCnt, sum+lineCnt);
			rollback(r, c, d, lineCnt);//맵 되돌리기		
		}
	}

	private static void rollback(int r, int c, int d, int lineCnt) {
		for (int i = 0; i < lineCnt; i++) {
			r = r - dr[d];
			c = c - dc[d];
			map[r][c] = 0; 
		}	
	}
}
