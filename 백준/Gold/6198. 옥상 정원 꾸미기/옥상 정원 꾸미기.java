import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int N = Integer.parseInt(br.readLine());
      long[] buildings = new long[N];
      
      for(int i=0; i<N; i++){
        buildings[i] = Long.parseLong(br.readLine());  
      }
      
      // N은 80,000이므로 ^2이면 시간초과 발생. 보통 10,000 이상일시 N^2 불가
      Stack<Long> stack = new Stack<>();
      long answer = 0;

      for(int i=0; i<N; i++){
        long currHeight = buildings[i];
        
        while(!stack.isEmpty() && stack.peek() <= currHeight){
          stack.pop();
        }
        // System.out.println(stack + " "+currHeight);
        answer+=stack.size();
        
        stack.push(currHeight);
      }
      System.out.println(answer);
  }
}