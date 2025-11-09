import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String goal = br.readLine();
        HashMap<Character, Integer> hmap = new HashMap<>();
        for(char g : goal.toCharArray()){
            hmap.put(g, hmap.getOrDefault(g, 0) + 1);
        }
        int count = 0;
        
        for(int i=0; i<N-1; i++){
            HashMap<Character, Integer> temp= new HashMap<>();
            String word = br.readLine();
            
            for(char w : word.toCharArray()){
                temp.put(w, temp.getOrDefault(w, 0) + 1);
            }
            
            if(isBisut(hmap, temp)){
                count++;
            }
        }
        
        System.out.println(count); 
            
    }
    
    public static boolean isBisut(HashMap<Character, Integer> hmap, HashMap<Character, Integer> temp){
        // 1. hmap -> fakeHmap 복사
        HashMap<Character, Integer> fakeHmap = new HashMap<>();
        
        for(char c : hmap.keySet()){
            fakeHmap.put(c, hmap.get(c));
        }
        
        // 2. fakeHmap에서 temp 빼기
        for(char c : temp.keySet()){
            fakeHmap.put(c, fakeHmap.getOrDefault(c, 0) - temp.get(c));
        }
        
        // 3. fakeHmap의 values 세서 1, 0, -1 이면 비슷: true
        // 1, -1 => true
        // 1 -> true
        // 0 -> true
        // -1 -> true
        int first = 0;
        int second =0;
        for(int v : fakeHmap.values()){
            if(v == 0) continue;
            else if(v == 1){
                first++;
            }else if(v == -1){ 
                second++;
            }else{ // 그 외의 경우
                return false;
            }
            if(second > 1  ||  first > 1) return false; // 1이나 -1이 두 번 이상 나온다면
        }
        
        return true;
        
    }
}
