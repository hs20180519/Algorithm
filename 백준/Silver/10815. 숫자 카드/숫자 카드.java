
import java.io.*;
import java.util.*;

// 숫자 카드
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        HashSet<Integer> h = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n=0;n<N; n++){
            h.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int m=0;m<M; m++){
            int num = Integer.parseInt(st.nextToken());
            if(h.contains(num)){
                sb.append(1);
            }else{
                sb.append(0);
            }
            sb.append(" ");
        }

        System.out.println(sb);
    }
}
