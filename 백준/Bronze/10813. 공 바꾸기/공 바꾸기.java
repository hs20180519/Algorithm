import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int[] basket = new int[N];
      for(int i=0; i<N; i++){
        basket[i] = i+1;
      }
      
      for(int i=0; i<M; i++){
        st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken())-1;
        int second = Integer.parseInt(st.nextToken())-1;
        
        // swap
        int temp = basket[first];
        basket[first] = basket[second];
        basket[second] = temp;
      }
      
      for(int i=0; i<N; i++){
        System.out.print(basket[i]+ " ");
      }
    }
}