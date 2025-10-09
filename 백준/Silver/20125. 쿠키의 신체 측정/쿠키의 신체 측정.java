import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      // 1. 심장을 구하자 (위에 *이 있는 위치)
      // 2. 왼쪽, 오른쪽 팔, 허리, 왼쪽, 오른쪽 다리
      
      int N = Integer.parseInt(br.readLine());
      char[][] arr = new char[N][N];
      for(int i=0; i<N; i++){
        arr[i] = br.readLine().toCharArray();
      }
      
      int heartX = 0;
      int heartY = 0;
      boolean heartFind = false;
      for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
          if(!heartFind && i>0 && arr[i][j] == '*' && arr[i-1][j] == '*'){
            heartX = i;
            heartY = j;
            heartFind = true;
            break;
          }
        }
      }
      
      System.out.println(heartX+1 + " " + (heartY+1));
      
      int leftPal = 0;
      int rightPal = 0;
      for(int i=heartY-1; i>=0; i--){
        if(arr[heartX][i] == '*'){
          leftPal++;
        }else{
          break;
        }
      }
      
      for(int i=heartY+1; i<N; i++){
        if(arr[heartX][i] == '*'){
          rightPal++;
        }else{
          break;
        }
      }
      
      int huri = 0;
      int huriX = 0;
      int huriY = 0;
      int leftDari = 0;
      int rightDari = 0;
      
      for(int i=heartX+1; i<N; i++){
        if(arr[i][heartY] == '*'){
          huri++;
          huriX = i;
          huriY = heartY;
        }else{
          break;
          
        }
      }
      
      // 허리 왼쪽 기준 다리, 오른쪽 다리
      
      for(int i=huriX+1; i<N; i++){
        if(arr[i][huriY-1] == '*'){
          leftDari++;
        }
        if(arr[i][huriY+1] == '*'){
          rightDari++;
        }
      }

      
      
      System.out.println(leftPal + " " + rightPal+ " " + huri+ " " + leftDari + " " + rightDari);
  }
}