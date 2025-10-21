import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String eng = br.readLine();
      int len = Integer.parseInt(br.readLine());
      LinkedList<Character> s = new LinkedList<>();
      for(char c : eng.toCharArray()){
        s.add(c);
      }
        
      ListIterator<Character> cursor = s.listIterator(s.size());
      
      for(int i=0; i<len; i++){
        StringTokenizer st = new StringTokenizer(br.readLine());
        String command = st.nextToken();
        
        
        if(command.equals("L")){
          if(cursor.hasPrevious()) cursor.previous();
        }else if(command.equals("D")){
          if(cursor.hasNext()) cursor.next();
        }else if(command.equals("B")){
          if(cursor.hasPrevious()){
            cursor.previous();
            cursor.remove();
          }
        }else if(command.equals("P")){

          Character add = st.nextToken().charAt(0);
          cursor.add(add);
        }
        
        // System.out.println("현재 명령어: " + command + " 커서: " + cursor);
        // System.out.println(s);
        
        
      }
      
      StringBuilder sb = new StringBuilder();
      for(char c : s){
        sb.append(c);
      }
      System.out.println(sb.toString());
  }
}