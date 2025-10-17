import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int N = Integer.parseInt(st.nextToken());
      int score = Integer.parseInt(st.nextToken());
      int P = Integer.parseInt(st.nextToken());
      
      if(N==0){
        System.out.println(1);
        return;
      }
  
      st = new StringTokenizer(br.readLine());
      int[] ranking = new int[N];
      
      for(int i= 0; i<N; i++){
        ranking[i] = Integer.parseInt(st.nextToken());
      }
        
        
      // 현재 랭킹이 꽉 찼고
      // 마지막 점수보다 같거나 작으면 못 들어감
      if(N == P && score <= ranking[N-1]){
        System.out.println(-1);
        return;
      }
      
      int rank = 1;
      for (int i = 0; i < N; i++) {
        if (score < ranking[i]) rank++;
        else break;
      }
      System.out.println(rank);
     
  }
}