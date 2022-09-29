package java0824;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20055_CHK {
	static int N,K,naeguZero;
	static int[] naegudo;
	static int[] robot;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		robot = new int[2*N];
		naegudo = new int[2*N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<2*N; i++) {
			naegudo[i] = Integer.parseInt(st.nextToken());
		}
		
		int round = 0;
		while(naeguZero<K) {
			round++;
			
			//컨베이어 벨트 회전
			rotate();
			
			if(robot[N-1] == 1) robot[N-1] = 0; //로봇 내리기
			
			//가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
			//로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
			moveRobot();
			
			if(robot[N-1] == 1) robot[N-1] = 0; //로봇 내리기
			
			
			//올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			putRobot();
			
			//내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			naeguZero=0;
			for(int nae : naegudo) {
				if(nae==0) naeguZero++;
			}
			
		}
		System.out.println(round);
		
		

	}

	private static void putRobot() {
		if(naegudo[0]>0) {
			robot[0] = 1;
			naegudo[0]--;
		}
		
	}

	private static void moveRobot() {
		//로봇이 있는 칸은, 다음 칸이 내구도가 0 이상이고, 로봇이 없는 칸이라면 로봇을 옮긴다
		for(int i = N-2; i>=0; i--) {
			if(robot[i]==1) {
				if(naegudo[i+1] > 0 && robot[i+1]==0) {
					robot[i+1] = 1;
					robot[i] = 0;
					naegudo[i+1]--;
				}
			}
		}
		
	}

	private static void rotate() {
		//내구도 이동
		int temp = naegudo[2*N-1];
		for(int i = 2*N-1; i>=1; i--) {
			naegudo[i] = naegudo[i-1];
		}
		naegudo[0] = temp;
		
		//로봇도 같이 회전
		temp = robot[2*N-1]; 
		for(int i = 2*N-1; i>=1; i--) {
			robot[i] = robot[i-1];
		}
		robot[0] = temp;
		
	}





}
