package week4.sw1767;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_KSG_SW1767 {
	
	static int[] dr = {-1,0,1,0};
	
	static int[] dc = {0,-1,0,1};
	
	static final int INF = Integer.MAX_VALUE/2-1;
	
	static int n;
	
	static int globalMinIdx;
	
	static class Core{
		int i;
		int j;
		public Core(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			StringTokenizer st;
			int[][] board = new int[n][n];
			
			ArrayList<Core> list = new ArrayList<>(24);
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					int data = Integer.parseInt(st.nextToken());
					board[i][j] = data;
					if(data == 1 && i > 0 && j > 0 && i < n-1 && j < n-1) {
						list.add(new Core(i,j));
					}
				}
			}
			int answer = 0;
			
			Core[] cores = list.toArray(new Core[list.size()]);
			
			int cc = cores.length;
			
			int[] min = new int[cc+1]; // 0개부터 12개까지. 최대 13개
			Arrays.fill(min, INF);
			
			boolean[] visited = new boolean[cores.length];
			globalMinIdx = -1;
			backtrack(board, cores, min, visited, 0, 0, 0, cc);
			
			for(int i=cc; i>=0; i--) {
				if(min[i] != INF) {
					answer = min[i];
					break;
				}
			}
			sb.append('#').append(t).append(' ').append(answer).append('\n');
			
		}
		System.out.print(sb);
		br.close();

	}

	private static void backtrack(int[][] board, Core[] cores, int[] min, boolean[] visited, int cnt, int value, int useCount, int cc) {
		if(globalMinIdx == cc && value > min[globalMinIdx]) {
			return;
		}
		
		if(cnt == cc) {
			min[useCount] = Math.min(min[useCount],value);
			globalMinIdx = Math.max(useCount,globalMinIdx);
			return;
		}
		visited[cnt] = true;
		int i = cores[cnt].i;
		int j = cores[cnt].j;
		int connected = 0;
		boolean trackable;
		
		for(int d=0; d<4; d++) {
			int ii = i+dr[d];
			int jj = j+dc[d];
			trackable = true;
			while(true) {
				if(!test(ii,jj)) {
					break;
				}
				if(board[ii][jj] != 0) {
					trackable = false;
					break;
				}
				board[ii][jj] = 2;
				ii = ii+dr[d];
				jj = jj+dc[d];
				connected++;
			}
			if(trackable) {
				backtrack(board,cores,min,visited,cnt+1,value+connected,useCount+1,cc);
			}
			// 지우기
			ii = ii-dr[d];
			jj = jj-dc[d];
			while(connected > 0) {
				board[ii][jj] = 0;
				ii = ii-dr[d];
				jj = jj-dc[d];
				connected--;
			}
		}
		visited[cnt] = false;
		backtrack(board,cores,min,visited,cnt+1,value,useCount,cc);
		
		
	}

	private static boolean test(int i, int j) {
		return i >= 0 && j >= 0 && i < n && j < n;
	}

}