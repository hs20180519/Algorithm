import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] fishes;
    static boolean[] visited;
    static int d, k;
    static int N;

    public static void main(String[] args) throws IOException {
        // 가능한 다양한 종류의 초밥
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 접시의 수
        d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        fishes = new int[N];
        for (int i = 0; i < N; i++) {
            fishes[i] = Integer.parseInt(br.readLine());
        }

        int maxNum = 0;
        for (int start = 0; start < N; start++) {

            int end = start + k <= N ? start + k - 1 : start + k - N - 1;
            // System.out.println(start + "," + end);
            maxNum = Math.max(countFishes(start, end, c), maxNum);

        }

        // start가 4일때 4,5,6,7 => end는 7 (if start + k(8) <= N) end = start+k-1,(7)
        // start가 5일대 5,6,7,0 => end는 0 (if start + k(9) > N) end = start+k - N -1 (0)


        // 5,6,7,0 / 6,7,0,1 / 7, 0, 1, 6 의 경우들도 세야 한다 !!!
        // end가 start보다 작을 때 케이스 추가하기

        System.out.println(maxNum);

    }

    static int countFishes(int start, int end, int coupon) {
        visited = new boolean[d+1];
        int count = 0;
        if (start < end) {
            for (int i = start; i <= end; i++) {
                if (!visited[fishes[i]]) {
                    count++;
                    visited[fishes[i]] = true;
                }
            }
        } else {
            for (int i = start; i < N; i++) {
                if (!visited[fishes[i]]) {
                    count++;
                    visited[fishes[i]] = true;
                }
            }

            for (int j = 0; j <= end; j++) {
                if (!visited[fishes[j]]) {
                    count++;
                    visited[fishes[j]] = true;
                }
            }
        }
        if(!visited[coupon]) count++;
        return count;
    }
}

