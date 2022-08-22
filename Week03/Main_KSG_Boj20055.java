package week3.boj20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_KSG_Boj20055 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] durability = new int[2*n];
		boolean[] conveyor = new boolean[durability.length];
		
		int n2 = 2*n;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n2; i++) {
			durability[i] = Integer.parseInt(st.nextToken());
		}
		
		int workCnt = 1;
		int stoppedCnt = 0;
		
		while(true) {
			rotate(conveyor, durability, n);
			conveyor[n-1] = false;
			
			stoppedCnt += moveRobot(durability, conveyor, n);
			conveyor[n-1] = false;
			
			if(durability[0] > 0) {
				conveyor[0] = true;
				durability[0]--;
				if(durability[0] == 0) {
					stoppedCnt++;
				}
			}
			
			if(stoppedCnt >= k) break;
			workCnt++;
		}
		System.out.print(workCnt);
		br.close();
	}

	private static void rotate(boolean[] conveyor, int[] durability, int n) {
		boolean tmp = conveyor[2*n-1];
		int tmp2 = durability[2*n-1];
		for(int i=2*n-1; i>0; i--) {
			conveyor[i] = conveyor[i-1];
			durability[i] = durability[i-1];
		}
		conveyor[0] = tmp;
		durability[0] = tmp2;
	}

	private static int moveRobot(int[] durability, boolean[] conveyor, int n) {
		int stopped = 0;
		for(int i=n-1; i>0; i--) {
			if(moveRobot(durability, conveyor, i-1, i)) {
				stopped++;
			}
		}
		return stopped;
	}

	private static boolean moveRobot(int[] durability, boolean[] conveyor, int i, int j) {
		if(!conveyor[i]) {
			return false;
		}
		if(durability[j] > 0 && !conveyor[j]) {
			conveyor[i] = false;
			conveyor[j] = true;
			durability[j]--;
			return durability[j] == 0;
		}
		return false;
	}

}
