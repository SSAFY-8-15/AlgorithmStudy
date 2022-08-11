package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1991_scw {

	static String ans = ""; 
	static Map<String, List<String>> tree = new HashMap<>();

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			List<String> list = new ArrayList<>();
			
			String P = st.nextToken();
			String L = st.nextToken();
			String R = st.nextToken();
			list.add(L);
			list.add(R);
			tree.put(P, list);
			
		}
		
		preOrder("A");
		ans += "\n";
		inOrder("A");
		ans += "\n";
		postOrder("A");
		
		System.out.println(ans);
		
	}

	private static void preOrder(String s) {
		if (s.equals(".")) {
			return;
		}
		
		ans += s;
		preOrder(tree.get(s).get(0));
		preOrder(tree.get(s).get(1));
		
	}

	private static void inOrder(String s) {
		if (s.equals(".")) {
			return;
		}
		
		inOrder(tree.get(s).get(0));
		ans += s;
		inOrder(tree.get(s).get(1));
		
	}

	private static void postOrder(String s) {
		if (s.equals(".")) {
			return;
		}
		
		postOrder(tree.get(s).get(0));
		postOrder(tree.get(s).get(1));
		ans += s;		
		
	}

}
