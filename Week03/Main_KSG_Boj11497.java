package week3.boj11497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_KSG_Boj11497 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] arr = new int[n];
			for(int i=0; i<n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			int maxGap = Math.max(arr[1] - arr[0], arr[n-1] - arr[n-2]);
			for(int i=2; i<n; i++) {
				maxGap = Math.max(maxGap, arr[i] - arr[i-2]);
			}
            sb.append(maxGap).append('\n');
		}
        System.out.println(sb);
	}

}
