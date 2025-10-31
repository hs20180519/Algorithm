import java.io.*;
import java.util.*;
public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        
        dfs(0, N, M, arr, new int[M], new boolean[N]);
        System.out.println(sb.toString());
        // 코드를 작성해주세요 
    }
    
    public static void dfs(int depth, int N, int M, int[] arr, int[] temp, boolean[] visited){
        if(depth == M){
            for(int i: temp){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        for(int i=0; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                temp[depth] = arr[i];
                dfs(depth+1, N, M, arr, temp, visited);
                visited[i] = false;
            }
        }
    }
}
