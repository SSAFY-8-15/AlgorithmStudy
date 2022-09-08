package week4.sw5658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_KSG_SW5658 {
	
	public static long charToInt(char c) {
		if(c <= '9') {
			return c-'0';
		}else {
			return c-'A' + 10;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			char[] chars = br.readLine().toCharArray();
			int sizeOfSegment = n/4;
			
			
			TreeSet<Long> set = new TreeSet<Long>(Collections.reverseOrder());
			
			long window = 0;
			long mod = 1;
			int rear = sizeOfSegment;
			
			// 윈도우 초기 로딩
			for(int i=0; i<sizeOfSegment; i++) {
				window *= 16;
				mod *= 16;
				window += charToInt(chars[i]);
			}
			
			//슬라이딩 윈도우
			for(int i=0; i<n; i++) {
				// 삽입
				set.add(window);
				
				//read 들어오기
				window *= 16;
				window += charToInt(chars[rear++]);
				
				//head 나가기
				window %= mod;
				
				//rear 조정
				if(rear == n) {
					rear = 0;
				}
			}
			
			String answer = null;
			for(Long answerCandidate : set) {
				if(--k == 0) {
					answer = Long.toString(answerCandidate);
					break;
				}
			}
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
		
		
		
		br.close();

	}

}
