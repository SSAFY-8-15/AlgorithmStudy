package Week03;

import java.io.*;
import java.util.*;

public class Main_14891_톱니바퀴 {

	static class Gear{
		char[] NS;
		
		public Gear(char[] nS) {
			NS = nS;
		}

		void rotate(int cw) {
			if (cw == 1) { // 시계
				char temp = NS[7];
				for (int i = 6; i >= 0; i--) {
					NS[i+1] = NS[i]; 
				}
				NS[0] = temp;
				
			}else { // 반시계
				char temp = NS[0];
				for (int i = 1; i <= 7; i++) {
					NS[i-1] = NS[i]; 
				}
				NS[7] = temp;
			}
		}
	}
	
	static List<Gear> GearList;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		GearList = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			GearList.add(new Gear(br.readLine().toCharArray()));
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int idx = Integer.parseInt(st.nextToken())-1;
			int cw = Integer.parseInt(st.nextToken());
			
			Gear gear = GearList.get(idx);
			
			leftDFS(idx-1, gear, cw*-1); //양 옆체크
			rightDFS(idx+1, gear, cw*-1); // 오른쪽 체크
			gear.rotate(cw);
		}
		
		int ans = 0;
		for (int i = 0; i < 4; i++) {
			Gear gear = GearList.get(i);
			if(gear.NS[0] == '1') {
				ans += (int)Math.pow(2, i);
			}
		}
		System.out.println(ans);
	}


	private static void leftDFS(int left, Gear gear, int cw) {
		// 왼쪽 체크
		if (left < 0) return;
		if(GearList.get(left).NS[2] == gear.NS[6]) { // 만약 왼쪽과 극이 같다면
			return;
		}else {
			leftDFS(left-1, GearList.get(left), cw*-1);
			GearList.get(left).rotate(cw);
		}			
	}

	private static void rightDFS(int right, Gear gear, int cw) {
		// 오른쪽 체크
		if (right == 4) return;
		if(gear.NS[2] == GearList.get(right).NS[6]) { // 만약 왼쪽과 극이 같다면
			return;
		}else {
			rightDFS(right+1, GearList.get(right), cw*-1);
			GearList.get(right).rotate(cw);
		}
	}

}
