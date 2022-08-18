import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IMS_17281 {
	static boolean[] visited;
	static int[][] arr;
	static int[] hitter;
	static int n;
	static int res=0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][9]; 
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<9; j++) {
				arr[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[9];
		hitter = new int[9];
		visited[3]=true;
		hitter[3]=0;
		dfs(1);
		System.out.println(res);

	}
	private static void dfs(int count) {
		if(count==9) {
			start();
		}
		for(int i=0; i<9; i++) {
			if(!visited[i]) {
				visited[i]=true;
				hitter[i]=count;
				dfs(count+1);
				visited[i]=false;
			}
		}	
		
	}
	private static void start() {
		int score=0;
		int player =0;
		boolean[] base;
		
		for(int i=0; i<n; i++) {
			base = new boolean[4];
			int cnt=0;
			a: while(true) {
				for(int j = player; j<9; j++) {
					int hit = arr[i][hitter[j]];
					switch(hit) {
					case 0:
						cnt++;
						break;
					case 1:
						for(int s=3; s>=1; s--) {
							if(base[s]==true) {
								if(s==3) {
									score++;
									base[s]=false;
									continue;
								}
								base[s]=false;
								base[s+1]=true;
							}
						}
							base[1]=true;
							break;
							
						
					case 2:
						for(int s=3; s>=1; s--) {
							if(base[s]==true) {
								if(s==2||s==3) {
									score++;
									base[s]=false;
									continue;
								}
								base[s]=false;
								base[s+2]=true;
							}
						}
							base[2]=true;
							break;
					case 3:
						for(int s=1; s<=3; s++) {
							if(base[s]==true) {
								score++;
								base[s]=false;
							}
						}
							base[3]=true;
							break;
					case 4:
						for(int s=1; s<4; s++) {
							if(base[s]==true) {
								score++;
								base[s]=false;
							}
							
						}
							score++;
							break;

					}
					if(cnt==3) {
						player=j+1;
						if(player==10) {
							player=1;
						}
						break a;
					}
					}
					player=0;
				}
				
		}
		res = Math.max(res, score);
	}

}
