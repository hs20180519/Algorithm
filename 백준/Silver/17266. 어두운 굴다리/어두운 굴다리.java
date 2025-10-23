import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      int M = Integer.parseInt(br.readLine());
      
      int[] location = new int[M];
      StringTokenizer st = new StringTokenizer(br.readLine());
              
      for(int i=0; i<M; i++){
       location[i] = Integer.parseInt(st.nextToken());
      }
      
      int height = location[0]; // 2 - 0
      for(int i=1; i<M; i++){
        int dist = (location[i] - location[i-1] + 1) / 2;
        height = Math.max(height, dist);
      }
      
      height = Math.max(height, N-location[M-1]);
      System.out.println(height);
  }
}