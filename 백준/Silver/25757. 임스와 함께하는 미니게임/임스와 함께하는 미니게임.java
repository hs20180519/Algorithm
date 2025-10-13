import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int N = Integer.parseInt(st.nextToken());
      String game = st.nextToken();
      HashSet<String> hset = new HashSet<>();
      
      for(int i=0; i<N; i++){
        String name = br.readLine();
        hset.add(name);
      }
      
      if(game.equals("Y")){
        System.out.println(hset.size());
      }else if(game.equals("F")){
        System.out.println(hset.size()/2);
      }else{
        System.out.println(hset.size()/3);
      }
  }
}