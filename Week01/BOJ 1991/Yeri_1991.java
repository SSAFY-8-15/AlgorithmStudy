package algo0806;

import java.util.HashMap;
import java.util.Scanner;

public class Yeri_1991 {
	static class Node{
		String right;
		String left;
		public Node(String l, String r) {
			left = l;
			right = r;
		}
	}
	static int N;
	static HashMap<String,Node> map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new HashMap<>();
		for(int i=0;i<N;i++) {
			String start = sc.next();
			String left = sc.next();
			String right = sc.next();
			if(left.equals(".")) left = null;
			if(right.equals(".")) right = null;
			map.put(start, new Node(left,right));
		}
		
		prefix("A");
		System.out.println();
		midfix("A");
		System.out.println();
		postfix("A");
	}
	private static void prefix(String cur) {
		Node curNode = map.get(cur);
		System.out.printf("%s",cur);
		if(curNode.left!=null) prefix(curNode.left);
		if(curNode.right!=null) prefix(curNode.right);
	}
	private static void midfix(String cur) {
		Node curNode = map.get(cur);
		if(curNode.left!=null) midfix(curNode.left);
		System.out.printf("%s",cur);
		if(curNode.right!=null) midfix(curNode.right);
	}
	private static void postfix(String cur) {
		Node curNode = map.get(cur);
		if(curNode.left!=null) postfix(curNode.left);
		if(curNode.right!=null) postfix(curNode.right);
		System.out.printf("%s",cur);
	}
}
