package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1992_쿼드트리 {
	
	static char[][] map;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		sb = new StringBuilder();
		
		for (int i = 0; i < n; i++) map[i] = br.readLine().toCharArray();
		
		QuardTree(0,0,n); // 0,0 ~ 0+n, 0+n 까지 반복
		System.out.println(sb);
	}
	private static void QuardTree(int r, int c, int cnt) {
		boolean same = true;	
		for (int i = r; i < r+cnt; i++) {
			for (int j = c; j < c+cnt ; j++) {
				if(map[r][c] != map[i][j]) {
					same = false;
					sb.append('(');
					QuardTree(r, c, cnt/2);
					QuardTree(r, c+cnt/2, cnt/2);
					QuardTree(r+cnt/2, c, cnt/2);
					QuardTree(r+cnt/2, c+cnt/2, cnt/2);
					sb.append(')');
					return;
				}
			}
		}
		if (same == true) {
			sb.append(map[r][c]);
		}
	}
}
