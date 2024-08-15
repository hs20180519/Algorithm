import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int M;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 자연수 N 중
        M = Integer.parseInt(st.nextToken()); // M개를 순서 없이 뽑음

        combination(0, 1, new int[M]);

    }
    // cnt : answers 에 들어간 수
    // startNumber: 시작 수 (1 ~ N)
    // answers[] : 조합을 저장할 배열
    private static void combination(int cnt, int startNumber, int []answers){
        if (cnt== M){
            for(int i=0; i<M; i++){
                System.out.printf("%d ", answers[i]);
            }
            System.out.println();
            return;
        }
        for(int i=startNumber; i<=N; i++){
            answers[cnt] = i;
            combination(cnt+1, i+1, answers);
        }
    }
}
