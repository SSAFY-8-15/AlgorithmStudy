import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IMS_1991 {
	static StringBuilder sb =new StringBuilder();
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		arr= new int[n][2];
		for(int i=0; i<n; i++ ) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int node = st.nextToken().charAt(0)-'A';
			arr[node][0] = st.nextToken().charAt(0)-'A';
			arr[node][1] = st.nextToken().charAt(0)-'A';
		}
		preorder(0);
		sb.append('\n');
		inorder(0);
		sb.append('\n');
		postorder(0);
		
		System.out.println(sb);
	}

	private static void preorder(int i) {
		sb.append((char)(i+'A'));
		if(arr[i][0]>0)preorder(arr[i][0]);
		if(arr[i][1]>0)preorder(arr[i][1]);
		
	}
	private static void inorder(int i) {
		if(arr[i][0]>0)inorder(arr[i][0]);
		sb.append((char)(i+'A'));
		if(arr[i][1]>0)inorder(arr[i][1]);
	}
	private static void postorder(int i) {
		if(arr[i][0]>0)postorder(arr[i][0]);
		if(arr[i][1]>0)postorder(arr[i][1]);
		sb.append((char)(i+'A'));
	}
	
	

}
