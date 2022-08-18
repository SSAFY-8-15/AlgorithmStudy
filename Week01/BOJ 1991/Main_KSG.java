
package week1.boj1991;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main_KSG {
	
	static int[] left = new int[27];
	
	static int[] right= new int[27];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader bis = new BufferedReader(new FileReader("res/1991/input.txt"));
		int n = Integer.parseInt(bis.readLine());
		for(int i=0; i<n; i++) {
			String s = bis.readLine();
			int parent = s.charAt(0) - 'A' + 1;
			left[parent] = s.charAt(2) - 'A' + 1;
			right[parent] = s.charAt(4) - 'A' + 1;
		}
		StringBuilder sb = new StringBuilder();
		preorder(1,sb);
		sb.append('\n');
		inorder(1,sb);
		sb.append('\n');
		postorder(1,sb);
		System.out.println(sb);
		bis.close();
	}

	private static void preorder(int i, StringBuilder sb) {
		if(i <= 0) {
			return;
		}
		sb.append((char)(i+'A'-1));
		preorder(left[i],sb);
		preorder(right[i],sb);
	}

	private static void inorder(int i, StringBuilder sb) {
		if(i <= 0) {
			return;
		}
		inorder(left[i],sb);
		sb.append((char)(i+'A'-1));
		inorder(right[i],sb);
	}

	private static void postorder(int i, StringBuilder sb) {
		if(i <= 0) {
			return;
		}
		postorder(left[i],sb);
		postorder(right[i],sb);
		sb.append((char)(i+'A'-1));
	}
}

