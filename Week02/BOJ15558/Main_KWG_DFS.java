package DIY.problemSolve;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 42024KB, 180ms
public class Main_KWG_DFS {
	static int n, k;
	static boolean[][] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		String[] map = new String[2];
		check = new boolean[2][n];
		map[0] = br.readLine();
		map[1] = br.readLine();
		check[0][0] = true;
		System.out.println(dfs(map, 0, 0, 0) ? 1 : 0);
	}

	private static boolean dfs(String[] map, int r, int c, int time) {
		if (c + 1 == n || c + k >= n)
			return true;

		boolean result = false;
		if (map[r].charAt(c + 1) == '1' && !check[r][c + 1]) {
			check[r][c + 1] = true;
			result = dfs(map, r, c + 1, time + 1);
			check[r][c + 1] = false;
		}
		if (result)
			return true;
		if (c - 1 >= time + 1 && map[r].charAt(c - 1) == '1' && !check[r][c - 1]) {
			check[r][c - 1] = true;
			result = dfs(map, r, c - 1, time + 1);
			check[r][c - 1] = false;
		}
		if (result)
			return true;
		if (map[1 - r].charAt(c + k) == '1' && !check[1 - r][c + k]) {
			check[1 - r][c + k] = true;
			result = dfs(map, 1 - r, c + k, time + 1);
			check[1 - r][c + k] = false;
		}
		return result;
	}
}
