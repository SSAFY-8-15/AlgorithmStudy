import java.util.*;

public class swea5656 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long m = sc.nextLong();

        long fi = five(n)-five(n-m)-five(m);
        long tw = two(n)-two(n-m)-two(m);

            System.out.println(Math.min(fi, tw));
        }
        static long five(long k) {
            int cnt = 0;
            while(k >= 5) {
                cnt += (k / 5);
                k /= 5;
            }
            return cnt;
        }
        static long two(long k) {
            int cnt = 0;
            
            while(k >= 2) {
                cnt += (k/ 2);
                k /= 2;
            }
            return cnt;
        }
}
