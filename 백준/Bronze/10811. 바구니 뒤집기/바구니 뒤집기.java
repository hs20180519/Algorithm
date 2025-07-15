import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      
      int[] basket = new int[N+1];
      for(int i=0; i<=N; i++){
        basket[i] = i;
      }
      
      for(int i=0; i<M; i++){
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        
        int k = 0;
        for(int j=start; j<=(start+end)/2; j++){
          
          int temp = basket[j];
          basket[j] = basket[end-k];
          basket[end-k++] = temp;
        }
      }
      
      for(int i=1; i<=N;i++){
        System.out.print(basket[i]+ " ");
      }
      
    }
      
}