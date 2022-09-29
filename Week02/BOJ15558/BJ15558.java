package java0817;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ15558 {
	static int N,k;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[N][2];
		
		visited = new boolean[N][2];
		
		
		String[] temp = br.readLine().split("");
		for(int i = 0; i<N; i++) {
			map[i][0] = Integer.parseInt(temp[i]); 
		}
		temp = br.readLine().split("");
		for(int i = 0; i<N; i++) {
			map[i][1] = Integer.parseInt(temp[i]); 
		}
		
		bfs(0);
		
		

	}
	private static void bfs(int start) {
		Queue<Me> queue = new LinkedList<>();
		queue.add(new Me(0, 0, 0));
		visited[start][0] = true;
		boolean isComplete = false;
		
		while(!queue.isEmpty()) {
			Me now = queue.poll();
			if(now.cnt>=N) {
				break;
			}
			
			int a = now.step-1;
			int b = now.step+1;
			int c = now.step+k;
			if(b >=N || c>=N) {
				isComplete = true;
				break;
			}
			
			if(a>=now.cnt && !visited[a][now.leftandright] && map[a][now.leftandright]==1) {
				queue.add(new Me(a, now.leftandright, now.cnt+1));
				visited[a][now.leftandright] = true;
			}
			if(b<N && !visited[b][now.leftandright] && map[b][now.leftandright]==1) {
				queue.add(new Me(b, now.leftandright, now.cnt+1));
				visited[b][now.leftandright] = true;
			}
			if(c<N && !visited[c][(now.leftandright+1)%2] && map[c][(now.leftandright+1)%2]==1) {
				queue.add(new Me(c, (now.leftandright+1)%2, now.cnt+1));
				visited[c][(now.leftandright+1)%2] = true;
			}
			
			visited[now.cnt][0] = true;
			visited[now.cnt][1] = true;
			
		}
		
		if(isComplete) System.out.println(1);
		else System.out.println(0);
	}

}
class Me{
	int step;
	int leftandright;
	int cnt;
	public Me(int step, int leftandright, int cnt) {
		super();
		this.step = step;
		this.leftandright = leftandright;
		this.cnt = cnt;
	}

}