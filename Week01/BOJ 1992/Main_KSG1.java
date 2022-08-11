package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_KSG1 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		char[][] data = new char[n][n];
		for(int i=0; i<n; i++) {
			data[i] = br.readLine().toCharArray();
		}
		StringBuilder sb = new StringBuilder(n*(n+1));
		
		
		
		compress(data,0,0,n,n,sb);
		System.out.println(sb);
		
	}

	public static void compress(char[][] data, int i, int j, int i2, int j2, StringBuilder sb) {
		// base
		if(isSameArea(data,i,j,i2,j2)) {
			sb.append(data[i][j]);
			return;
		}
		
		//induction
		int height = (i2-i)/2;
		int width = (j2-j)/2;
		
		sb.append('(');
		compress(data,i,j,i+height,j+width,sb);
		compress(data,i,j+width,i+height,j2,sb);
		compress(data,i+height,j,i2,j+width,sb);
		compress(data,i+height,j+width,i2,j2,sb);
		sb.append(')');
	}

	public static boolean isSameArea(char[][] data, int i, int j, int i2, int j2) {
		char w = data[i][j];
		for(int r=i; r<i2; r++) {
			for(int c=j; c<j2; c++) {
				if(data[r][c] != w) {
					return false;
				}
				w = data[r][c];
			}
		}
		return true;
	}

}
