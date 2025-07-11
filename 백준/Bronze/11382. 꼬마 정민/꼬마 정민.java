import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      StringBuilder sb = new StringBuilder();
      
      long a = Long.parseLong(st.nextToken());
      long b = Long.parseLong(st.nextToken());
      long c = Long.parseLong(st.nextToken());
      long ans = a+b+c;
      sb.append(ans);
      System.out.println(sb.toString());
  }
}