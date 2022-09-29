package DIY.problemSolve;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11497_BOJ {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (--T >= 0) {
			int n = Integer.parseInt(br.readLine());
			int[] p = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++)
				p[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(p);
			int[] sorted=new int[n];
			int mid=n/2;
			int size=0;
			int sw=1;
			int curMax=0;
			for(int i=n-1;i>=0;i--) {
				int cur=mid+size*sw;
				sorted[cur]=p[i];
				if(size>0) {
					int tmp;
					if(sw==-1) 
						tmp=Math.abs(sorted[cur]-sorted[cur+1]);
					else
						tmp=Math.abs(sorted[cur]-sorted[cur-1]);
					if(curMax<tmp) curMax=tmp; 
				}
				if(sw==1) 
					size++;
				sw*=-1;
			}
			int edgeDiff=Math.abs(sorted[0]-sorted[n-1]);
			sb.append((curMax>edgeDiff?curMax:edgeDiff)+"\n");
		}
		System.out.print(sb.toString());
	}
}
