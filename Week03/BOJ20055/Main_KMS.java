package Week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_20055_컨베이어_벨트 {

	static class robots{
		int x;

		public robots(int x) {
			this.x = x;
		}
	}
	
	static int[] belt;	
	static int[] robots;
	static int cnt; // 내구도 0 개수;
	static int N, K;
	static int round = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int rcnt = 0;
		belt = new int[N*2];
		robots = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N*2; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			// 1. 로봇과 컨베이어 벨트 1칸 이동
			rotate();

			// 2. 가장 먼저 올라간 로봇 부터 회전하는 방향으로 이동 체크
			for (int x = N-2; x >= 0; x--) {
				if(!check(x)) continue;
				//2-1. 로봇 이동
				robots[x+1] = robots[x];
				robots[x] = 0;
				belt[x+1]--; 
				// 내구도 삭제 
				if(belt[x+1] == 0) cnt ++;
			}
			robots[N-1] = 0;
			
			// 3. 올리는 위치 칸 내구도 1 이상 > 로봇 올리기
			if (belt[0] > 0) {
				robots[0] = (rcnt++)%9 +1;
				belt[0]--;
				if(belt[0] == 0) cnt ++;
			}
			
			// 4. 내구도 0 개수 세기
			round++;
			if (cnt == K) {
				break;
			}
		}
		System.out.println(round);
	}

	private static boolean check(int i) {
		if (robots[i]==0 || robots[i+1] > 0 || belt[i+1] == 0) return false;
		return true;
	}

	private static void rotate() {
		int temp1 = belt[N*2-1];
		
		for (int i = N*2-2; i >= 0; i--) {
			belt[i+1] = belt[i];
		}
		belt[0] = temp1;
		
		for (int i = N-2; i >= 0; i--) {
			robots[i+1] = robots[i];
		}
		robots[0] = 0;
		robots[N-1] = 0; // N번째 무조건 로봇 뽑기
	}

}
