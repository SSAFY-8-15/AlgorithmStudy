package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_KSG2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] data = new int[n+1][n+1];
		for(int i=1; i<=n; i++) {
			char[] chars = br.readLine().toCharArray();
			for(int j=1; j<=n; j++) {
				data[i][j] += (chars[j-1] - '0') + data[i-1][j] + data[i][j-1] - data[i-1][j-1];
			}
		}
		StringBuilder sb = new StringBuilder(n*(n+1));

		compress(data,1,1,n+1,n+1,sb);
		System.out.println(sb);
		
	}

	public static void compress(int[][] data, int i, int j, int i2, int j2, StringBuilder sb) {
		// base
		int zeroCnt = getZeroCnt(data,i,j,i2,j2);
		if(zeroCnt == 0) {
			sb.append('0');
			return;
		}else if(zeroCnt == (j2-j)*(i2-i)) {
			sb.append('1');
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

	public static int getZeroCnt(int[][] data, int i, int j, int i2, int j2) {
		return data[i2-1][j2-1] - (data[i-1][j2-1] + data[i2-1][j-1] - data[i-1][j-1]);
	}

}
