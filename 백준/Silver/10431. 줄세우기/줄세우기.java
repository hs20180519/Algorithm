import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int P = Integer.parseInt(br.readLine());
      for(int i=0; i<P; i++){
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int[] arr = new int[20];
        int count = 0;
        
        for(int j=0; j<20; j++){
          arr[j] = Integer.parseInt(st.nextToken());
          
          for(int k=0; k<j; k++){
            if(arr[k] > arr[j]) count++;
          }
          
        }
        
        System.out.println(T + " " + count);
      }
  }
}