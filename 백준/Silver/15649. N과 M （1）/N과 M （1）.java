import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;
    private static int[] answers;
    private static boolean[] isSelected;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N개의 자연수 중에
        M = Integer.parseInt(st.nextToken()); // M개의 자연수 뽑음

        answers = new int[M]; // M의 크기를 갖는 정답 리스트
        isSelected = new boolean[N+1]; // N+1의 크기를 갖는 방문 여부 리스트
        seq(0);
    }
    private static void seq(int cnt){
        if (cnt == M){
            for(int i=0; i<M; i++){
                System.out.print(answers[i]+" ");
            }
            System.out.println("");
        }
        else{
            for(int i=1; i<=N; i++){
                if(isSelected[i]) continue; // 방문했으면 계속
                answers[cnt] = i;
                isSelected[i] = true;
                seq(cnt+1);
                isSelected[i] = false;

            }
        }
    }
}
