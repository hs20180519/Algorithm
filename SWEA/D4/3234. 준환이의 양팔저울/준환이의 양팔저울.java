import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    /*
    순열(순서가 다르면 다른 경우로 인정)

    permutation 인자에 배열을 넣을 필요 없고, "합"을 넣으면 된다.
    permutation(cnt+1, leftSum + weights[i], rightSum);
    permutation(cnt+1, leftSum, rightSUm + weights[i]);
    그리고 방문 배열을 원래대로 복구한다.

    오른쪽 합이 왼쪽 합보다 커지면 return
    cnt가 N개면 뽑을 수 있는 경우이므로 정답 카운트 증가한다.

     */
    static int answer;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/d0908/input_SWEA_3234.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine()); // 무게 추의 개수
            int [] weights = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }
            answer = 0;
            boolean[] visited = new boolean[N];

            permutation(0, 0, 0, N, weights, visited);
            System.out.println("#"+tc+" "+answer);
        }

    }
    public static void permutation(int cnt, int leftSum, int rightSum, int N, int[] weights, boolean[] visited) {
        // 왼쪽보다 오른쪽이 클 경우 탐색 종료
        if (rightSum > leftSum) {
            return;
        }
        // N개만큼 다 뽑았을 경우
        if (cnt == N) {
            answer++;
            return;
        }

        // 순열 생성
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;

                // 추를 왼쪽에 올림
                permutation(cnt + 1, leftSum + weights[i], rightSum, N, weights, visited);

                // 추를 오른쪽에 올림
                permutation(cnt + 1, leftSum, rightSum + weights[i], N, weights, visited);

                // 원래 상태 복구
                visited[i] = false;
            }
        }
    }
}