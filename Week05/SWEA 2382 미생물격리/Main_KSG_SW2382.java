package week5.sw2382;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_KSG_SW2382 {
	
	static class Microbe implements Comparable<Microbe>{
		int i;
		int j;
		int c;
		int d;
		public Microbe(int i, int j, int c, int d) {
			this.i = i;
			this.j = j;
			this.c = c;
			this.d = d;
		}
		
		@Override
		public int compareTo(Microbe o) {
			return o.c-c;
		}
	}
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int n;
	
	static int changeDir(int d) {
		switch(d) {
			case 0:
				return 1;
			case 1:
				return 0;
			case 2:
				return 3;
			case 3:
				return 2;
			default:
				return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			Microbe[][] table = new Microbe[n][n];
			Queue<Microbe> microbese = new LinkedList<>();
			PriorityQueue<Microbe> tmp = new PriorityQueue<>();
			for(int q=0; q<k; q++) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken())-1;
				Microbe mb = new Microbe(i,j,c,d);
				microbese.add(mb);
				table[i][j] = mb;
			}
			
			while(m-- > 0) {
				while(!microbese.isEmpty()) {
					Microbe mb = microbese.poll();
					mb = table[mb.i][mb.j];
					if(mb != null) {
						table[mb.i][mb.j] = null;
						int d = mb.d;
						
						mb.i += dr[d];
						mb.j += dc[d];
						
						if(mb.i == 0 || mb.i == n-1 || mb.j == 0 || mb.j == n-1) {
							mb.c /= 2;
							if(mb.c == 0) {
								continue;
							}
							mb.d = changeDir(d);
						}
						tmp.add(mb);
					}
				}
				while(!tmp.isEmpty()) {
					Microbe mb = tmp.poll();
					Microbe mb2 = table[mb.i][mb.j];
					if(mb2 != null) {
						mb2.c += mb.c;
						mb = mb2;
					}else {
						table[mb.i][mb.j] = mb;
						microbese.add(mb);
					}
				}
			}
			int answer = 0;
			for(Microbe mb : microbese) {
				mb = table[mb.i][mb.j];
				if(mb != null) {
					answer += mb.c;
					table[mb.i][mb.j] = null;
				}
			}
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
		br.close();
	}
}
