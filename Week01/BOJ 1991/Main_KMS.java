package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.*;
import java.util.StringTokenizer;

public class Main_1991_트리순회 {
	static HashMap<Character, char[]> map = new HashMap<>();
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			char[] temp = {left, right};
			map.put(root, temp);
		}	
		preOrder('A');
		System.out.println();
		inOrder('A');
		System.out.println();
		postOrder('A');
	}
	
	public static void preOrder(char node) {
		if(node == '.') return;
		System.out.print(node);
		preOrder(map.get(node)[0]);
		preOrder(map.get(node)[1]);
	}
	public static void inOrder(char node) {
		if(node == '.') return;
		inOrder(map.get(node)[0]);
		System.out.print(node);
		inOrder(map.get(node)[1]);
	}
	public static void postOrder(char node) {
		if(node == '.') return;
		postOrder(map.get(node)[0]);
		postOrder(map.get(node)[1]);
		System.out.print(node);
	}
}