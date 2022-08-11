package java0805;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

//BJ1991 트리순회
public class Main_CHK {
	static char[][] map;
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][2];
		

		for(int i = 0; i<N; i++) {
			String[] str = br.readLine().split(" ");
			
			char parent = str[0].charAt(0);
			char leftChild = str[1].charAt(0);
			char rightChild = str[2].charAt(0);
			
			map[parent-65][0] = leftChild;
			map[parent-65][1] = rightChild;
		}

		sb = new StringBuilder();
		preOrder(0);
		sb.append("\n");
		inOrder(0);
		sb.append("\n");
		postOrder(0);
		sb.append("\n");
		

		System.out.println(sb);
	}
	private static void preOrder(int i) {
		char first = (char)(i+65);
		char second = map[i][0];
		char third = map[i][1];
		
		sb.append(first);
		
		if(second!='.') {
			preOrder(second-65);
		}

		if(third!='.') {
			preOrder(third-65);
		}


	}
	private static void inOrder(int i) {
		char first = (char)(i+65);
		char second = map[i][0];
		char third = map[i][1];
		
		if(second!='.') {
			inOrder(second-65);
		}
		
		sb.append(first);
		
		if(third!='.') {
			inOrder(third-65);
		}
		
	}
	private static void postOrder(int i) {
		char first = (char)(i+65);
		char second = map[i][0];
		char third = map[i][1];
		
		if(second!='.') {
			postOrder(second-65);
		}
	
		if(third!='.') {
			postOrder(third-65);
		}
		
		sb.append(first);
		
	}


}
