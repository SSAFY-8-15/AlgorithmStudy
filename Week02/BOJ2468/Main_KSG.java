package week2.boj15558;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_KSG {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		char[] left = br.readLine().toCharArray();
		char[] right = br.readLine().toCharArray();
		System.out.println(bfs(n,k,left,right)?1:0);
		br.close();
	}

	private static boolean bfs(int n, int k, char[] left, char[] right) {
		Deque<int[]> deque = new LinkedList<int[]>();
		deque.add(new int[] {0,0,-1});
		left[0] = '2';
		int length = left.length;
		while(!deque.isEmpty()) {
			int[] arr = deque.removeFirst();
			int line = arr[0];
			int pos = arr[1];
			int cnt = arr[2];
			if(pos <= cnt) {
				continue;
			}
			if(pos >= length-k) {
				return true;
			}
			char[] currentLine;
			char[] oppositeLine;
			if(line == 0) {
				currentLine = left;
				oppositeLine = right;
			}else {
				currentLine = right;
				oppositeLine = left;
			}
			
			if(oppositeLine[pos+k] == '1') {
				oppositeLine[pos+k] = '2';
				deque.addLast(new int[] {line^1,pos+k,cnt+1});
			}
			if(currentLine[pos+1] == '1') {
				currentLine[pos+1] = '2';
				deque.addLast(new int[] {line,pos+1,cnt+1});
			}
			if(pos > 0 && currentLine[pos-1] == '1') {
				currentLine[pos-1] = '2';
				deque.addLast(new int[] {line,pos-1,cnt+1});
			}
			
		}
		return false;
	}
}
