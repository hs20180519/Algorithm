
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int L;
    static int[][] arr;
    static int maxScore;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            arr = new int[N][2];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken()); // 점수
                arr[i][1] = Integer.parseInt(st.nextToken()); // 칼로리
            }

            maxScore = 0;

            for (int i = 1; i <= N; i++) { // 1 -> 하나를 뽑는 조합, 2 -> 둘을 뽑는 조합 ..
                combination(i);
            }
            System.out.println("#" + t + " " + maxScore);
        }
    }
    
    // swap
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // nextPermutation
    private static boolean nextPermutation(int[] p) {

        int i = N - 1; // 꼭대기
        while (i > 0 && p[i - 1] >= p[i]) i--;

        if (i == 0) return false;

        int j = N - 1; // 꼭대기 앞의 수와 바꿀 수
        while (p[i - 1] >= p[j]) j--;

        swap(p, i - 1, j);

        int k = N - 1;
        while (i < k) { // 꼭대기부터 끝까지 오름차순 정렬
            swap(p, i++, k--);
        }

        return true;
    }

    // combination
    private static void combination(int num) {
        int[] p = new int[N];
        // 뒤에서부터 num개를 1으로 변경
        // N이 5이고 p가 3이면, [0, 0, 1, 1, 1]
        for (int i = N - 1; i >= N - num; i--) { // 뒤에서부터 num개를 1으로 변경
            p[i] = 1;
        }
        Arrays.fill(p, 0, N - num, 0); // 0부터 N-num개를 0으로 변경

        do {
            int score = 0;
            int cal = 0;
            for (int i = 0; i < N; i++) {
                if (p[i] == 1) { // 뽑았으면
                    cal += arr[i][1];
                    if (cal > L) break;
                    score += arr[i][0];
                }
            }
            if (cal <= L) {
                maxScore = Math.max(score, maxScore);
            }
        } while (nextPermutation(p));
    }
}
