import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, X;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/d1010/input_SWEA_4014.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스
        
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 지도의 한 변
            X = Integer.parseInt(st.nextToken()); // 경사로의 길이
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;
            for (int i = 0; i < N; i++) {
                if (canBuildRunway(map[i])) count++; // 가로 체크
                int[] col = new int[N];
                for (int j = 0; j < N; j++) {
                    col[j] = map[j][i];
                }
                if (canBuildRunway(col)) count++; // 세로 체크
            }
            System.out.println("#" + tc + " " + count);
        }
    }

    static boolean canBuildRunway(int[] line) {
        boolean[] installed = new boolean[N];
        
        for (int i = 0; i < N - 1; i++) {
            int heightDiff = line[i] - line[i + 1];
            if (heightDiff == 0) continue; // 같은 높이
            if (heightDiff == 1) { // 내려가는 경우
                for (int j = 1; j <= X; j++) {
                    if (i + j >= N || line[i + j] != line[i + 1] || installed[i + j]) {
                        return false; // 설치 불가
                    }
                    installed[i + j] = true; // 설치
                }
            } else if (heightDiff == -1) { // 올라가는 경우
                for (int j = 0; j < X; j++) {
                    if (i - j < 0 || line[i] != line[i - j] || installed[i - j]) {
                        return false; // 설치 불가
                    }
                    installed[i - j] = true; // 설치
                }
            } else {
                return false; // 높이 차이가 1이 아닐 경우
            }
        }
        return true;
    }
}
