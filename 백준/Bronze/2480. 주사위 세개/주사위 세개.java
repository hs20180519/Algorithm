import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      StringBuilder sb = new StringBuilder();
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      
      HashMap<Integer, Integer> map = new HashMap<>();
      map.put(a, map.getOrDefault(a, 0) + 1);
      map.put(b, map.getOrDefault(b, 0) + 1);
      map.put(c, map.getOrDefault(c, 0) + 1);
      
      // map 사이즈가 3이라면 가장 큰 눈 * 100
      
      // map 사이즈가 2라면 1000원 + (같은 눈)* 100
      
      // map 사이즈가 1라면 10000원 + (같은 눈) * 1000
      
      
      if(map.size() == 3){
        int maxNum = 0;
        for(int i : map.keySet()){
          maxNum = maxNum<i ? i: maxNum;
        }
        sb.append(maxNum*100);
      }else if(map.size() == 2){
        int sameNum = 0;
        for(int i : map.keySet()){
          if(map.get(i) == 2){
            sameNum = i;
          }
        }
        sb.append(1000+sameNum*100);
      }else{
        for(int i: map.keySet()){
          sb.append(10000+i*1000);
        }
      }

      System.out.println(sb.toString());
  }
}