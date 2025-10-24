import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken()); // 단어 개수
      int M = Integer.parseInt(st.nextToken()); // 외울 단어 기준
      
      // 외울 단어 수가 넘는 것들을 저장하고, Queue에 저장
      // 저장할때, {단어, 나온 횟수, 길이} 로 저장
      Map<String, Integer> map = new HashMap<>();
      
      List<String[]> q = new LinkedList<>();
      
      for(int i=0; i<N; i++){
        String word = br.readLine();
        if(word.length()>= M){
          map.put(word, map.getOrDefault(word, 0) + 1);
        }
      }
      
      for(String str: map.keySet()){
        q.add(new String[]{str, String.valueOf(map.get(str)), String.valueOf(str.length())});
      }
      
      Collections.sort(q, (a, b) ->{
        if(a[1].equals(b[1])){
          if(a[2].equals(b[2])){
            return a[0].compareTo(b[0]);
          }else{
            return Integer.parseInt(b[2]) - Integer.parseInt(a[2]);
          }
        }else{
          return Integer.parseInt(b[1]) - Integer.parseInt(a[1]);
        }
      });
      
      StringBuilder sb = new StringBuilder();
      for(String[] s: q){
        sb.append(s[0]).append("\n");
      }
      
      System.out.println(sb.toString());
  }
}