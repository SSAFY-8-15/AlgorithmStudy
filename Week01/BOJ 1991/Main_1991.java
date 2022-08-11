package algo0806;

import java.util.Scanner;

public class Main_1991 {
	static int N;
	static int[][] map;
	static String answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			char[] temp = sc.next().toCharArray();
			for(int j=0;j<N;j++) {
				map[i][j] = temp[j]-'0';
			}
		}
		answer="";
		quadTree(N,0,0);
		System.out.println(answer);
	}
	private static void quadTree(int n, int r, int c) {
		if(n==1) {
			answer+=map[r][c];
			return;
		}
		int sum=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				sum+=map[r+i][c+j];
			}
		}
		if(sum==0 || sum==n*n) {
			answer+= sum==0? "0":"1";
		} else {
			answer+="(";
			quadTree(n/2, r,c);
			quadTree(n/2, r,c+n/2);
			quadTree(n/2, r+n/2,c);
			quadTree(n/2, r+n/2,c+n/2);
			answer+=")";
		}
	}
	
}
