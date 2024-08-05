import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[]args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 트럭 수  	10
		int w = Integer.parseInt(st.nextToken()); // 다리 길이 	100
		int l = Integer.parseInt(st.nextToken()); // 최대 하중	100
		int currentWeight = 0; 	// 현재 무게
		int time = 0; 			// 현재 시간
		
		Queue<Integer> waitingTrucks = new LinkedList<>(); // 대기 중인 트럭
		Queue<Integer> bridgeTrucks = new LinkedList<>(); 	// 다리 위의 트럭
		
		String[] c = br.readLine().split(" ");
		//System.out.println(Arrays.toString(c));
		
		for(String a : c) {
			waitingTrucks.add(Integer.parseInt(a));  // 대기 중인 트럭에 삽입
		}		
		
		for (int i=0; i< w; i++) { // 다리 위의 트럭을 0으로 초기화
			bridgeTrucks.add(0);
		}
		
		// 다리 위의 트럭이 없을 때까지
		while(!bridgeTrucks.isEmpty()) {
			time ++;
			currentWeight -= bridgeTrucks.poll(); // 트럭이 건너감
			
			if (!waitingTrucks.isEmpty()) {
				if (currentWeight + waitingTrucks.peek() <= l) {
					int p = waitingTrucks.poll(); 	// 첫번째 요소를 꺼내
					currentWeight += p; 			// 현재 무게에 더하고,
					bridgeTrucks.add(p); 			// 다리를 건너는 트럭에 추가
				}else {
					bridgeTrucks.add(0); // 트럭이 없으면 0을 추가하여 시간이 지나도록 함
				}
				
		
			}
		}
		System.out.println(time);	
	}
}
