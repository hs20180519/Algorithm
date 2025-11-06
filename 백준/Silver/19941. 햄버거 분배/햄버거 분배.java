import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());
      
      String str = br.readLine();
      boolean[] visited = new boolean[str.length()];
      int people = 0;
      
      for(int i=0; i<str.length(); i++){
        if(str.charAt(i) == 'P'){ // 사람이면
          boolean canEat = false;
          for(int j=K; j>0; j--){ // 앞에서부터
            if(i-j >=0 && str.charAt(i-j) == 'H' && !visited[i-j]){
              visited[i-j] = true; // 방문
              people++;
              canEat = true;
              break;
            }
          }
          if(!canEat){
            for(int j=1; j<=K; j++){ // 뒤에도 가능한지 확인
              if(i+j < str.length() && str.charAt(i+j) == 'H' && !visited[i+j]){
                visited[i+j] = true;
                people++;
                break;
              }
            }
          }
        }
      }

      System.out.println(people);
  }
}