import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String a = br.readLine();
      
      while(!a.equals("end")){
        char[] arr = a.toCharArray();
        
        if(isContainMoeum(arr) && !isMoeumOrJaeumContinue(arr) && !isContinueTwiceExceptEO(arr)){
          System.out.println("<"+ a + "> is acceptable.");
        }else{
          System.out.println("<"+ a + "> is not acceptable.");
        }
        
        a = br.readLine();
      }
  }
  
  public static boolean isContainMoeum(char[] arr){
    for(char c : arr){
      if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;
    }

    return false;
  }
  
  public static boolean isMoeumOrJaeumContinue(char[] arr){
    if(arr.length < 3) return false;
    for(int i=0; i<arr.length-2; i++){
       if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u'){
         if(arr[i+1] == 'a' || arr[i+1] == 'e' ||arr[i+1] == 'i' ||arr[i+1]== 'o' || arr[i+1] == 'u'){
          if(arr[i+2] == 'a' || arr[i+2] == 'e' ||arr[i+2] == 'i' ||arr[i+2]== 'o' || arr[i+2] == 'u'){
            return true;
          }
         }
       }else{
         if(arr[i+1] != 'a' && arr[i+1] != 'e' && arr[i+1] != 'i' && arr[i+1] != 'o' && arr[i+1] != 'u'){
          if(arr[i+2] != 'a' && arr[i+2] != 'e' && arr[i+2] != 'i' && arr[i+2] != 'o' && arr[i+2] != 'u'){
            return true;
          }
        }
      }
    }

    return false;
  }
  
  public static boolean isContinueTwiceExceptEO(char[] arr){
    if(arr.length < 2) return false;
    for(int i=0; i<arr.length-1; i++){
      if(arr[i] != 'e' && arr[i] != 'o' && arr[i] == arr[i+1]) return true;
    }
    return false;
  }
}