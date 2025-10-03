import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = null;
      while(true){
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        if(A == 0 && B == 0 && C == 0) break;
        
        int maxNum = Math.max(A, Math.max(B, C));
        // 1. Invalid 체크
        if(A + B + C - maxNum <= maxNum){
          System.out.println("Invalid");
        }else if(A == B && B == C){
          System.out.println("Equilateral");
        }else if((A == B && B != C) || (B == C && C != A) || (C == A) && (A != B)){
          System.out.println("Isosceles");
        }else{
            System.out.println("Scalene");
        }
      }
    }
  }
