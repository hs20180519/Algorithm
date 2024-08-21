import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 * [문제 풀이를 위한 아이디어]
	 * 
	 * 0 -> 1
	 * 2 -> 3
	 * Z는 1사분면, 2사분면, 3사분면, 4사분면순으로 이동한다.
	 * 
	 * 4가 주어졌다면 half를 2로 갱신하고,
	 * r좌표가 2보다 작고, c좌표가 2보다 작으면 1사분면
	 * r좌표가 2보다 작고, c좌표가 2보다 크거나 같으면 2사분면
	 * r좌표가 2보다 크거나 같고, c좌표가 2보다 작으면 3사분면
	 * r좌표가 2보다 크거나 같고, c좌표가 2보다 크거나 같으면 4사분면
	 * 
	 * 범위가 1사분면이 아니라면, 각 사분면의 크기 => area크기(half*half)를 구해 누적한다.
	 * half가 1이 아니라면 다시 4개의 분면으로 나누어 생각한다.
	 *
	 * [난이도]
	 * r, c 좌표를 기준으로 1, 2, 3, 4사분면을 나눠야 한다는 
	 * 아이디어를 생각해내기가 어려운 것 같다.
	 * 특히 재귀를 써서 count++를 하면 연산이 너무 많아지므로 
	 * 각 사분면의 areaSize로 더해서 풀어나가야한다.
	 * 
	 */

	static int count;
	static int GoalR;
	static int GoalC;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken()); // 행
		int c = Integer.parseInt(st.nextToken()); // 열
		
		int Nsquare = (int)Math.pow(2, N);
	
		count = 0;
		findZ(r, c, Nsquare);
		System.out.println(count);
	}

	public static void findZ(int r, int c, int size) {
		if (size == 1) {
			return;
		}
		
		int half = size / 2;
		int areaSize = half * half;
		if(r < half && c < half) { // 1사분면
			findZ(r, c, half);
		}else if(r < half && c >= half) { // 2사분면
			count += areaSize;
			findZ(r, c-half, half);
		}
		else if(r >= half && c <half) { // 3사분면
			count += 2*areaSize;
			findZ(r-half, c, half);
		}
		else if(r >= half && c>= half){// 4사분면
			count += 3*areaSize;
			findZ(r-half, c-half, half);
		}
	
		
	}

}
