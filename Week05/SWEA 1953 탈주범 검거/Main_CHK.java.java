package SWEA1953;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };// 상하좌우
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Point> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs();
			// true개수 count
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j])
						count++;
				}
			}
			System.out.println("#" + t + " " + count);
		}

	}

	private static void bfs() {
		queue = new LinkedList<>();
		queue.add(new Point(R, C, 1));
		visited[R][C] = true;

		while (!queue.isEmpty()) {
			Point np = queue.poll();

			if (np.t >= L) {// L보다 시간이 같거나 커지는 경우 그냥 빼서 버림
				continue;
			}

			switch (map[np.x][np.y]) {
			case 1:// 4방
				for (int d = 0; d < 4; d++) {// 상하좌우
					int nx = np.x + dx[d];
					int ny = np.y + dy[d];
					if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
						if (d == 0 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}
						if (d == 1 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}
						if (d == 2 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}
						if (d == 3 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}
					}

				}
				break;
			case 2:// 상하
				for (int d = 0; d < 2; d++) {
					int nx = np.x + dx[d];
					int ny = np.y + dy[d];
					if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
						if (d == 0 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}
						if (d == 1 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}
					}
				}
				break;
			case 3:// 좌우
				for (int d = 2; d < 4; d++) {
					int nx = np.x + dx[d];
					int ny = np.y + dy[d];
					if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
						if (d == 2 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}
						if (d == 3 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}
					}

				}
				break;
			case 4:// 상우
				for (int d = 0; d < 4; d = d + 3) {// 0,3
					int nx = np.x + dx[d];
					int ny = np.y + dy[d];
					if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
						if (d == 0 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}
						if (d == 3 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}
					}
				}
				break;
			case 5:// 하우
				for (int d = 1; d < 4; d = d + 2) {// 1,3
					int nx = np.x + dx[d];
					int ny = np.y + dy[d];
					if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
						if (d == 1 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}

						if (d == 3 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}
					}

				}
				break;
			case 6:// 하좌
				for (int d = 1; d < 3; d++) {// 1 2
					int nx = np.x + dx[d];
					int ny = np.y + dy[d];
					if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
						if (d == 1 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}
						if (d == 2 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}
					}
				}
				break;
			case 7:// 상좌
				for (int d = 0; d < 3; d = d + 2) {// 0 2
					int nx = np.x + dx[d];
					int ny = np.y + dy[d];
					if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
						if (d == 0 && (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}

						if (d == 2 && (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5)) {
							queue.add(new Point(nx, ny, np.t + 1));
							visited[nx][ny] = true;
						}

					}

				}
				break;
			}

		}

	}

}

class Point {
	int x;
	int y;
	int t;

	public Point(int x, int y, int t) {
		super();
		this.x = x;
		this.y = y;
		this.t = t;
	}

}