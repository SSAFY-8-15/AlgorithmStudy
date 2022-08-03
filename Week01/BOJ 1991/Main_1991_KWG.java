package tuesday;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_1991_KWG {
	static char[][] tree;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] idx = new int[n];
		tree = new char[n][2];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			char mid = st.nextToken().charAt(0);
			tree[mid - 'A'][0] = st.nextToken().charAt(0);
			tree[mid - 'A'][1] = st.nextToken().charAt(0);
		}
		inorder('A');
		bw.flush();
		preorder('A');
		bw.flush();
		postorder('A');
		bw.flush();
	}

	static void inorder(char c) throws Exception {
		bw.append(c);
		int idx = c - 'A';
		if (tree[idx][0] != '.')
			preorder(tree[idx][0]);
		if (tree[idx][1] != '.')
			preorder(tree[idx][1]);
	}

	static void preorder(char c) throws Exception {
		int idx = c - 'A';
		if (tree[idx][0] != '.')
			preorder(tree[idx][0]);
		bw.append(c);
		if (tree[idx][1] != '.')
			preorder(tree[idx][1]);
	}

	static void postorder(char c) throws Exception {
		int idx = c - 'A';
		if (tree[idx][0] != '.')
			preorder(tree[idx][0]);
		if (tree[idx][1] != '.')
			preorder(tree[idx][1]);
		bw.append(c);
	}
}
