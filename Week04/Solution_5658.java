package study;

import java.util.*;
import java.io.*;

public class Solution_5658 {

	static int N, K;
	static String arr[]; // 입력 문자열 담는거
	static TreeSet<String> set; // treeset으로 collections.reverseOrder 할 것, 중복제거 (hashset은 바로 안됨)
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int T = 1; T <= TC; T++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = br.readLine().split("");
			set = new TreeSet<>(Collections.reverseOrder());
			
			for (int i = 0; i < N/4; i++) { //4번째마다 원래거와 동일한 조합이 됨
				getpw(); // 비밀번호 생성
				rotate(); //하나 돌려
			}
			
			String ans[] = set.toArray(new String[set.size()]); // set->array
			int answer = Integer.parseInt(ans[K-1], 16); // 16진수->int
			System.out.println("#" + T + " " + answer);
			
		}
	}

	// 시계방향 한칸이동
	private static void rotate() {
		String temp = arr[N-1];
		for (int i = N-1; i > 0; i--) {
			arr[i] = arr[i-1];
		}
		arr[0] = temp;
	}

	// 비밀번호 조합, 생성
	private static void getpw() {
		for (int i = 0; i < arr.length; i += N/4) {
			StringBuilder sb = new StringBuilder();
			for (int j = i; j < i+(N/4); j++) {
				sb.append(arr[j]);
			}
			set.add(sb.toString());
		}
	}

}
