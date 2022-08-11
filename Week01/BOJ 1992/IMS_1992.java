import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IMS_1992 {
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<n; j++) {
				arr[i][j]=s.charAt(j)-'0';
			}
		}
		quad(0,0,n);
		System.out.println(sb);
	}
	private static void quad(int a, int b, int n) {
		if(check(a,b,n)) {
			sb.append(arr[a][b]);
			return;
		}
		int half = n/2;
		sb.append('(');
		quad(a,b,half);
		quad(a,b+half,half);
		quad(a+half,b,half);
		quad(a+half,b+half,half);
		sb.append(')');
	}
	private static boolean check(int a, int b, int n) {
		int val = arr[a][b];
		for(int i=a; i<a+n; i++) {
			for(int j=b; j<b+n; j++) {
				if(val!=arr[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	

}
