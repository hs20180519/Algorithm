// package d0816;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/d0816/input_SWEA_1225"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			int t = Integer.parseInt(br.readLine());
			ArrayDeque<Integer> q = new ArrayDeque<>();

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				q.add(Integer.parseInt(st.nextToken()));
			}

			int minus = 1;
			while (true) {
				if(minus > 5) {
					minus = 1;
				}
				int p = q.pollFirst();
				if (p - minus <= 0) {
					q.addLast(0);
					break;
				} else {
					q.addLast(p-minus);
					minus++;
				}
			}
			
			sb.append("#").append(tc).append(" ");
			while(!q.isEmpty()) {
				sb.append(q.pollFirst()).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
