import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_CHK {
	static int N, K;
	static char[] map;
	static TreeSet<String> treeset;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			treeset = new TreeSet<>(Collections.reverseOrder());
			map = new char[N]; 
			String[] str = br.readLine().split("");
			for(int i = 0; i<N; i++) {
				map[i] = str[i].charAt(0);
			}
			
			saveNums();//첫번째 저장
			//N/4번 rotate
			for(int i = 0; i<(N/4); i++) {
				rotate();
				saveNums();// N/4개씩 묶어서 set에 저장
				}
			
			
			
			Iterator iter = treeset.iterator();	// Iterator 사용
			for(int i = 0; i<K-1; i++) {
				iter.next();
			}
			int ans = transform((String) iter.next());
			
			System.out.println("#"+t+" "+ans);
		}
	}
	
	
	private static void saveNums() {
		String num16 = "";
		for(int i = 1; i<N+1; i++) {
			if(i%(N/4)==1) {//(N/4)개를 모으고 다시 시작할 때
				num16 = "";//초기화
			}
			
			num16 += map[i-1];
			
			if(i%(N/4)==0) {//(N/4)개를 모을 때마다
				treeset.add(num16);
			}
		}
	}
	
	private static int transform(String s) {//16진수 ->10진수
		int num = Integer.parseInt(s, 16);
		return num;
	}
	private static void rotate() {
		char temp = map[N-1];
		for(int i = N-1; i>=1; i--) {
			map[i] = map[i-1];
		}
		map[0] = temp;
	}


}
