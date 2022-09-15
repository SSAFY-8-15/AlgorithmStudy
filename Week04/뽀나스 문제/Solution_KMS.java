package BOJ;

import java.util.Scanner;

public class Main_2004_조합_0의_개수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextInt();
		long m = sc.nextInt();
		
		long temp = n;
		long five = FindTrailingZeros(n, 5); 
		five = five - FindTrailingZeros(n-m, 5) - FindTrailingZeros(m, 5); //소인수분해 한 결과 5는 5끼리 빼기
		
		long two = FindTrailingZeros(n, 2);
		two = two - FindTrailingZeros(n-m, 2) - FindTrailingZeros(m, 2);
		
		System.out.println(Math.min(five, two));
		
	}
	
	
	private static long FindTrailingZeros(long n, long divider) {
		if(n < 0) return -1;
		long count = 0;
		for (long i = divider; n/i >= 1 ; i *= divider) {
			count += n/i;
		}
		return count;		
	}
}
