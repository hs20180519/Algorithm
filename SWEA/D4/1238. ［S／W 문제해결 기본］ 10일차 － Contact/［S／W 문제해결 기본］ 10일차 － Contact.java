import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * [문제 풀이를 위한 아이디어]
 * 
 * from - to 를 받아서 arr[from].add(to)
 * 이때 중복된 것 받지 않기 위해 arr를 HashSet[]으로 선언
 * 
 * bfs 돌아 최대 깊이 기록 후, 최대 깊이 중 가장 큰 수 갱신
 * 
 * 
 * [난이도]
 *  visited를 쓸거면 굳이 HashSet으로 안 써도 됐을 듯 하다.
 *  HashSet을 쓰니까 내용을 꺼내오기 조금 번거롭다.
 * 
 */

public class Solution {
	static HashSet[] people; // 사람들 저장할 set
	static boolean[] visited; // 방문 여부 저장
	static ArrayList<int[]> ans;
	static int maxCount;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/d0828/input_SWEA_1238.txt")); // TODO : X
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int tc = 1; tc<=10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			people = new HashSet[101]; // 초기화
			for(int i=0; i<101; i++) {
				people[i] = new HashSet<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				people[from].add(to); // from - to
			}
	
			visited = new boolean[101]; // 방문 배열 초기화
		
			ans = new ArrayList<int[]>();
			maxCount = 0;
			int contact = Integer.MIN_VALUE;
			bfs(start);
			for(int [] a: ans) {
				if (a[1] == maxCount) {
					contact = Math.max(contact, a[0]); // 최대 값
				}
			}
			System.out.printf("#%d %d\n",tc,contact);
		}
	}
	
	// 최대 깊이 기록 후, 최대 깊이 중 가장 큰 수 갱신
	public static void bfs(int start) {
		Queue<int[]>q = new LinkedList<>();
		q.add(new int[] {start, 0});
		visited[start] = true;
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int next = curr[0];
			int count = curr[1];
			
			maxCount = Math.max(maxCount, count);
			
			for(Object i : people[next]) {
				int a = Integer.parseInt(i.toString());
				if(!visited[a]) {
					q.add(new int[] {a, count+1});
					ans.add(new int[] {a, count+1});
					visited[a] = true;
				}
			}
			
		}
		
	}
}
