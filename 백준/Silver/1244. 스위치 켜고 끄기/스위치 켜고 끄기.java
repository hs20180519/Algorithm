import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      int[] sw = new int[N+1];
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      sw[0] = -1;
      for(int i=1; i<=N; i++){
        sw[i] = Integer.parseInt(st.nextToken());
      }
      
      int M = Integer.parseInt(br.readLine()); // 학생 수
      
      for(int j=0; j<M; j++){
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()); // 1: 남, 2: 여
        int n = Integer.parseInt(st.nextToken()); // 받은 수
        
        if(s == 1){ // 남자면
          for(int i=1; i <= N; i++){
            if(i%n == 0){ // 배수라면 스위치의 상태 변화
              sw[i] = 1-sw[i];
            }
          }
        }else{
          sw[n] = 1-sw[n];
          
          for(int i=1; i<=N; i++){
          
            if(n+i > N || n-i < 1) break;
            if(sw[n+i] == sw[n-i]){
              sw[n+i] = 1-sw[n+i];
              sw[n-i] = 1-sw[n-i];
            }else{
              break;
            }
          }
        }
        
        
      }
      
      StringBuilder sb = new StringBuilder();
      for(int i=1; i<=N; i++){
        sb.append(sw[i]).append(" ");
        if(i % 20 == 0) sb.append("\n");
      }
      System.out.println(sb.toString());
  }
}