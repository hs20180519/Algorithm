import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      char[] N = st.nextToken().toCharArray();
      int B = Integer.parseInt(st.nextToken());
      int answer = 0;
      
      for(int i=0; i<N.length; i++){
        if('A'<=N[i] && N[i]<= 'Z'){
          answer += (N[i]-'7') * Math.pow(B, N.length-i-1); 
        }
        else{
          answer+= (N[i]-'0') * Math.pow(B, N.length-i-1);
        }
      }
      System.out.println(answer);
    }
}