
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        // n개의 숫자 -> 수열 입력
        st = new StringTokenizer(br.readLine(), " ");
        
        // 공백을 기준으로 입력받는 숫자가 x보다 작으면 print
        for(int i=0; i<n; i++){
            int c = Integer.parseInt(st.nextToken());
            if (c < x){
                System.out.print(c+" ");
            }
        }
    }

}
