package java0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*구현*/
public class BJ14891_CHK {
	static int K;
	static int[][] T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = new int[4][8];
		for(int i = 0; i <4; i++) {
			String[] str = br.readLine().split("");
			for(int j = 0; j<8; j++) {
				T[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int wheelNum = Integer.parseInt(st.nextToken())-1;
			int wheelDir = Integer.parseInt(st.nextToken()); //1=시계, -1=반시계
			

			boolean[] isRotate = new boolean[4];
			isRotate[wheelNum] = true;
			int[] rotateDir = new int[4];
			rotateDir[wheelNum] = wheelDir;

			//돌아갈 다른 휠 확인
			if(wheelNum==0) {//입력된 기준 휠이 0
				for(int r = 0; r<3; r++) {
					if((T[wheelNum][2] != T[wheelNum+1][6]) && isRotate[wheelNum]) {//다르면 && 지금 휠이 회전할거면
						isRotate[wheelNum+1] = true; //오른쪽 휠도 돌아감
						rotateDir[wheelNum+1] = rotateDir[wheelNum]*(-1);
					}
					wheelNum++;
				}
			}
			else if(wheelNum==1) {//입력된 기준 휠이 1
				if(T[wheelNum][6] != T[wheelNum-1][2]) {
					isRotate[wheelNum-1] = true;
					rotateDir[wheelNum-1] = rotateDir[wheelNum]*(-1);
				}
				for(int r = 0; r<2; r++) {
					if((T[wheelNum][2] != T[wheelNum+1][6]) && isRotate[wheelNum]) {//다르면 && 지금 휠이 회전할거면
						isRotate[wheelNum+1] = true; //오른쪽 휠도 돌아감
						rotateDir[wheelNum+1] = rotateDir[wheelNum]*(-1);
					}
					wheelNum++;
				}
				
			}
			else if(wheelNum==2) {//입력된 기준 휠이 2
				if(T[wheelNum][2] != T[wheelNum+1][6]) {
					isRotate[wheelNum+1] = true;
					rotateDir[wheelNum+1] = rotateDir[wheelNum]*(-1);
				}
				for(int r = 0; r<2; r++) {
					if((T[wheelNum][6] != T[wheelNum-1][2]) && isRotate[wheelNum]) {//다르면 && 지금 휠이 회전할거면
						isRotate[wheelNum-1] = true; //왼쪽 휠도 돌아감
						rotateDir[wheelNum-1] = rotateDir[wheelNum]*(-1);
					}
					wheelNum--;
				}
			}
			else if(wheelNum==3) {//입력된 기준 휠이 3
				for(int r = 0; r<3; r++) {
					if((T[wheelNum][6] != T[wheelNum-1][2]) && isRotate[wheelNum]) {//다르면 && 지금 휠이 회전할거면
						isRotate[wheelNum-1] = true; //왼쪽 휠도 돌아감
						rotateDir[wheelNum-1] = rotateDir[wheelNum]*(-1);
					}
					wheelNum--;
				}
			}
			

			
			for(int j = 0; j<4; j++) {
				rotate(j,rotateDir[j], isRotate[j]);//타겟 휠 돌리기
			}
			
		}
		
		
		//최종 값 계산
		int[] cost = {1,2,4,8};
		int ans = 0;
		for(int i = 0; i<4; i++) {
			if(T[i][0]==1) {
				ans += cost[i];
			}
			 
		}
		System.out.println(ans);


	}
	private static void rotate(int wheelNum, int wheelDir, boolean isRotate) {
		int[] tempT = new int[8];
		for(int i = 0; i<8; i++) {
			tempT[i] = T[wheelNum][i];
		}
		
		if(isRotate == false) {
			return;
		}
		else {

			if(wheelDir==1) {//시계
				for(int q = 0; q<8; q++) {
					if(q==0) {
						T[wheelNum][q] = tempT[7];
					}
					else {
						T[wheelNum][q] = tempT[q-1];
					}
				}
				
			}
			else {//반시계
				for(int i = 0; i<8; i++) {
					if(i==7) {
						T[wheelNum][i] = tempT[0];
					}
					else {
						T[wheelNum][i] = tempT[i+1];
					}
				}
			}
		}
		
	}
	
}