import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());
      for(int t = 1; t<=T; t++){
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 문서의 개수
        int M = Integer.parseInt(st.nextToken()); // 궁금한 문서 인덱스 
        int order = 0; // 순서
        
        int[] countP = new int[10]; // 중요도 배열
        Queue<int[]> q = new LinkedList<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
          int a = Integer.parseInt(st.nextToken());
          q.offer(new int[]{a,i}); // 중요도, 문서 인덱스
          countP[a]++;
        }
        
        while(!q.isEmpty()){
          int[] curr = q.poll();
          int priority = curr[0];
          int index = curr[1];
          
          // 현재 priority 보다 중요도가 높은 문서가 존재한다면
          if(existHigher(priority, countP)){
            q.offer(new int[]{priority, index}); // 맨 뒤에 재배치
          }else{
            order++;
            countP[priority]--;
            if(index == M){ // 궁금한 문서 끝나면
              break;
            }
          }
        }
        System.out.println(order);
      }
  }
  public static boolean existHigher(int p, int[] countP){
    for(int i=9; i>p; i--){
      if(countP[i] > 0) return true;
    }
    return false;
  }
  
}