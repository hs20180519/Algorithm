import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());
      
      while(T-- > 0){
        char[] arr = br.readLine().toCharArray();
        System.out.println(arr[0]+""+arr[arr.length-1]);
      }
  
      
  }
}