
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int j=0; j<M; j++){
            int n = Integer.parseInt(st.nextToken());
            System.out.println(binarySearch(arr, n, N));
        }
    }
    public static int binarySearch(int[] arr, int n, int len){
        int start = 0;
        int end = len-1;
        while(start<=end){
            int mid = (start+end)/2;

            if(arr[mid]==n){
                return 1;
            }
            else if(arr[mid]>n){
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        return 0;
    }
}
