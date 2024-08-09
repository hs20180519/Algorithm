import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    // bfs를 통해 해결
    // 큐에 수 a가 들어올 때, a*10 +1와 a*2를 각각 큐에 넣는다
    // 단, count는 계산 횟수이므로 큐에 함께 넣는다.

    // a가 2이고 b가 162라면,
    // queue : [ (2, 1) ] -> queue.poll() -> *10+1한 수와 *2한 수 add
    // queue : [ (21, 2), (4, 2) ] 두번째 수는 count임에 유의
    // queue : [ (4, 2) , (42, 3)] 21을 두배한 수는 queue에 add되었으나,
    // 10을 곱하고 1을 더한 수는 211 이므로 b보다 작으니 queue에 add X
    // ...

    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());

            System.out.println(bfs(a, b));
        }

        public static long bfs(long a, long b) {
            Queue<long[]> queue = new LinkedList<>();
            queue.add(new long[]{a, 1});
            while (!queue.isEmpty()) {
                long[] current = queue.poll();
                long currentValue = current[0];
                long count = current[1];

                if (currentValue == b) { // 목표값에 도달하면
                    return count;
                }

                long num_1 = currentValue * 2; // 2를 곱한 수
                long num_2 = currentValue * 10 + 1; // 10을 곱하고 1을 더한 수

                // 목표값을 초과하지 않으면 큐에 삽입
                if (num_1 <= b) {
                    queue.add(new long[]{num_1, count + 1});
                }
                if (num_2 <= b) {
                    queue.add(new long[]{num_2, count + 1});
                }
            }
            return -1;
        }
    }

