package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20055 {

	static int[] belt; //내구도저장
	static boolean[] robots;
	static int n;
	static int k;
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		int step = 0;
		belt = new int[n*2];
		robots = new boolean[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n*2; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		while (true) {
			step++;
			cycle();
			robotMove();
			if (fin()) {
				System.out.println(step);
				break;
			}
		}
	}
	

	private static void cycle() {
		int e = belt[belt.length-1];
		for (int i = belt.length-2; i >= 0; i--) {
			belt[i+1] = belt[i]; // 내구도 이동
		}
		
		belt[0] = e; // 마지막꺼가 맨앞으로
		
		for (int i = n-2; i >= 0; i--) {
			robots[i+1] = robots[i]; //로봇 이동
		}
		
		robots[0] = false; // 한칸씩옮기면 맨앞은 비어있음
		
	}
	
	private static void robotMove() {
		if (robots[n-1]) {
			robots[n-1] = false; // 마지막 로봇 떨어트리고
		}
		
		for (int i = n-2; i > 0; i--) {
			if (robots[i] && !robots[i+1] && belt[i+1] > 0) {
				robots[i+1] = true;
				robots[i] = false;
				belt[i+1]--;
			}
		}
		
		if (belt[0] > 0 && !robots[0]) {
			robots[0] = true;
			belt[0]--;
		}
	}



	private static boolean fin() { // 매 step마다 끝났는지 판별
		int cnt = 0;
		for (int i = 0; i < belt.length; i++) {
			if (belt[i] == 0) {
				cnt++; // 내구도 0 개수 카운트
			}
		}
			
		if (cnt >= k) {
			return true;
		}else return false;
			
	}

}
