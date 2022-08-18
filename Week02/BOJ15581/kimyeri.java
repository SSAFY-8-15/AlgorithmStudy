import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,k;
	static char[][] sticks;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		sticks = new char[2][N];
		sticks[0] = br.readLine().toCharArray();
		sticks[1] = br.readLine().toCharArray();
		visited = new boolean[2][N];
		if(bfs()) System.out.println(1);
		else System.out.println(0);
	}
	private static boolean bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0,0});
		visited[0][0]=true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int dis = cur[2];
			if(cur[1]==N-1 || cur[1]+k>N-1) return true; // 끝 도착하거나, 끝을 넘어설 수 있으면 true 반환
			int f = cur[1]+1; // 앞으로 1칸
			int b = cur[1]-1; // 뒤로 1칸
			int j = cur[1]+k; // 옆줄 k칸
			if(dis<f && f<N) {
				if(sticks[cur[0]][f]=='1'&&!visited[cur[0]][f]) { // 갈 수 있고, 방문하지 않았다면
					q.add(new int[] {cur[0],f,dis+1});
					visited[cur[0]][f] = true;
				}
			}
			if(dis<b && b<N) {
				if(sticks[cur[0]][b]=='1'&&!visited[cur[0]][b]) {
					q.add(new int[] {cur[0],b,dis+1});
					visited[cur[0]][b] = true;
				}
			}
			if(dis<j && j<N) {
				if(sticks[(cur[0]+1)%2][j]=='1'&&!visited[(cur[0]+1)%2][j]) {
					q.add(new int[] {(cur[0]+1)%2,j,dis+1});
					visited[(cur[0]+1)%2][j] = true;
				}
			}
		}
		return false; // 다 돌아도 도착 못했으면
	}
}