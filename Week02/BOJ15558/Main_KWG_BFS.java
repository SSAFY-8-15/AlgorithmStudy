package DIY.problemSolve;

import java.io.*;
import java.util.*;

//28812KB, 264ms
public class Main_KWG_BFS {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		String[] map = new String[2];
		map[0] = br.readLine();
		map[1] = br.readLine();
		boolean[][] check = new boolean[2][n];
		Queue<int[]> q = new LinkedList<>();

		q.offer(new int[] { 0, 0, 0 });
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int time = cur[2];
			if (c + 1 >= n || c + k >= n) {
				System.out.println(1);
				return;
			}
			if (map[r].charAt(c + 1) == '1' && !check[r][c + 1]) {
				q.offer(new int[] { r, c + 1, time + 1 });
				check[r][c + 1] = true;
			}
			if (c - 1 >= time + 1 && map[r].charAt(c - 1) == '1' && !check[r][c - 1]) {
				q.offer(new int[] { r, c - 1, time + 1 });
				check[r][c - 1] = true;
			}
			if (map[1 - r].charAt(c + k) == '1' && !check[1 - r][c + k]) {
				q.offer(new int[] { 1 - r, c + k, time + 1 });
				check[1 - r][c + k] = true;
			}
		}
		System.out.println(0);
	}
}
