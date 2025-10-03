import java.util.*;
import java.io.*;

public class Main {
  static int[][] classes;
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int H = Integer.parseInt(st.nextToken());
      int W = Integer.parseInt(st.nextToken());
      int N = Integer.parseInt(st.nextToken())+1;
      int M = Integer.parseInt(st.nextToken())+1;
      
      
      int garo = H/N;
      if(H%N != 0) garo++;
      
      int sero = W/M;
      if(W%M != 0) sero++;
      
      
      System.out.println(garo*sero);
    }
    

  }
