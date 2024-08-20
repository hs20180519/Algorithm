
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int result = bfs(num);

        System.out.println(result);
    }

    static int bfs(int num) {
        boolean[] visited = new boolean[5001];
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        visited[num] = true;
        queue.add(new int[]{num, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currentNum = curr[0];
            int currentCount = curr[1];

            if (currentNum == 0) {
                return currentCount;
            }

            int subtractFive = currentNum - 5;
            int subtractThree = currentNum - 3;

            if (subtractFive >= 0 && !visited[subtractFive]) {
                visited[subtractFive] = true;
                queue.add(new int[]{subtractFive, currentCount + 1});
            }

            if (subtractThree >= 0 && !visited[subtractThree]) {
                visited[subtractThree] = true;
                queue.add(new int[]{subtractThree, currentCount + 1});
            }
        }

        return -1; // 목표를 달성하지 못한 경우
    }
}
