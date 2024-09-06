import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	/*
	 * 인구수를 차례대로 1번부터 N번까지 기록한다.
	 * peoples = [0, 2, 3, 4, 5, 6, 7];
	 * 
	 * 각 노드들에 인접한 노드 번호를 저장한다.
	 * 2번째 입력 4 1 3 6 5이 들어왔을 경우  graph[2]에 1, 3, 6, 5번(4개)를 추가한다.
	 * 
	 * 백준시를 두 선거구로 나누어 계산하기 위해 조합한다.
	 * 조합은 1개~ N/2개 만큼 뽑고, 뽑은 배열을 red, 뽑지 않은 배열을 blue로 한다.
	 * 각각 같은 배열안에 있고, 인접해있다면 dfs를 돌고 방문처리한다.
	 * 두 배열 모두 dfs를 돌았을 때 모든 노드들이 방문되었다면 가능한 경우이므로 인구수의 차이를 계산한다.
	 * 
	 * 처음 minPeople을 최댓값으로 설정한다.
	 * 모든 조합 계산 후에도 그대로 나온다면 가능하지 않은 경우이므로 -1을 출력한다.
	 */
	
	
	static boolean[] visited;
	static ArrayList<Integer>[] graph;
	static int N;
	static int[] peoples;
	static int minPeople;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 구역의 개수
		
		peoples = new int[N+1]; // 인구 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<N+1; i++) {
			peoples[i] = Integer.parseInt(st.nextToken());
		}
	
		graph = new ArrayList[N+1]; // 각 노드들에 인접한 노드 번호 저장
		for(int i=1; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken()); // 인접한 구역 수
			for(int j=0; j<number; j++) {
				graph[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		minPeople = Integer.MAX_VALUE; // 초기값 최대로 설정
	
		for(int k=1; k<=N/2; k++) { // 6C4나 6C2는 같은 경우이므로 N/2까지만 조합을 구함
			combination(0, 1, new int[k],k);
		}
		
		// minPeople이 MAX_VALUE면 가능하지 않은 방법만 있었으므로 -1을 출력
		System.out.println(minPeople == Integer.MAX_VALUE ? -1 : minPeople);
	}
	
	// N개중 k개를 뽑는 조합
	public static void combination(int cnt, int startIndex, int[] blue, int k) {
		if(cnt ==k) { // k개 만큼 다 뽑았으면
			visited = new boolean[N+1];
			dfs(blue[0], blue); // blue에서 같은 blue끼리 인접 노드들 방문
				
			int[] red = makeRed(blue); // blue가 아닌 배열 red 만듦
				
			dfs(red[0], red); // red에서 같은 red끼리 인접 노드들 방문
		
			int count = 0; // 방문 횟수
			for(int i=1; i<N+1; i++) {
				if(visited[i]) count++;
			}	
			if(count==N) { // 방문 횟수가 총 노드 개수와 같으면 모두 방문 => 가능한 방법
				minPeople = Math.min(minPeople, calculatePeople(blue, red));
			}		
			return;
		}
		
		for(int i=startIndex; i<N+1; i++) {
				blue[cnt] = i;
				combination(cnt+1, i+1, blue, k);
		}
	}
	
	
	public static void dfs(int index, int [] answers) {
		visited[index] = true; // 방문
		
		for(int next : graph[index]) {
			if(!visited[next]) {
				// 그 노드가 같은 배열 안에 있다면
				for(int i=0; i<answers.length; i++) {
					if(answers[i] == next) dfs(next, answers);
				}
			}
		}
	}
	
	// 노드들 중 blue가 아닌 노드들을 뽑아 red 배열로 만듦
	public static int[] makeRed(int[] blue) {
		int[] red = new int[N-blue.length];
		int index =0;
		for(int i=1; i<N+1; i++) {
			if(!isInAnswer(blue, i)) { // blue에 i가 없으면 red에 추가
				red[index] = i;
				index++;
			}
		}
		return red;
	}
	
	// answers에 num이 있으면 true, 없으면 false
	public static boolean isInAnswer(int [] blue, int num) {
		for(int i=0; i<blue.length; i++) {
			if(blue[i] == num) {
				return true;
			}
		}
		return false;
	}
	
	// 두 선거구 인구 차이 계산
	public static int calculatePeople(int[] blue, int [] red) {
		int blueCount = 0;
		int redCount = 0;
		for(int i=0; i<blue.length; i++) {
			blueCount += peoples[blue[i]];
		}
		for(int i=0; i<red.length; i++) {
			redCount += peoples[red[i]];
		}
		return Math.abs(blueCount-redCount);
	}
	
}
