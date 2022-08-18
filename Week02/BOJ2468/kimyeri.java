import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] space = new int[N][N];
		TreeSet<Integer> set = new TreeSet<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				space[i][j] = Integer.parseInt(st.nextToken());
				set.add(space[i][j]); // 현존하는 높이만큼
			}
		}
		int[] dx = {-1,0,1,0};
		int[] dy = {0,-1,0,1};
		int maxArea = 1;
		for(int height : set) { 
			boolean[][] visit = new boolean[N][N];
			int area=0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(space[i][j]>height && !visit[i][j]) {
						area++;
						Queue<int[]> q = new LinkedList<>();
						q.add(new int[] {i,j});
						visit[i][j]=true;
						while(!q.isEmpty()) {
							int[] cur = q.poll();
							for(int d=0;d<4;d++) {
								int nx = cur[0]+dx[d];
								int ny = cur[1]+dy[d];
								if(-1<nx && nx<N && -1<ny && ny<N && !visit[nx][ny] && space[nx][ny]>height) {
									q.add(new int[] {nx,ny});
									visit[nx][ny] = true;
								}
							}
						}
					}
				}
			}
			maxArea = Math.max(maxArea, area);
		}
		System.out.println(maxArea);
	}

}
