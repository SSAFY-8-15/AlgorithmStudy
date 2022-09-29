package SWEA2382;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N,M,K;
	static ArrayList<MSM> arr;
	static ArrayList<MSM>[][] map;
	static int[] dx = {0,-1,1,0,0};
	static int[] dy = {0,0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new ArrayList<>();
			
			for(int k = 0; k<K; k++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				arr.add(new MSM(r, c, n, d));
			}
			
			//구현
			for(int m = 0; m<M; m++) {//m번 움직이기
				int size = arr.size(); 
				if(size==0) break;
				//map[][] 초기화
				map = new ArrayList[N][N];
				for(int i = 0; i<N; i++) {
					for (int j = 0; j<N; j++) {
						map[i][j] = new ArrayList<>();
					}
				}
				
				for(int s = 0; s<size; s++) {//살아있는 모든 미생물 1번씩 움직이기
					arr.get(s).r = arr.get(s).r + dx[arr.get(s).d]; 
					arr.get(s).c = arr.get(s).c + dy[arr.get(s).d];
					
					//가장자리에 들어갔을 경우
					if(arr.get(s).r == 0 || arr.get(s).c == 0 ||
							arr.get(s).r == N-1 ||arr.get(s).c == N-1) {
						//절반으로 줄이기
						arr.get(s).n = arr.get(s).n/2; 
						//위치 바꾸기 1->2, 2->1, 3->4, 4->3
						int dir = arr.get(s).d;
						if(dir == 1 || dir == 3) {
							arr.get(s).d += 1;
						}
						else if(dir == 2 || dir == 4) {
							arr.get(s).d -= 1;
						}

					}
					map[arr.get(s).r][arr.get(s).c].add(new MSM(arr.get(s).r, arr.get(s).c, arr.get(s).n, arr.get(s).d));
				}
				arr = new ArrayList<>(); //arr초기화 (밑에서 계산하고 필요한 것만 다시 넣을 것이므로)
				
				//할 일
				//1. 없어져야 할 군집은 없애기
				//2. 합칠거 합치기
				for(int i = 0; i<N; i++) {
					for (int j = 0; j<N; j++) {
						if(map[i][j].size()==0) continue;//담지 않고 넘어감
						if(map[i][j].size()==1) {//n==0인걸 제외하고 담고 끝냄
							if(map[i][j].get(0).n!=0) {
								arr.add(new MSM(map[i][j].get(0).r, map[i][j].get(0).c, map[i][j].get(0).n, map[i][j].get(0).d));
							}
							continue; 
						}
						//2개이상일때
						int sum = 0;
						int max_index = -1;
						int max = -1;
						for(int h = 0; h<map[i][j].size(); h++) {
							sum += map[i][j].get(h).n;
							if(max<map[i][j].get(h).n) {
								max = map[i][j].get(h).n;
								max_index = h;
							}
						}
						
						arr.add(new MSM(map[i][j].get(max_index).r, map[i][j].get(max_index).c, sum, map[i][j].get(max_index).d));
					}
				}
			
			}
			
			if(arr.size()==0) {
				System.out.println("#"+t+" "+0);
			}else {
				int ans = 0;
				for(int i = 0; i<arr.size(); i++) {
					ans += arr.get(i).n;
				}
				System.out.println("#"+t+" "+ans);
			}
			
			
			
		}
		
	}
}
class MSM{
	int r;
	int c;
	int n;
	int d;
	public MSM(int r, int c, int n, int d) {
		super();
		this.r = r;
		this.c = c;
		this.n = n;
		this.d = d;
	}
}
