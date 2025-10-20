import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      Deque<Integer> q = new ArrayDeque<>();
      int N = Integer.parseInt(br.readLine());
      
      for(int i=1; i<=N; i++){
        q.add(i);
      }
      
      while(q.size()>1){
        // 1. 제일 위에 있는 카닥 버리기
        q.pollFirst();
            
        // 2. 제일 위의 카드를 제일 아래에 있는 카드 밑으로 옮기기
        q.addLast(q.pollFirst());
      }
      
      System.out.println(q.pop());
  }
}