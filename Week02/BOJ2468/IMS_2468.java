import java.util.*;
import java.io.*;


public class IMS_2468 {
	static int n;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n][n];
		int maxh =0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				arr[i][j]=sc.nextInt();
				if(arr[i][j]>maxh) {
					maxh= arr[i][j];
				}
			}
		}
		int max=0;
		for(int h=0; h<maxh+1; h++) {
			visited = new boolean[n][n];
			int count=0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(!visited[i][j] && arr[i][j]>h) {
						count+=dfs(i,j,h);
					}
				}
			}
			if(count>max) {
				max=count;
			}
			
		}
		System.out.println(max);
		

	}
	private static int dfs(int r, int c, int h) {
		
		visited[r][c]=true;
		for(int d=0; d<4; d++) {
			int nr= r+dr[d];
			int nc =c+dc[d];
			if(check(nr,nc)&&!visited[nr][nc]) {
				if(arr[nr][nc]>h) {
					dfs(nr,nc,h);
				}
			}
		}
		return 1;
		
	}
	private static boolean check(int a, int b) {
		return a>=0 && a<n&& b>=0 && b<n;
	}

}
