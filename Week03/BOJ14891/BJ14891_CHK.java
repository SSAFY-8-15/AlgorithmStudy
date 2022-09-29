import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
    static int K;
    static int[][] magnet;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        magnet = new int[4][8];
        for(int i = 0; i<4; i++) {
            String[] str = br.readLine().split("");
            for(int j = 0; j<8; j++) {
                magnet[i][j] = Integer.parseInt(str[j]);
            }
        }
        K = Integer.parseInt(br.readLine());    
        for(int k = 0; k<K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken());
 
            //회전
            rotate(num, r);     

        }
        //계산
        int ans = 0;
        for(int i = 0; i<4; i++) {
            ans += magnet[i][0] * Math.pow(2, i);
        }
        System.out.println(ans);
             
             
     
 
    }
    private static void rotate(int num, int r) {
        int left = magnet[num][6];
        int right = magnet[num][2];
         
        //자석num을 r방향으로 1칸 돌린다.
        if(r==1) {//시계
            clock(num);
        }
        else {//반시계
            nonClock(num);
        }
 
        int nowR = r;
        if(num==0) {
            //오른쪽만 3번 봐
            for(int nowMagnet = 0; nowMagnet<3; nowMagnet++) {
                if(magnet[nowMagnet+1][6]!=right) {
                    nowR = nowR*(-1);//이전 자석과는 반대니까
                    right = magnet[nowMagnet+1][2];
                    if(nowR==1) clock(nowMagnet+1);
                    else nonClock(nowMagnet+1);
                }
                else {
                    break;
                }
            }
        }
        else if(num==1) {
            //왼1번
            if(magnet[0][2]!=left) {
                nowR = nowR*(-1);//이전 자석과는 반대니까
                if(nowR==1) clock(0);
                else nonClock(0);
            }
            nowR = r;
            //오른2번
            for(int nowMagnet = 1; nowMagnet<3; nowMagnet++) {
                if(magnet[nowMagnet+1][6]!=right) {
                    nowR = nowR*(-1);//이전 자석과는 반대니까
                    right = magnet[nowMagnet+1][2];
                    if(nowR==1) clock(nowMagnet+1);
                    else nonClock(nowMagnet+1);
                }
                else {
                    break;
                }
            }
             
        }
        else if(num==2) {
            //왼2번
            for(int nowMagnet = 2; nowMagnet>0; nowMagnet--) {
                if(magnet[nowMagnet-1][2]!=left) {
                    nowR = nowR*(-1);//이전 자석과는 반대니까
                    left = magnet[nowMagnet-1][6];
                    if(nowR==1) clock(nowMagnet-1);
                    else nonClock(nowMagnet-1);
                }
                else {
                    break;
                }
            }
            nowR = r;
            //오른1번
            if(magnet[3][6]!=right) {
                nowR = nowR*(-1);//이전 자석과는 반대니까
                if(nowR==1) clock(3);
                else nonClock(3);
            }
             
        }
        else {//제일오른쪽자석
            //왼만 3번 봐
            for(int nowMagnet = 3; nowMagnet>0; nowMagnet--) {
                if(magnet[nowMagnet-1][2]!=left) {
                    nowR = nowR*(-1);//이전 자석과는 반대니까
                    left = magnet[nowMagnet-1][6];
                    if(nowR==1) clock(nowMagnet-1);
                    else nonClock(nowMagnet-1);
                }
                else {
                    break;
                }
            }
        }
         
 
    }
 
    private static void clock(int num) {
        int temp = magnet[num][7];
        for(int i = 6; i>=0; i--) {
            magnet[num][i+1] = magnet[num][i];
        }
        magnet[num][0] = temp;
         
    }
    private static void nonClock(int num) {
        int temp = magnet[num][0];
        for(int i = 0; i<=6; i++) {
            magnet[num][i] = magnet[num][i+1];
        }
        magnet[num][7] = temp;
         
    }
 
}
