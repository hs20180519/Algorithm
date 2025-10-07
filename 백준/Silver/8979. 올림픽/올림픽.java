import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());
      
      int[][] arr = new int[N+1][4];
      
      
      for(int i=1; i<=N; i++){
        st = new StringTokenizer(br.readLine());
        int country = Integer.parseInt(st.nextToken());
        int gold = Integer.parseInt(st.nextToken());
        int silver = Integer.parseInt(st.nextToken());
        int bronze = Integer.parseInt(st.nextToken());
        arr[N][0] = gold;
        arr[N][1] = silver;
        arr[N][2] = bronze;
        
      }
      
      int rank = 1;
      for(int i=1; i<=N; i++){
        if(arr[i][0] > arr[K][0]){
          rank++;
        }else if(arr[i][1] > arr[K][1]){
          rank++;
        }else if(arr[i][2] > arr[K][2]){
          rank++;
        }
      }
      
      System.out.println(rank);
    }
  }
