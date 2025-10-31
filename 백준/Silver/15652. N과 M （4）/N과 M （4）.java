import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        dfs(0, M, N, new int[M]);
        
        System.out.println(sb.toString());
        // 코드를 작성해주세요
        
    }
    
    public static void dfs(int depth, int M, int N, int[] temp){
        if(!isIncreasing(depth, temp)){
            // System.out.println(Arrays.toString(temp));
            return;
        }
        
        if(depth == M){
            
            for(int i:temp){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        for(int i=1; i<=N; i++){
            temp[depth] = i;
            dfs(depth+1, M, N, temp);
        }
    }
    
    public static boolean isIncreasing(int depth, int[] temp){
        for(int i=1; i<depth; i++){
            if(temp[i] < temp[i-1]) return false;
        }
        return true;
    }
}
