import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = 101;
      while(T-->0){
        String arr = br.readLine();
        if(arr==null)break;
        System.out.println(arr);
      }
  }
}