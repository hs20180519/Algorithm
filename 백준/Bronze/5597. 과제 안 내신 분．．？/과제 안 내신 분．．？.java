import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int[] students = new int[31];
      for(int i=0; i<28; i++){
        int st = Integer.parseInt(br.readLine());
        students[st]++;
      }
      for(int i=0; i<31; i++){
        if(i!=0 && students[i] == 0){
          System.out.println(i);
        }
      }
    }
      
}