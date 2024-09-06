import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static ArrayList<Integer>[] graph;
	static int N;
	static int[] node, peoples;
	static int minPeople;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 구역의 개수
		
		peoples = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		node = new int[N+1];
		for(int i=1;i<N+1; i++) {
			peoples[i] = Integer.parseInt(st.nextToken());
			node[i] = i;
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
		
		visited = new boolean[N+1];
		minPeople = Integer.MAX_VALUE;
	
		for(int k=1; k<=N/2; k++) {
			combination(0, 1, new int[k],k);
		}
		System.out.println(minPeople == Integer.MAX_VALUE ? -1 : minPeople);
	}
	
	// N개중 k개를 뽑는 조합
	public static void combination(int cnt, int startIndex, int[] answers, int k) {
		if(cnt ==k) { // 다 뽑았으면
			// 여기서 answers에서  dfs돌고, answers가 아닌 나머지 에서 dfs 돌아서 visited 확인
			visited = new boolean[N+1];
			dfs(answers[0], answers);
				
			int[] otherAnswers = makeOtherAnswers(answers);
				
			dfs(otherAnswers[0], otherAnswers);
		
			int count = 0;
			for(int i=1; i<N+1; i++) {
				if(visited[i])count++;
			}	
			if(count==N) { // 가능한 방법일 때
				minPeople = Math.min(minPeople, calculatePeople(answers, otherAnswers));
			}		
			return;
		}
		for(int i=startIndex; i<N+1; i++) {
				answers[cnt] = node[i];
				combination(cnt+1, i+1, answers, k);
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
	
	public static int[] makeOtherAnswers(int[] answers) {
		int[] otherAnswers = new int[N-answers.length];
		int index =0;
		for(int i=1; i<N+1; i++) {
			if(!isInAnswer(answers, i)) { // 없으면
				otherAnswers[index] = i;
				index++;
			}
		}
		return otherAnswers;
	}
	
	// answers에 num이 있으면 true, 없으면 false
	public static boolean isInAnswer(int [] answers, int num) {
		for(int i=0; i<answers.length; i++) {
			if(answers[i] == num) {
				return true;
			}
		}
		return false;
	}
	
	// 두 선거구 인구 차이
	public static int calculatePeople(int[] a, int []b) {
		int aCount = 0;
		int bCount = 0;
		for(int i=0; i<a.length; i++) {
			aCount += peoples[a[i]];
		}
		for(int i=0; i<b.length; i++) {
			bCount += peoples[b[i]];
		}
		return Math.abs(aCount-bCount);
	}
	
}
