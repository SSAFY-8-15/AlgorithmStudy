package Week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11497_통나무_건너뛰기 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			for (int j = 0; j < N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			
			int[] arr2 = new int[N];
			int i = 0;
			int left = 0;
			int right = N-1;
			while(left < right) {
				arr2[left] = arr[i++];
				arr2[right] = arr[i++];
				left ++;
				right --;
				
				if (left == right) {
					arr2[left] = arr[i];
					break;
				}
			}
			
			int maxDiff = 0;
			for (int j = 0; j < N-1; j++) {
				maxDiff = Math.max(Math.abs(arr2[j]-arr2[j+1]), maxDiff);
			}
			maxDiff = Math.max(Math.abs(arr2[0]-arr2[N-1]), maxDiff);
			System.out.println(maxDiff);
		}
	}
}
