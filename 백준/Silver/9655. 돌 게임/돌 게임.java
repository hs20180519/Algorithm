import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      
      // 1 -> 상근 WIN, SK
      // 2 -> 창영 WIN, CY
      // 3 -> 상근 WIN, SK
      // 4 -> 창영 WIN, CY
      // 홀수면 SK, 짝수면 CY
      
      if(N%2 == 0){
        System.out.println("CY");
      }else{
        System.out.println("SK");
      }
  }
}