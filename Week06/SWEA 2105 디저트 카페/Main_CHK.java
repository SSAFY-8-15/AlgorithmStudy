package SWEA2105;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_CHK {
	static int N,max,sx,sy;
	static int[][] map;
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			//구현
			max = -1;
			for(int i = 0; i<N; i++) {
				for (int j = 0; j < N; j++) {
					sx = i;
					sy = j;
					boolean[] nums = new boolean[101];
					dfs(i,j,0,0,nums);
				}
			}
			

			System.out.println("#"+t+" "+max);
			
		}
	}

	private static void dfs(int x, int y, int d, int len, boolean[] nums) {
//		System.out.println("sx:"+sx+" sy:"+sy+" x:"+x+" y:"+y+" d:"+d+" len:"+len);
		if(x==sx&&y==sy&&d==3) {
			max=Math.max(max, len);
			return;
		}
		if(d==4) {
			return;
		}
		int nx = x + dx[d];
		int ny = y + dy[d];
		if(nx>=0&&ny>=0&&nx<N&&ny<N&&!nums[map[nx][ny]]) {
			//그냥 nums를 넘기면 문제가 발생.. -> 서로 다른 dfs에서 nums를 공유하는듯 싶다
			//->newNums를 넘긴다
			boolean[] newNums = new boolean[101];
			for(int i = 0; i<101; i++) {
				newNums[i] = nums[i];
			}
			newNums[map[nx][ny]] = true;
			dfs(nx,ny,d,len+1,newNums);
			dfs(nx,ny,d+1,len+1,newNums);
		}
	}
}
