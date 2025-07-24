import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());
      
      while(T-->0){
        int money = Integer.parseInt(br.readLine());
        
        int a = money/25;
        money %= 25;
        int b = money/10;
        money %= 10;
        int c = money/5;
        money %= 5;
        int d = money/1;
        System.out.println(a + " " + b + " " + c + " " + d);
        
      }
  }
}