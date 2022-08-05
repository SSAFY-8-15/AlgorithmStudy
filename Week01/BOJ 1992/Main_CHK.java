package java0803;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//BJ1992 쿼드트리
public class Main_CHK {
	static int[][] map;
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for(int i = 0; i<N; i++) {
			String[] str = br.readLine().split(""); 
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		sb = new StringBuilder();
		search(0,0,N-1,N-1);
		System.out.println(sb);
	}
	private static void search(int startX, int startY, int endX, int endY) {

		if(startX>endX || startY>endY) {
			return;
		}
		
		//-->구간의 숫자들이 모두 같은지 확인
		int stand = map[startX][startY];
		boolean allEqual = true;
		for(int i = startX; i<=endX; i++) {
			for(int j = startY; j<=endY; j++) {
				if(map[i][j] != stand) {
					allEqual = false;
				}
			}
		}
		//<--모두 같으면 true, 하나라도 다르면 false
		
		if(allEqual) {
			sb.append(stand);
			
		}
		else {//false면 구간을 또 4등분 한다
			sb.append("(");
			int midX = (startX+endX)/2;
			int midY = (startY+endY)/2;
			search(startX,startY,midX,midY);//왼쪽위
			search(startX,midY+1,midX,endY);//오른쪽위
			search(midX+1,startY,endX,midY);//왼쪽아래
			search(midX+1,midY+1,endX,endY);//오른쪽아래
			sb.append(")");
		}
		
	}

}
