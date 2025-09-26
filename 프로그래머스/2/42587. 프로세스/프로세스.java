import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<int[]> q = new LinkedList<>();
        
        for(int i=0; i<priorities.length; i++){
            q.add(new int[] {i, priorities[i]});
        }
        
        int count = 1;
     
        while(!q.isEmpty()){
            int[] curr = q.poll();
            boolean skip = false;
            // System.out.println("현재는"+ Arrays.toString(curr));
            
            for(int[] c : q){
                if(c[1] > curr[1]){ // 더 높은 프로세스가 있다면
                    // System.out.println("더 높은 프로세스가 있음");
                    q.add(curr);
                    skip = true;
                    break;
                }
            }
            // System.out.println();
            
           
            if(!skip){
                if(curr[0] == location){
                    return count;
                }
                count++;
            }
            
        }
        
        
        return count;
    }
}