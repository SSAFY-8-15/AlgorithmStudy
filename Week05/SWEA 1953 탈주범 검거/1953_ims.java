import java.util.*;

import java.io.*;

public class swea1953{

    static class tj{
        int x,y;
        public tj(int x,int y){
            x=this.x;
            y=this.y;
        }
    }
    static int n, m, r, c, l;
    static int [][]arr;
    static boolean[][] visited;
    static int ans;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int[][] tunnel = {
        {0,0,0,0},
        {1,1,1,1},
        {1,0,1,0},
        {0,1,0,-1},
        {1,1,0,0},
        {0,1,1,0},
        {0,0,1,1},
        {1,0,0,1},
    };

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc =1; tc<=T; tc++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            arr= new int[n][m];
            visited = new boolean[n][m];
            int ans =0;
            for(int i =0; i<n; i++){
                st  = new StringTokenizer(br.readLine());
                for(int j=0; j<m; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println("#"+tc+" "+bfs());
        }

        }
    private static int bfs(){
        int ans=0;
        visited[r][c]=true;
        int time = 0;

        Queue<tj> q = new LinkedList<>();
        q.add(new tj(r,c)); 
        while(!q.isEmpty()){
            if(time ==l-1) break;
            int size = q.size();
            while(size>0){
            tj temp = q.poll();
            int[] go  = tunnel[arr[temp.x][temp.y]];
            for(int d=0; d<go.length; d++){
                if(go[d]==0) continue;
                int rx =temp.x+dr[d];
                int ry = temp.y+dc[d];
                if(!checked(rx,ry))continue;
                if(visited[rx][ry])continue;
                if(tunnel[arr[rx][ry]][d%4]==1){
                    visited[rx][ry]=true;
                    q.offer(new tj(rx,ry));
                    ans +=1;
                }
            }
            }
            time += 1;
        }
        return ans;

    }
    private static boolean checked(int a, int b){
        return a<n && a>=0 && b<m&& b>=0;
    }
}
