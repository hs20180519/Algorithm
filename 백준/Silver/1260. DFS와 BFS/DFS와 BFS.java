import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static ArrayList<Integer>[] arr;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 정점의 개수
        int m = Integer.parseInt(st.nextToken()); // 간선의 개수
        int v = Integer.parseInt(st.nextToken()); // 시작 노드

        arr = new ArrayList[n + 1]; // 노드의 개수+1 만큼 크기 설정
        for (int i = 1; i < n + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) { // 노드별 인접 노드 설정
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            arr[b].add(a);
        }

        for (int i = 1; i < n + 1; i++) { // 각 배열 정렬
            Collections.sort(arr[i]);
        }

        visited = new boolean[n + 1]; // 방문 처리를 위한 배열
        dfs(v);
        System.out.println();

        visited = new boolean[n+1];
        bfs(v);

    }

    private static void dfs(int v) {
        visited[v] = true; // 방문
        System.out.print(v + " ");
        for (int a : arr[v]) { // 인접 노드들 중
            if (!visited[a]) { // 방문 안했으면
                dfs(a);
            }
        }
    }

    private static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>(); // 큐 선언
        visited[v] = true; // 방문
        queue.add(v);

        while (!queue.isEmpty()) { // 비어있지 않으면
            int n = queue.poll(); // 빼내고 (방문)
            System.out.print(n + " ");

            for (int k : arr[n]) { // 순서대로
                if (!visited[k]) { // 방문 안했으면
                    visited[k] = true; // 방문
                    queue.add(k); // 큐에 추가
                }
            }
        }
    }
}