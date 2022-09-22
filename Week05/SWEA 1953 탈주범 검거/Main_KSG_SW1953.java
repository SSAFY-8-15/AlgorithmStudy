package week5.sw1953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_KSG_SW1953 {
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	// 0 = 상, 1 = 우, 2= 하, 3=좌
	static int[][] dir = {{},{0,1,2,3},{0,2},{1,3},{0,1},{2,1},{2,3},{0,3}};
	
	static int[] hole = {0b0000, 0b1111, 0b0101, 0b1010, 0b0011, 0b0110, 0b1100, 0b1001};
	
	static boolean test(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
	
	static int n;
	static int m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int[][] table = new int[n][m];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					table[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<int[]> queue = new LinkedList<>();
			int type = table[r][c];
			queue.add(new int[] {r,c,l-1,type});
			table[r][c] = 0;
			int answer = 1;
			
			while(!queue.isEmpty()) {
				int[] elem = queue.poll();
				int i = elem[0];
				int j = elem[1];
				l = elem[2];
				if(l == 0) {
					continue;
				}
				type = elem[3];
				
				for(int d : dir[type]) {
					int ii = i + dr[d];
					int jj = j + dc[d];
					if(!test(ii,jj)) continue;
					if(table[ii][jj] == 0) continue;
					
					int type2 = table[ii][jj];
					
					int oppositD = (d+2) % 4;
					
					if((hole[type2] & (1 << oppositD)) == 0) continue; 
					
					
					queue.add(new int[] {ii,jj,l-1, type2});
					
					table[ii][jj] = 0;
					answer++;
					
				}
			}
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
		
		br.close();
		
	}
	

}
