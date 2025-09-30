import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = 3;
      for(int i=0; i<N; i++){
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        
        int sum = a+b+c+d;
        
        // 배 0 등 1
        if(sum == 4){ // 등 4
          System.out.println("E");
        }else if(sum == 0){ // 배 4
          System.out.println("D");
        }else if(sum == 1){ // 등 1 베 3
          System.out.println("C");
        }else if(sum == 2){
          System.out.println("B");
        }else{
          System.out.println("A");
        }
      }
      
      
      // System.out.println(answer);
  }
}