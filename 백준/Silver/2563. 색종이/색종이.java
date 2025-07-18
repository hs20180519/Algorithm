import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      StringTokenizer st = null;
      int[][] dohaeji = new int[100][100];
      for(int k=0; k<n; k++){
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken())-1;
        int y = Integer.parseInt(st.nextToken())-1;
        
        for(int i=x; i<x+10; i++){
          for(int j=y; j<y+10; j++){
            dohaeji[i][j] += 1;
          }
        }
      }
      
      int answer = 0;
      for(int i=0; i<100; i++){
        for(int j=0; j<100; j++){
          if(dohaeji[i][j] > 1){
            answer += dohaeji[i][j]-1 ;
          }
        }
      }
      System.out.println(n*100-answer);
  }
}