package algo0912;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_CHK {
	//중복순열?
	static int N,W,H,ans;
	static int[][] map,copyMap;
	static int[] nums;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());//행
			map = new int[H][W];
			copyMap = new int[H][W];
			for(int i = 0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					copyMap[i][j] = map[i][j];
					
				}
			}
			
			ans=Integer.MAX_VALUE;//남은 벽돌의 수 -> 최소가 되는 값을 출력
			nums = new int[N];
			npr(0);//중복순열			
			
			System.out.println("#"+t+" "+ans);

		}
	}

	private static void npr(int cnt) {
		if(cnt == N) {//중복순열을 다 구했으면
			//맵 복구시키기
			for(int i = 0; i<H; i++) {
				for (int j = 0; j < W; j++) {
					map[i][j] = copyMap[i][j];
				}
			}
			
			//위에서부터 떨어뜨리는 로직
			for(int i = 0; i<N; i++) {
				drop(nums[i]);//nums[i]열에서 벽돌을 떨어뜨린다
				organize();//빈 공간이 생기면 떠있는 돌들을 아래로 정돈
				countRocks(); //맵의 남은 돌 카운트 -> 최소값이면 갱신
			}
			return;
		}
		
		for(int i = 0; i<W; i++) {
			nums[cnt] = i;
			npr(cnt+1);
		}
	}

	private static void countRocks() {
		int sum = 0;
		for(int i = 0; i<H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] != 0) {
					sum++;
				}
			}
		}
		ans = Math.min(ans, sum);
		
	}

	private static void organize() {
		for(int i = 0; i<W; i++) {
			int point = H-2;
			while(point>=0) {
				if(map[point][i]!=0) {
					if(map[point+1][i]!=0) {
						point--;
						continue;//바로 밑이 돌이면 pass
					}
					for(int p=point+1; p<H; p++) {//point위치 바로 밑부터 빈공간인지 아닌지 체크
						if(point==H-2) {//p가 가장 아랫줄이라면 ->따로 처리
							map[p][i]=map[point][i];
							map[point][i]=0;
							continue;
						}
						
						if(map[p][i]!=0) {
							map[p-1][i]=map[point][i];
							map[point][i]=0;
							break;
						}
						
						//point바로 밑부터 제일 아래까지 쭉 빈 공간일 경우->p==H-1이 됨
						if(p==H-1) {
							map[p][i]=map[point][i];
							map[point][i]=0;
						}
						
					}
				}
				point--;
				
			}
			
			
		}
		
	}

	private static void drop(int col) {
		//맨 위의 돌을 만날 때까지
		int row = 0;
		int rockSpot = -1;//맨위의 돌 row위치
		while(row<H) {
			if(map[row][col]!=0) {
				rockSpot = row;
				break;
			}
			row++;
		}
		if(rockSpot!=-1) {
			//돌위치 찾았으면 터뜨리기
			int power = map[rockSpot][col];
			bomb(rockSpot,col,power);	
		}
		
		
	}

	private static void bomb(int row, int col, int power) {
		map[row][col] = 0;//해당 위치 터뜨리기
		for(int d = 0; d<4; d++) {//4방
			int nowx = row;
			int nowy = col;
			for(int i = 0; i<power-1; i++) {
				nowx = nowx + dx[d];
				nowy = nowy + dy[d];

				if(!check(nowx,nowy)) break;
				if(map[nowx][nowy]==0) continue;
				bomb(nowx,nowy,map[nowx][nowy]);
			}
		}
		
	}

	private static boolean check(int nx, int ny) {
		return nx>=0 && ny>=0 && nx<H && ny<W;
	}
	
	private static void pr() {

		for(int i = 0; i<H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();

		
	}
	
	
	
}
	