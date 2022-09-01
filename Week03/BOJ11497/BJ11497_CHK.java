package java0822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//BJ11497
public class BJ11497_CHK {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] height = new int[N];
			int[] ans = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) {
				height[i] = Integer.parseInt(st.nextToken()); 
			}
			
			Arrays.sort(height);
			
			int start = 0;
			int end = N-1;
			for(int i = 0; i<N; i++) {
				if(i%2==0) {
					ans[start] = height[i];
					start ++;
				}
				else {
					ans[end] = height[i];
					end --;
				}
			}
			
			int max = -1;
			for(int i = 0; i<N-1; i++) {
				max = Math.max(max, Math.abs(ans[i]-ans[i+1]));
			}
			System.out.println(max);
			
			
		}
		
		

	}

}
