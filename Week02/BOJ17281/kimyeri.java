import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] p;
	static int[] idx;
	static int[] cand = {1,2,3,4,5,6,7,8};
	static int max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		p = new int[N][9];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<9;j++) {
				p[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		idx = new int[8];
		npr(0, new boolean[8]);
		System.out.println(max);
	}
	private static void npr(int cnt, boolean[] visited) {
		if(cnt==8) {
			int result = simulate();
			max = Math.max(result, max);
			return;
		}
		for(int i=0;i<8;i++) {
			if(visited[i]) continue;
			visited[i]=true;
			idx[cnt]=cand[i];
			npr(cnt+1,visited);
			visited[i]=false;
		}
	}
	private static int simulate() {
		int res = 0;
		int curNum = 0;// n번 타자 
		int now;
		for(int i=0;i<N;i++) {
			int out = 0;
			boolean[] base = new boolean[3];
			while(true) {
				now = 0;
				if(curNum==3) now = p[i][0]; // 4번 타자는 1번 선수
				else if(curNum<3) now = p[i][idx[curNum]];
				else now=p[i][idx[curNum-1]];
				if(0<now && now<4) {
					for(int b=2;b>-1;b--) {
						if(base[b]) {
							if(b+now>2) { // 가장 끝에 있는 루부터 선수 진출시키기
								res+=1;
								base[b]=false;
							} else {
								base[b+now] = true;
								base[b]=false;
							}
						}
					}
					base[now-1]=true; // 한 선수씩 진출
				} else if (now == 4) {
                    // 홈런
					res+=1;
					for(int b=0;b<3;b++) { // base에 있는 선수들 내보내기
						if(base[b]) res+=1;
						base[b]=false;
					}
				} else { // out 세기
					out+=1;
				}
				curNum++; // 다음 타자 번호
				if(curNum==9) curNum=0; // 번호 초기화
				if(out==3) {
					out=0;
					break;
				}
			}
		}
		return res;
	}
}
