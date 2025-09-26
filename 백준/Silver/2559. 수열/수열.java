import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken()); // 연속적인 날짜의 수
      int[] arr = new int[N];
      
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++){
        arr[i] = Integer.parseInt(st.nextToken());
      }
      
      // 연속적인 합 배열
      int[] answer = new int[N-K+1];
      int hap = Integer.MIN_VALUE;
 
      // 1. 맨 앞
      for(int i=0; i<K; i++){
        answer[0] += arr[i];
      }
      hap = Math.max(hap, answer[0]);
      
      // 2. 뒤의 것 더하고, 앞의 것 빼기
      // 3. 그 중 최댓값
      for(int i=1; i<N-K+1; i++){
        answer[i] = answer[i-1] - arr[i-1] + arr[i+K-1];
        hap = Math.max(hap, answer[i]);
      }
      
      System.out.println(hap);
  }
}