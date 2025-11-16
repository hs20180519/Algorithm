import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 원형 => 슬라이딩 윈도우
        // 윈도우 크기 = a개수
        // 윈도우 안에 들어있는 b의 최소 개수 = 필요한 swap 수
        
        String str = br.readLine();
        int n = str.length();
        
        int cntA = 0;
        for(char c : str.toCharArray()){
            if(c == 'a') cntA ++;
        }
        
        // a가 0이거나 없는 경우
        if(cntA == 0 || cntA == n){
            System.out.println(0);
            return;
        }
        
        // 원형 처리
        String ss= str+str;
        
        // 초기 윈도우 안 b개수 세기
        int cntB = 0;
        for(int i=0; i<cntA; i++){
            if(ss.charAt(i) == 'b') cntB++;
        }
        
        int answer = cntB;
        
        // 슬라이딩 윈도우
        for(int i=1; i<n; i++){
            // 전의 것이 b면
            if(ss.charAt(i-1) == 'b'){
                cntB--;
            }
            if(ss.charAt(i+cntA-1) == 'b'){
                cntB++;
            }
            
            answer = Math.min(answer, cntB);
        }
        
        System.out.println(answer);
    }
    
}
