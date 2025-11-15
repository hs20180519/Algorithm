import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<String> set = new HashSet<>();
        
        for(int n=0; n<N; n++){
            String s = br.readLine();
            set.add(s);
        }
        // System.out.println(set);
        
        for(int m=0; m<M; m++){
            String s = br.readLine();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<s.length(); i++){
                if(s.charAt(i) == ','){
                    String temp = sb.toString();
                    // System.out.println(temp);
                    if(set.contains(temp)){
                        set.remove(temp);
                    }
                    sb = new StringBuilder();
                }else{
                    sb.append(s.charAt(i));
                }
                
                if(i == s.length()-1){
                    String temp = sb.toString();
                    // System.out.println(temp);
                    if(set.contains(temp)){
                        set.remove(temp);
                    }
                    
                }
            }
            // System.out.println(set);
            System.out.println(set.size());
        }
        
        
        
        // 코드를 작성해주세요
    }
}
