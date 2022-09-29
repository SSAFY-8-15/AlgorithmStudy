package DIY.problemSolve;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14891_BOJ {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] wheel = new char[4][8];
		int head[] = new int[4];
		for (int i = 0; i < 4; i++)
			wheel[i] = br.readLine().toCharArray();
		int n = Integer.parseInt(br.readLine());
		for (int t = 0; t < n; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int origin = Integer.parseInt(st.nextToken()) - 1;
			int forward = Integer.parseInt(st.nextToken()) * -1;
			int originleft=wheel[origin][(head[origin]+2)%8];
			int originright=wheel[origin][(head[origin]+6)%8];
			move(head, origin, forward);
			int leftward = forward;
			for (int i = origin - 1; i >= 0; i--) {
				int left = (head[i] + 2) % 8;
				int right = (head[i + 1] + 6) % 8;
				if (wheel[i][left] == originright)
					break;
				leftward *= -1;
				originright=wheel[i][(head[i]+6)%8];
				move(head, i, leftward);
			}
			int rightward = forward;
			for (int i = origin + 1; i < 4; i++) {
				int left = (head[i - 1] + 2) % 8;
				int right = (head[i] + 6) % 8;
				if (originleft == wheel[i][right])
					break;
				rightward *= -1;
				originleft=wheel[i][(head[i]+2)%8];
				move(head, i, rightward);
			}
		}
		int score = 0;
		for (int i = 0; i < 4; i++)
			score |= (wheel[i][head[i]] - '0') << i;
		System.out.println(score);
	}

	static void move(int[] head, int origin, int forward) {
		head[origin] += forward;
		if (head[origin] == 8)
			head[origin] = 0;
		else if (head[origin] == -1)
			head[origin] = 7;
	}

}
