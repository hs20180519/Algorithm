import java.util.*;
class Solution {
    public int solution(int[][] points, int[][] routes) {
        
        int[][] count = new int[20000][101*101];
  
        for(int[] route : routes){
            int time = 0;
            
            int startX = points[route[0] - 1][0];
            int startY = points[route[0] - 1][1];
            count[time++][startX * 101+startY]++; 
            
            for (int i = 0; i < route.length - 1; i++) {
                int endX = points[route[i+1] - 1][0];
                int endY = points[route[i+1] - 1][1];
                
                while(endX != startX){
                    if(endX - startX > 0){
                        startX += 1;
                    }
                    else{
                        startX -= 1;
                    }
                    count[time++][startX * 101 + startY] ++;
                }
                while(endY != startY){
                    if(endY - startY > 0)
                        startY += 1;
                    else{
                        startY -= 1;
                    }
                    count[time++][startX * 101 + startY] ++;
                }   
            }
        }
        
        int answer = 0;
         for(int i=0; i<20000; i++){
            for(int j =0; j<101*101; j++){
                if (count[i][j] >= 2) {
              
                    answer++;
                }
            }
        }
        return answer;
    }
}