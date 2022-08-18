package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_15558_scw {

	static int N, K;
	static int[][] map;
	static int[] moves;
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[2][N];
		moves= new int[] {-1, 1, K};
		
		String s = br.readLine();
		for (int i = 0; i < N; i++) {
			map[0][i] = s.charAt(i)-'0';
		}
		s = br.readLine();
		for (int i = 0; i < N; i++) {
			map[1][i] = s.charAt(i)-'0';
		}
		
		/*
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		*/
		
		if (game()) {
			System.out.println(1);
		}else System.out.println(0);
		
	}

	private static boolean game() {
		boolean[][] vst= new boolean[2][N];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0,0,0}); //bridge & position & time(delete)
		vst[0][0] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int b = cur[0];
			int p = cur[1];
			int t = cur[2];
			
			for (int d = 0; d < 3; d++) {
				int nb = b;
				int np = p + moves[d]; //move position
				int nt = t;
				if (d==2) {
					if (b==1) {
						nb = 0;
					}else
						nb = 1;
				}
				
				if(np >= N) return true;
				if(np <= nt) continue; // 시작하자마자 뒤로X, time칸 뒤로갈수 없음
				if(vst[nb][np]) continue;
				if(map[nb][np]==0) continue;
				vst[nb][np] = true;
				q.offer(new int[] {nb,np,nt+1});
				
			}
		}
		
		return false;
	}

}
