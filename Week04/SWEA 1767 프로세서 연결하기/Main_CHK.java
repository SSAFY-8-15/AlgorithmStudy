package algo0912;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_CHK {
	//부분집합
	static int N, ans, maxCore;
	static int[][] map;
	static ArrayList<corePoint> coreList; 
	static boolean[][] isStream;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			coreList = new ArrayList<>();
			for(int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1 && i != 0 && i != N-1 && j != 0 && j != N-1) {
						coreList.add(new corePoint(i, j));
					}
				}
			}
			maxCore = 0;
			ans = Integer.MAX_VALUE;
			isStream = new boolean[N][N];
			selectCore(0,0,0);
			
			System.out.println("#"+t+" "+ans);
		}
	}

	private static void selectCore(int idx, int coreCnt, int sum) {
		if(idx==coreList.size()) {
			if(maxCore<coreCnt) {
				maxCore = coreCnt;
				ans = sum;
			}
			else if(maxCore == coreCnt) {
				ans = Math.min(ans, sum);
			}
			return;
		}

		int x = coreList.get(idx).x;
		int y = coreList.get(idx).y;
		
		for(int d = 0; d<4; d++) {//4방향
			int nowX = x;
			int nowY = y;
			int cnt = 0;
			
			while(true) {
				int nx = nowX + dx[d];
				int ny = nowY + dy[d];
			
				//끝에 닿으면 ->접지 성공 -> stream배열 채우고 길이 더해주기
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
					break;
				}
				//이미 전류가 흐르던 자리거나 코어가 있는 자리면 ->실패->이 코어는 연결하지 않음
				if(isStream[nx][ny] == true || map[nx][ny]==1) {
					cnt = 0;
					break;
				}
				
				cnt++;
				nowX = nx;
				nowY = ny;
				
			}
			
			//stream배열 채우기
			int stx = x;
			int sty = y;
			for(int i = 0; i<cnt; i++) {
				stx = stx+dx[d];
				sty = sty+dy[d];
				isStream[stx][sty] = true;
			}
			
			if(cnt != 0) {
				selectCore(idx+1, coreCnt+1, sum+cnt);
				
				//복구
				stx = x;
				sty = y;
				for(int i = 0; i<cnt; i++) {
					stx = stx+dx[d];
					sty = sty+dy[d];
					isStream[stx][sty] = false;
				}
			}
			else {//실패
				selectCore(idx+1, coreCnt, sum);
			}
				
		}	
		
	}

}

class corePoint{
	int x;
	int y;
	public corePoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
}