package week4.boj2004;

import java.util.Scanner;

public class Main_KSG_Boj2004 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int r = n-m;
		
	    long a = 0;
		long b = 0;
		
		for(long i=2; i<=n && i>0; i*=2) {
			a += (n/i - m/i - r/i);
		}
		for(long i=5; i<=n && i>0; i*=5) {
			b += (n/i - m/i - r/i);
		}
		
		System.out.println(Math.min(a,b));
		sc.close();

	}

}