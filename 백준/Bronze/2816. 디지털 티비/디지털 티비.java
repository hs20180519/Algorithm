import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      String[] arr = new String[N];
      for(int i=0; i<N; i++){
        arr[i] = br.readLine();
      }
      
      StringBuilder sb = new StringBuilder();
      
      // 1. KBS를 찾을 때까지 화살표를 내리고
      int point = 0;
      while(!arr[point].equals("KBS1")){
        point++;
        sb.append("1");
      }
      
      int size = point;
      String temp;
      // 2. 그만큼 채널을 위로 한칸씩 올림
      for(int i=point; i>0; i--){
        temp = arr[i];
        arr[i] = arr[i-1];
        arr[i-1] = temp;
        
        sb.append("4");
      }

      point = 0; // 첫번째 가리킴
      
      // 3. KBS2를 찾을 때까지 화살표를 내리고
      while(!arr[point].equals("KBS2")){
        point++;
        sb.append("1");
      }
      
      size = point;
      // 4. 그만큼 채널을 위로 한칸씩 올림
      for(int i=point; i>1; i--){
        temp = arr[i];
        arr[i] = arr[i-1];
        arr[i-1] = temp;
        
        sb.append("4");
      }
        // System.out.println(Arrays.toString(arr));
      
      System.out.println(sb.toString());
  }
}