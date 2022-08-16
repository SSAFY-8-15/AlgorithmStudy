package week02.E_Friday;

import java.io.*;
import java.util.*;

//78420KB, 604ms
public class Main_KWG {
	static int[] battingOrder = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	static int[][] hits;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int innings = Integer.parseInt(br.readLine());

		hits = new int[innings][9];

		for (int i = 0; i < innings; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++)
				hits[i][j] = Integer.parseInt(st.nextToken());
		}
		long maxScore = Long.MIN_VALUE;
		do {
			long curScore = getScore(innings);
			if (maxScore < curScore)
				maxScore = curScore;
		} while (np(8));
		System.out.println(maxScore);
	}

	private static long getScore(int innings) {
		long score = 0;
		int idx = 0;
		for (int i = 0; i < innings; i++) {
			int outs = 0;
			int[] onbase = new int[4];
			while (outs < 3) {
				int HitInfo;
				if (idx < 3)
					HitInfo = hits[i][battingOrder[idx + 1]];
				else if (idx == 3)
					HitInfo = hits[i][0];
				else
					HitInfo = hits[i][battingOrder[idx]];
				idx++;
				if (idx == 9)
					idx = 0;
				if (HitInfo == 0) {
					outs++;
				} else {
					score += movebase(onbase, HitInfo);

				}
			}
		}
		return score;
	}

	private static int movebase(int[] onbase, int go) {
		int earned = 0;
		if (go == 4)
			earned = 1;
		for (int i = 3; i > 0; i--) {
			if (i + go >= 4 && onbase[i] == 1)
				earned++;
			if (i > go)
				onbase[i] = onbase[i - go];
			else if (i == go)
				onbase[i] = 1;
			else
				onbase[i] = 0;
		}
		return earned;
	}

	private static boolean np(int size) {
		int i = size;
		while (i > 1 && battingOrder[i - 1] > battingOrder[i])
			i--;
		if (i == 1)
			return false;
		int j = size;
		while (battingOrder[i - 1] > battingOrder[j])
			j--;
		swap(i - 1, j);
		int k = size;
		while (i < k) {
			swap(i, k);
			i++;
			k--;
		}
		return true;
	}

	private static void swap(int i, int k) {
		int tmp = battingOrder[i];
		battingOrder[i] = battingOrder[k];
		battingOrder[k] = tmp;
	}
}
