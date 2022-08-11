package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1992_scw {

	static int N, R; 
	static int[][] board;
	//static Map<String, List<String>> tree = new HashMap<>();
	static ArrayList<Integer> arr = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		R = (int) ((Math.log(N)/Math.log(2))-1);
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			char [] st = s.toCharArray();
			for (int j = 0; j < N; j++) {
				board[i][j] = st[j]-'0';
			}
		}
		
		int f = board[0][0];
		boolean tf = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] != f) {
					tf = false;
					break;
				}
			}
		}
		
		if (tf) {
			System.out.println(f);
		}else {
			dive(board,N,0);
			System.out.println(sb);
		}
	}

	private static void dive(int[][] b, int size, int cnt) {
		if (cnt == R) {
			if(check(b)) {
				sb.append(b[0][0]);
				return;
			}else {
				sb.append("(");
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						sb.append(b[i][j]);
					}
				}
				sb.append(")");
				return;
			}
		}
		

		if(check(b)) {
			sb.append(b[0][0]);
		}
		else {
			sb.append("(");
			
			int sz = size/2;
			int[][] b1 = new int[sz][sz];
			int[][] b2 = new int[sz][sz];
			int[][] b3 = new int[sz][sz];
			int[][] b4 = new int[sz][sz];
			
			for (int i = 0; i < sz; i++) {
				for (int j = 0; j < sz; j++) {
					b1[i][j] = b[0+i][0+j];
					b2[i][j] = b[0+i][sz+j];
					b3[i][j] = b[sz+i][0+j];
					b4[i][j] = b[sz+i][sz+j];
				}
			}
			
			dive(b1,sz,cnt+1);
			dive(b2,sz,cnt+1);
			dive(b3,sz,cnt+1);
			dive(b4,sz,cnt+1);
			sb.append(")");
		}
		 

	}

	private static boolean check(int[][] t) {
		int c = t[0][0];
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t.length; j++) {
				if (t[i][j] != c) {
					return false;
				}
			}
		}
		return true;
	}

}
