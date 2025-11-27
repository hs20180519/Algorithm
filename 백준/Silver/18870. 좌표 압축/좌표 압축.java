import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] temp = new int[N];
        HashMap<Integer, Integer> hmap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int a = Integer.parseInt(st.nextToken());
            arr[i] = a;
            temp[i] = a;
        }
        
        Arrays.sort(temp);
        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (!hmap.containsKey(temp[i])) {
                hmap.put(temp[i], idx++);
            }
        }
        
        int len = hmap.size();
        for(int i=0; i<N; i++){
            sb.append(hmap.get(arr[i])).append(" ");
        }
        
        System.out.println(sb.toString());
    }
}
