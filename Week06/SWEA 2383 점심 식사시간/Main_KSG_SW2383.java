package week6.sw2383;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_KSG_SW2383 {
	
	// 최대 사람 수
	static final int MAX_PEOPLE = 10;
	
	// 최대 출구 수
	static final int MAX_EXIT = 2;
	
	// 계단에 올라갈 수 있는 최대 크기
	static final int WINDOW_SIZE = 3;

	static int[] peopleI = new int[MAX_PEOPLE];
	static int[] peopleJ = new int[MAX_PEOPLE];
	
	static int[] exitI = new int[MAX_EXIT];
	static int[] exitJ = new int[MAX_EXIT];
	static int[] exitSize = new int[MAX_EXIT];
	
	static int[][] dist = new int[MAX_EXIT][MAX_PEOPLE];
	
	static People[][] peoplesInExit = new People[MAX_EXIT][MAX_PEOPLE];
	
	static People[] peoples = new People[MAX_PEOPLE];
	
	static Deque[] window = new Deque[MAX_EXIT];
	
	static {
		for(int i=0; i<MAX_EXIT; i++) {
			window[i] = new LinkedList<People>();
		}
	}
	
	static int answer;
	
	static int peopleCount;
	
	static int exitCount;
	
	static class People{
		int index;
		int i;
		int j;
		int enteredTime;
		
		public People(int index, int i, int j) {
			this.index = index;
			this.i = i;
			this.j = j;
		}
		
		public String toString() {
			return String.valueOf(index);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			peopleCount = 0;
			
			exitCount = 0;
			
			for(int i=0; i<n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					int w = Integer.parseInt(st.nextToken());
					if(w == 1) {
						peopleI[peopleCount] = i;
						peopleJ[peopleCount] = j;
						peopleCount++;
					}else if(w > 1) {
						exitI[exitCount] = i;
						exitJ[exitCount] = j;
						exitSize[exitCount] = w;
						exitCount++;
					}
				}
			}
			for(int i=0; i<peopleCount; i++) {
				peoples[i] = new People(i, peopleI[i],peopleJ[i]);
			}
			
			for(int i=0; i<exitCount; i++) {
				peoplesInExit[i] = new People[peopleCount];
				for(int j=0; j<peopleCount; j++) {
					dist[i][j] = Math.abs(peopleI[j]-exitI[i]) + Math.abs(peopleJ[j]-exitJ[i]);
					peoplesInExit[i][j] = peoples[j];
				}
				final int exitIdx = i;
				Arrays.sort(peoplesInExit[exitIdx],(x,y)->dist[exitIdx][x.index]-dist[exitIdx][y.index]);
			}
			
			answer = Integer.MAX_VALUE;
			int dMax = 1 << peopleCount;
			
			int[] peoplesWithExit = new int[peopleCount];
			
			for(int d=0; d<dMax; d++) {
				int c = d;
				for(int i=0; i<peopleCount; i++) {
					int exit = c%2;
					peoplesWithExit[i] = exit;
					c /= 2;
				}
				int max = 0;
				
				for(int i=0; i<exitCount; i++) {
					int elapsedTime = 0;
					int eSize = exitSize[i];
					Deque<People> w = window[i];
					
					for(int j=0; j<peopleCount; j++) {
						People people = peoplesInExit[i][j];
						int idx = people.index;
						
						if(peoplesWithExit[idx] == i) {
							
							while(!w.isEmpty() && w.peek().enteredTime + eSize <= elapsedTime) {
								w.poll();
							}
							
							if(w.size() == WINDOW_SIZE) {
								elapsedTime = w.poll().enteredTime+eSize;
							}
							elapsedTime = Math.max(elapsedTime, dist[i][idx]+1);
							
							people.enteredTime = elapsedTime;
							w.offer(people);
						}
					}
					if(!w.isEmpty()) {
						elapsedTime = w.peekLast().enteredTime+eSize;
						w.clear();
					}
					max = Math.max(max,elapsedTime);
				}
				
				for(int i=0; i<exitCount; i++) {
				}
				answer = Math.min(answer, max);
			}
			sb.append("#").append(t).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
		br.close();
	}
}
