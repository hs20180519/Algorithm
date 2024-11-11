import java.io.*;
import java.util.*;

public class Main {
	// 그리디 & PQ
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 강의의 우선순위 큐
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1[0] == o2[0]) {
				return o1[1] - o2[1];
			} else
				return o1[0] - o2[0];

		});
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			// T <= S인 경우 같이 들을 수 있는 수업
			pq.add(new int[] { S, T }); // 끝나는 시간
		}

		// 강의의 종료시간을 담을 우선순위 큐
		PriorityQueue<Integer> room = new PriorityQueue<>();
		room.add(0);
		
		while (!pq.isEmpty()) {
			int[] curr = pq.poll(); // 현재 강의
			if(room.peek() <= curr[0]) { 
				// room.peek() : 가장 빨리 끝나는 강의의 종료 시간
				// 현재 강의의 시간보다 작거나 같은 경우 재사용 가능
				room.poll();
			}
			room.add(curr[1]);
		}
		System.out.println(room.size());
	}

}
