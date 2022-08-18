package week2.boj17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_KSG {
	
	static final int BASE_COUNT = 4;
	
	static final int MAX_OUT_COUNT = 3;
	
	static final int HITTER_COUNT = 9;
	
	static final int[] base = new int[BASE_COUNT]; 
	
	static int maxScore = 0;
	
	private static void perm(int[][] allRecords, int[] result, int[] end, int visited, int cnt, int[] hitters) {
		if(cnt == hitters.length) {
			int tmp = hitters[0];
			hitters[0] = hitters[3];
			hitters[3] = tmp;
			Arrays.fill(result, 0);
			maxScore = Math.max(maxScore,allInningSimulate(allRecords, hitters, result, end));
			tmp = hitters[0];
			hitters[0] = hitters[3];
			hitters[3] = tmp;
			
		}
		for(int i=0; i<hitters.length; i++) {
			if(((1<<i) & visited) != 0){
				continue;
			}
			visited |= (1<<i);
			hitters[cnt] = i;
			perm(allRecords,result,end,visited,cnt+1,hitters);
			visited &= ~(1<<i);
		}
		
		
	}
	
	private static int allInningSimulate(int[][] allRecords, int[] hitOrder, int[] result, int[] end) {
		int maxScore = 0;
		int hitter = 0;
		int score = 0;
		for(int j=0; j<allRecords.length; j++) {
			// 새 이닝 시작
			inningSimulate(allRecords[j], hitOrder, result, end, hitter, j);
			score += result[j];
			hitter = end[j];
		}
		maxScore = Math.max(maxScore, score);
		return maxScore;
	}
	
	// result와 end를 갱신한다.
	// result[j] = i번 타자로 j번째 이닝을 시작할때의 최대점수
	// end[j] i번 타자로 j번째 이닝을 시작할때, 이닝 종료후 다음 이닝 시작 선수
	private static void inningSimulate(int[] records, int[] hitOrder, int[] result, int[] end, int i, int j) {
		Arrays.fill(base, 0);
		int outCount = 0;
		int score = 0;
		int currentHitter = i;
		while(outCount < MAX_OUT_COUNT) {
			int hitterLabel = hitOrder[currentHitter];
			int hit = records[hitterLabel];
			switch(hit) {
				case 0:
					outCount++;
					break;
				default:
					base[0] = 1;
					score += updateBase(hit, base);
					break;
			}
			currentHitter = (currentHitter+1) % HITTER_COUNT;
		}
		result[j] = score;
		end[j] = currentHitter;
	}
	
	private static int updateBase(int hit, int[] base) {
		int score = 0;
		int i;
		final int limit = BASE_COUNT-hit;
		for(i=limit; i<BASE_COUNT; i++) {
			score += base[i];
			base[i] = 0;
		}
		for(i=limit-1; i>=0; i--) {
			base[i+hit] = base[i];
			base[i] = 0;
		}
		return score;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] allRecords = new int[n][HITTER_COUNT];
		int[] result = new int[n];
		int[] end = new int[n];
		int[] hitters = new int[HITTER_COUNT];
		
		hitters[0] = 0;
		
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int[] records = allRecords[i];
			for(int j=0; j<HITTER_COUNT; j++) {
				records[j] = Integer.parseInt(st.nextToken());
			}
		}
		
		perm(allRecords, result, end, 1, 1, hitters);
		System.out.println(maxScore);
		br.close();

	}

}
