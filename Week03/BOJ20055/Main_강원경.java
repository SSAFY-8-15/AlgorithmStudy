package DIY.problemSolve;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_20055_BOJ {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		boolean belts[] = new boolean[2 * n];
		int durability[] = new int[2 * n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2*n; i++)
			durability[i] = Integer.parseInt(st.nextToken());
		int curStart = 0, curEnd = n - 1;
		int destroied = 0;
		int loops = 0;
		while (++loops>0) {
			// step1
			curStart = (curStart == 0 ? 2 * n - 1 : curStart - 1);
			curEnd = (curEnd == 0 ? 2 * n - 1 : curEnd - 1);
			if (belts[curEnd])
				belts[curEnd] = false;
			// step2
			for (int i = 1; i < n; i++) {
				int curPos = curEnd - i;
				if (curPos < 0)
					curPos += 2 * n;
				if (belts[curPos]) {
					int nextPos = (curPos + 1) == 2 * n ? 0 : curPos + 1;
					if (durability[nextPos] > 0 && !belts[nextPos]) {
						belts[nextPos] = true;
						belts[curPos] = false;
						durability[nextPos]--;
						if (durability[nextPos] == 0)
							destroied++;
						if (nextPos == curEnd)
							belts[nextPos] = false;
					}
				}
			}
			// step3
			if (durability[curStart] > 0 && !belts[curStart]) {
				durability[curStart]--;
				belts[curStart] = true;
				if (durability[curStart] == 0)
					destroied++;
			}
			if(destroied>=k) break;
		}
		System.out.println(loops);
	}
}
