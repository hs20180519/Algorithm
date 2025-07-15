import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int[] basket = new int[N+1];
      
      for(int i=0; i<M; i++){
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int ballNum = Integer.parseInt(st.nextToken());
        
        for(int j=start; j<=end; j++){
          basket[j] = ballNum;
        }
      }
      
      for(int i=1; i<=N; i++){
        System.out.print(basket[i]+ " ");
      }
    }
}