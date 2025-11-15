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
        
        for(int m=0; m<M; m++){
            String[] arr = br.readLine().split(",");
            for(String a : arr){
                if(set.contains(a)) set.remove(a);
            }
            
            // System.out.println(set);
            System.out.println(set.size());
        }
        
        
        
        // 코드를 작성해주세요
    }
}
