package week3.boj14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_KSG_Boj14891 {

	static final int GEAR_COUNT = 4;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] gears = new int[GEAR_COUNT+2];
		
		for(int i=1; i<=GEAR_COUNT; i++) {
			String s = br.readLine().trim();
			gears[i] = Integer.parseInt(s, 2);
			gears[i] |= gears[i] << 8;
			gears[i] |= gears[i] << 16;
		}
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int gearIdx = Integer.parseInt(st.nextToken());
			int rotation = Integer.parseInt(st.nextToken());
			rotateGear(gears,gearIdx,rotation, true, true);
		}
		System.out.println(((gears[1] & 0b10000000) >> 7) | ((gears[2] & 0b10000000) >> 6) | ((gears[3] & 0b10000000) >> 5) | ((gears[4] & 0b10000000) >> 4));
		br.close();
		
	}

	private static void rotateGear(int[] gears, int i, int rotation, boolean left, boolean right) {
		if(left && (((gears[i-1] >>> 4) ^ gears[i]) & 0b10) == 0b10) {
			if(i > 1) {
				rotateGear(gears,i-1,-rotation,true,false);
			}
		}
		if(right && (((gears[i+1] << 4) ^ gears[i]) & 0b100000) == 0b100000) {
			if(i < GEAR_COUNT) {
				rotateGear(gears,i+1,-rotation,false,true);
			}
		}
		gears[i] = Integer.rotateRight(gears[i],rotation);
		
	}

}
