import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      int longNum = N/4;
      StringBuilder sb = new StringBuilder();
      while(longNum-->0){
        sb.append("long").append(" ");
      }
      sb.append("int");
      System.out.println(sb.toString());
  }
}