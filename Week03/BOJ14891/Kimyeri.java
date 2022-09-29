import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int[][] wheels;
	public static void main(String[] args)  throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		wheels = new int[4][8];
		for(int i=0;i<4;i++) {
			char[] temp = br.readLine().toCharArray();
			for(int j=0;j<8;j++) {
				wheels[i][j] = temp[j]-'0';
			}
		}
		K = Integer.parseInt(br.readLine());
		for(int k =0;k<K;k++) {
			st  = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken())-1; 
			int dir = Integer.parseInt(st.nextToken()); 
			int[] status = new int[4];
			status[n]=dir;
			// 왼쪽 체크
			int idx = n;
			while(0<idx) {
				if(wheels[idx-1][2]!=wheels[idx][6]) {
					status[idx-1] = -status[idx]; // 반대 방향
				} else break;
				idx--;
			}
			// 오른쪽 체크
			idx = n;
			while(idx<3) {
				if(wheels[idx][2]!=wheels[idx+1][6]) {
					status[idx+1] = -status[idx]; // 반대 방향
				} else break;
				idx++;
			}
			// 돌리기
			for(int i=0;i<4;i++) {
				int d = status[i];
				if(d==0) continue;
				int ni = 0;
				int temp = wheels[i][ni];
				int cnt=0;
				while(cnt<8) {
					ni = (ni+d)%8!=-1 ? (ni+d)%8:7;
					int now = wheels[i][ni];
					wheels[i][ni] = temp;
					temp = now;
					cnt++;
				}
			}
		}
		int sum= 0;
		for(int i=0;i<4;i++) {
			sum+= Math.pow(2, i)*wheels[i][0]; 
		}
		System.out.println(sum);
		
	}

}