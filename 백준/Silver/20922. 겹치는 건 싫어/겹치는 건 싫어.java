import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
                
                
        int[] count = new int[100_001];
        
        int left = 0;
        int maxLen = 0;
        for(int right = 0; right < N; right++){
            int v = arr[right];
            count[v]++;
            
            // K개 초과 -> left 이동
            while(count[v] > K){
                count[arr[left]]--;
                left++;
            }
            
            maxLen = Math.max(maxLen, right-left+1);
        }
      System.out.println(maxLen);
  }
  
}
