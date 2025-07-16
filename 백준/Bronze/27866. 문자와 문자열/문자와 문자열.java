import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      char[] s = br.readLine().toCharArray();
      int i = Integer.parseInt(br.readLine());
      
      System.out.println(s[i-1]);
      
  }
}