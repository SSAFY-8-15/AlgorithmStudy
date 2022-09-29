package study;

import java.io.*;
import java.util.*;

public class Solution_2382 {
	
	static int N,M,K;
	static int ans;
    static int[][] map;
    static ArrayList<micro> mlist;
    static PriorityQueue<micro> pq;
    // 1:상  2:하  3:좌  4:우
    static int[][] dir = { {0,0}, {-1,0}, {1,0}, {0,-1}, {0,1} }; //상하좌우 dir
    
    static class micro{
        int r, c, num, d;
        micro(int r,int c,int num,int d){
            this.r = r;
            this.c = c;
            this.num = num;
            this.d = d;
        }
    }

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for(int t = 1; t <= TC; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 셀의 갯수
            M = Integer.parseInt(st.nextToken()); // 격리 시간
            K = Integer.parseInt(st.nextToken()); // 미생물 군집의 갯수

            map = new int[N][N];
            mlist = new ArrayList<>();
            ans = 0;
            
            // 미생물의 수가 많은 순으로 정렬
            pq = new PriorityQueue<>((o1,o2)->(o2.num - o1.num));

            for(int i=1; i < K+1; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                map[x][y] = i; // map 배열에는 각 군집의 인덱스를 저장한다
                mlist.add(new micro(x,y,num,d));
            }
            
            for (int i = 0; i < M; i++) {
				move();
			}
            
            for (int i = 0; i < mlist.size(); i++) {
				ans += mlist.get(i).num;
			}
            
            System.out.println("#" + t + " " + ans);
        }


	}

	private static void move() {
		//이동시키고 start
		for (int i = 0; i < mlist.size(); i++) {
			micro cur = mlist.get(i);
			map[cur.r][cur.c] = 0;
			int nr = cur.r + dir[cur.d][0];
			int nc = cur.c + dir[cur.d][1];
			int num = cur.num;
			int d = cur.d;
			pq.add(new micro(nr, nc, num, d));
		}
		mlist.clear(); // 다날리고 남은거 다시 추가할것
		
		
		while (!pq.isEmpty()) {
			micro nxt = pq.poll();
			
			if (nxt.r <= 0 || nxt.r >= N-1 || nxt.c <= 0 || nxt.c >= N-1) {
				nxt.num /= 2;
				
				if (nxt.num == 0) {
					continue;
				}
				
				if (nxt.num > 0) {
					//방향 반대로
					if (nxt.d == 2 || nxt.d == 4) { 
						nxt.d = nxt.d-1;
					}else {
						nxt.d  = nxt.d+1;
					}
				}
				
				mlist.add(nxt);
				
			}else {
				if (map[nxt.r][nxt.c] == 0) {
					mlist.add(nxt);
					map[nxt.r][nxt.c] = mlist.size();
				}else {
					mlist.get(map[nxt.r][nxt.c]-1).num += nxt.num;
				}
			}
		}
		
	}

}
