
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
        private static ArrayList<Integer>[] arr;
        private static boolean[] visited;
        private static int count;

        public static void main(String[] args) throws IOException {
            // bfs로 1번 노드부터 시작하여 인접 노드들을 돌면서
            // count를 증가시키고 최종 count 출력
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int c = Integer.parseInt(br.readLine()); // 컴퓨터의 수
            int num = Integer.parseInt(br.readLine()); // 연결된 쌍의 수

            arr = new ArrayList[c+1];
            for (int i=1; i<c+1; i++) { // arr 초기화
                arr[i] = new ArrayList<>();
            }


            for(int i=0; i<num; i++) { // arr에 연결된 노드들 입력
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[a].add(b);
                arr[b].add(a);
            }

            for (int i=1; i<c+1; i++) { // 작은 순서대로 정렬 -> 굳이 ?
                Collections.sort(arr[i]);
            }
            count = 0; // 웜 바이러스에 걸린 노드의 개수
            visited = new boolean[c+1]; // 방문 기록 노드
            bfs(1); // dfs 탐색
            System.out.println(count-1); // 1번 노드 제외

        }
        private static void dfs(int v) {
            visited[v] = true; // 방문처리
            count++; // 방문한 노드 개수 추가
            for(int i : arr[v]) { // 인접 노드 방문
                if(!visited[i]) { // 방문하지 않았던 노드라면
                    dfs(i); // 재귀
                }
            }
        }

        private static void bfs(int v){
            Queue<Integer> queue = new LinkedList<>();
            queue.add(v);
            visited[v] = true;
            count ++;
            while(!queue.isEmpty()){
                int cv = queue.poll();
                for(int i : arr[cv]){
                    if(!visited[i]) {
                        visited[i] = true;
                        queue.add(i);
                        count++;
                    }
                }
            }
        }



    }
