//package d0816;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/d0816/input_Main_11723.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int M = Integer.parseInt(br.readLine());
		int bitset = 0;

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();

			int num = 0;

			if (st.hasMoreTokens()) {
				num = Integer.parseInt(st.nextToken());
				num--;
			}
			switch (command) {
			case "add": // S에 x를 추가
				bitset |= (1 << num);
				break;
			case "remove": // S에서 x를 제거
				bitset &= ~(1 << num);
				break;

			case "check": // S에 x가 있으면 1, 없으면 0 출력
				sb.append((bitset & (1 << num)) == (1<<num) ? 1 : 0).append('\n');
				break;
				
			case "toggle": // S에 x가 있으면 x를 제거하고, 없으면 x를 추가
				bitset ^= (1 << num);
				break;

			case "all": // 1, 2, .. 20으로 바꾼다
				bitset = (1 << 20) - 1;
				break;

			case "empty":
				bitset = 0;
				break;
			}
		}

		System.out.println(sb);
	}
}
