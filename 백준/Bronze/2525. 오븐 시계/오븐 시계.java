import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      StringBuilder sb = new StringBuilder();
      
      int time = Integer.parseInt(st.nextToken());
      int min = Integer.parseInt(st.nextToken());
      
      int cookTime = Integer.parseInt(br.readLine());
      
      int ansTime = time;
      int ansMin = min+cookTime;
      while(ansMin >= 60){
        ansMin -= 60;
        ansTime = ansTime+1 > 23 ? 0 : ansTime+1;
      }
      sb.append(ansTime).append(" ").append(ansMin);
      System.out.println(sb.toString());
  }
}