import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      StringTokenizer st = null;
      int[][] temp = new int[N][2];
      int[] answer = new int[N];
      
      for(int i=0; i<N; i++){
        st = new StringTokenizer(br.readLine());
        temp[i][0]= Integer.parseInt(st.nextToken());
        temp[i][1] = Integer.parseInt(st.nextToken());
        
        answer[i] = 1; // answer 1로 초기화 - rank
      }
      
      for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
          if((temp[i][0] < temp[j][0]) && temp[i][1] < temp[j][1]){ // 나보다 정치가 큰 친구가 있다면 rank 증가
            answer[i]++;
          }
        }
      }

      
      for(int i=0; i<N; i++){
        System.out.print(answer[i] + " ");
      };
  }
}