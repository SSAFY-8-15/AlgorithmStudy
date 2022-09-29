package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_11497 {

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int T = 0; T < TC; T++) {
			
			int ans = 0;
			int size = Integer.parseInt(br.readLine());
			ArrayList<Integer> h = new ArrayList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < size; i++) {
				h.add(Integer.parseInt(st.nextToken()));
			}
			
			Collections.sort(h);
			for (int i = 2; i < size; i++) {
				ans = Math.max(ans, Math.abs(h.get(i) - h.get(i-2)));
			}
			System.out.println(ans);
		}
	}

}
