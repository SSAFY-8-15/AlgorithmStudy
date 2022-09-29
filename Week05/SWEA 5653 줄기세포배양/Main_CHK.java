package SWEA5653;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int MAX; 
	static int N,M,K;
	static Cell[][] map;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static ArrayList<Point> allMap;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			MAX = 400;
			map = new Cell[MAX][MAX];
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<M; j++) {
					int power = Integer.parseInt(st.nextToken());
					if(power!=0) {
						map[MAX/2 - N + i][MAX/2 - M + j] = new Cell(power,1,0);						
					}
				}
			}
			
			
			//구현
			for(int k = 0; k<K; k++) {
				ArrayList<Point> arr = new ArrayList<>();
				for(int i = 0; i<MAX; i++) {
					for(int j = 0; j<MAX; j++) {
						if(map[i][j] == null) continue;//빈 공간
						Cell nowCell = map[i][j];
						if(nowCell.status == 2) continue;//죽은 세포
						if(nowCell.status == 0) {//비활성
							//1. power > time -> time+1;
							if(nowCell.power>nowCell.time) {
								nowCell.time += 1;
								continue;
							}
							//2. power == time -> time=1, 상태->활성화
							if(nowCell.power == nowCell.time) {
								nowCell.time = 1;
								nowCell.status = 1;
								continue;
							}
						}
						else if(nowCell.status == 1) {//활성
							//1. 활성화 상태에서 time=1 -> 4방 번식(나중에 한번에)
							if(nowCell.time==1) {
								arr.add(new Point(i, j, nowCell.power));
							}
							//2. time==power -> 상태->죽음
							if(nowCell.power == nowCell.time) {
								nowCell.status = 2;
								continue;
							}
							nowCell.time += 1;
							
						}
					}
				}
				
				//번식 한번에 처리
				for(int a = 0; a<arr.size(); a++) {
					Bunsik(arr.get(a).x,arr.get(a).y,arr.get(a).p);
				}
				
				
			}
			
			//count
			int count = 0;
			for(int i = 0; i<MAX; i++) {
				for(int j = 0; j<MAX; j++) {
					if(map[i][j]!=null && map[i][j].status!=2) count++;
				}
			}
			System.out.println("#"+t+" "+count);
			
		}

	}
	private static void Bunsik(int i, int j, int power) {
		for(int d = 0; d<4; d++) {
			int nx = i + dx[d];
			int ny = j + dy[d];
			
			//4방이 비어있는 공간이면 일단 넣는다
			if(map[nx][ny]==null) {
				map[nx][ny] = new Cell(power,1,0);
			}
			else {//비어있지 않은 경우
				//만약 비활성화 상태인데 time=1이면 동시에 들어온 cell이므로 크기 비교->내가 더 크면 바꿈
				if(map[nx][ny].status==0 && map[nx][ny].time==1 && map[nx][ny].power<power) {
					map[nx][ny] = new Cell(power,1,0);
				}
			}
		}
		
	}

}
class Cell{
	int power; //생명력
	int time; //지난 시간
	int status; //활성화 상태 - 비활성:0, 활성:1, 죽음:2
	public Cell(int power, int time, int status) {
		super();
		this.power = power;
		this.time = time;
		this.status = status;
	}
}

class Point{
	int x;
	int y;
	int p;
	public Point(int x, int y, int p) {
		super();
		this.x = x;
		this.y = y;
		this.p = p;
	}
	
	
}
