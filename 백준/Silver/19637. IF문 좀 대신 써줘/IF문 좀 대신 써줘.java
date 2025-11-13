import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
     
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(st.nextToken()); // 칭호의 개수
        int M = Integer.parseInt(st.nextToken()); // 캐릭터들의 개수
        String[] chingho = new String[N];
        int[] highValue = new int[N];
        
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            chingho[n] = st.nextToken();
            highValue[n] = Integer.parseInt(st.nextToken());
        }
        
        for(int m=0; m<M; m++){
            int junToLuck = Integer.parseInt(br.readLine());
            
            int left = 0, right = N - 1;
            int answerIdx = 0;
            
            while (left <= right) {
                int mid = (left + right) / 2;
                if (junToLuck <= highValue[mid]) {
                    answerIdx = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            sb.append(chingho[answerIdx]).append('\n');
        }
        System.out.println(sb.toString());
    }
       
}
