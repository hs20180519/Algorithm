import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      char[] words = br.readLine().toCharArray();
      HashMap<Character, Integer> map = new HashMap<>();
      for(char c : words){
        char cc = Character.toUpperCase(c);
        map.put(cc, map.getOrDefault(cc, 0) + 1);
      }
      
      char maxChar = Character.toUpperCase(words[0]);
      int maxNum = 0;
      
      for(char k : map.keySet()){
        if(map.get(k) > maxNum){
          maxChar = k;
          maxNum = map.get(k);
        }
      }
      // System.out.println(map);
      
      int count = 0;
      for(char k : map.keySet()){
        if(map.get(k) == maxNum && k!= maxChar){
          count++;
        }
      }
      
      if(count>0){
        System.out.println("?");
      }else{
        System.out.println(maxChar);
      }
      
    }
}