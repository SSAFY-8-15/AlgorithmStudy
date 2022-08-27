package Week02;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17281_야구 {
	
	static int[]p = {1,2,3,4,5,6,7,8};
	//static int[]p = {4, 5, 6, 7, 8, 1, 2, 3};
	 
	static int max;
	static boolean [] visited;
	static int num4;
	static boolean[] bases; // 베이스 상황
	static int current; // 현재 타석
	static int maxPoint = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int answer = 0;
		int count = 0;
		int[][] results = new int[N][9]; // 순서
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			// 매 이닝의 결과들 저장 
			for (int j = 0; j < 9; j++) 
				results[i][j] = Integer.parseInt(st.nextToken());
			
			// 1이닝마다 중복 순열을 통해 최대의 이익을 찾아야함
			answer += max;
		}
		do {
			int point = 0; // 현재 순열의 점수
			int order = 0; // 현재 타자 번호
			for (int inning = 0; inning < N; inning++) {
				int outCnt = 3;
				bases = new boolean[3];
				while(outCnt > 0) {
					if (order < 3) {
						int playerNum = p[order]; //현재 타자 번호
						int result = results[inning][playerNum];
						point += getPoint(result);
						if (result == 0) {
							outCnt --;
						}
						order++;
					}
					
					else if (order == 3) {
						//지명 타자 치기
						int result = results[inning][0];
						point += getPoint(result);
						if (result == 0) {
							outCnt --;
						}
						order++;
					}
					else {
						int playerNum = p[order-1]; //현재 타자 번호
						int result = results[inning][playerNum];
						point += getPoint(result);
						if (result == 0) {
							outCnt --;
						}
						order = (order+1)%9;
					}
					//System.out.println(Arrays.toString(bases));
					//System.out.println(outCnt);
					
				}
			}
			if(point > maxPoint) {
				maxPoint = Math.max(point, maxPoint);
				System.out.println(Arrays.toString(p));
				System.out.println(maxPoint);
			}
			//break;
		}while(np(7));
		System.out.println(maxPoint);
	}
	
	
	private static int getPoint(int point) {
		int temp = 0;
		
		//아웃
		if (point == 0) return 0;
		
		//홈런
		else if(point == 4) {
			for (int j = 0; j < 3; j++) {
				if (bases[j]) temp++;
				bases[j] = false;
			}
			return ++temp;
		}
		
		//1루타 2, 3루타
		else {
			//점수 내는 곳
			for (int j = 3-point; j <3 ; j++) {
				if (bases[j]) {
					temp ++;
					bases[j] = false;
				}
			}
			
			//타자 이동.
			for (int j = 2-point; j > -1 ; j--) {
				bases[j+point] = bases[j];
			}
			bases[point-1] = true;
			return temp;
		}
	}

	private static boolean np(int size) {
		int i = size;
		while(i > 0 && p[i-1] > p[i])i--;
		if(i==0) return false;
		
		int j = size;
		while(p[i-1] > p[j]) j--;
		swap(i-1, j);
		
		int k = size;
		while(i < k) swap(i++, k--);

		return true;
	}

	private static void swap(int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}
}
