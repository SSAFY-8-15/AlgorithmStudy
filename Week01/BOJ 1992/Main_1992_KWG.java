import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1992_KWG {
	static int map[][];
	static int n;
	static StringBuilder s;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		s = new StringBuilder("");
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < n; j++)
				map[i][j] = line.charAt(j) - '0';
		}
		quad(0, 0, n);
		System.out.println(s.toString());
	}

	private static void quad(int x, int y, int size) {
		if (size == 1)
			s.append(map[x][y]);
		else {
			boolean hasdiff = false;
			int first = map[x][y];
			for (int i = 0; i < size; i++) {
				int[] tmp = Arrays.copyOfRange(map[i + x], y, y + size);
				if (Arrays.toString(tmp).contains((1 - first)+"")) {
					hasdiff = true;
					break;
				}
			}
			if (hasdiff) {
				s.append('(');
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						int newSize = size / 2;
						quad(x + (newSize * i), y + (newSize * j), newSize);
					}
				}
				s.append(')');
			} else
				s.append(first);
		}
	}
}
