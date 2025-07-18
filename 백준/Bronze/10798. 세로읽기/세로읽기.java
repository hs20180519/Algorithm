import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String[] arr = new String [5];
      int maxLen = 0;
       
      for(int i=0; i<5; i++){
        arr[i] = br.readLine();
        maxLen = arr[i].length() > maxLen ? arr[i].length() : maxLen;
      }
      
      char[][] eng = new char[5][maxLen];
      
      for(int i=0; i<5; i++){
        for(int j=0; j<arr[i].length(); j++){
          eng[i][j] = arr[i].charAt(j);
        }
      }
      
      for(int i=0; i<maxLen; i++){
        for(int j=0; j<5; j++){
          if((0<= eng[j][i]-'0' && eng[j][i]-'0' <= 9) || ('A' <= eng[j][i] && eng[j][i] <= 'z')){
           System.out.print(eng[j][i]);
          }
        }
      }
  }
}