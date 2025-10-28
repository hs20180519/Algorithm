import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Deque<int[]> q = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();
        // 순차대로, 큐 -> {7, 1} {70, 30} {45, 5}
        for(int i=0; i<progresses.length; i++){
            q.add(new int[]{100-progresses[i],speeds[i]});
        }
        
        int day = 0;
        while(!q.isEmpty()){
            int[] curr = q.pollFirst();
            int a = curr[0];
            int b = curr[1];
            
            int d = a/b;
            int cnt = 1; // 배포 가능한 기능
            if(a%b !=0) d++;
            
            while(!q.isEmpty()){
                int[] c = q.pollFirst();
                if(c[0]%c[1] == 0 && c[0] / c[1] == d){
                    cnt++;
                }else if(c[0] / c[1] < d){
                    cnt++;
                }else{
                    q.addFirst(c); // 다시 앞으로 넣고
                    break; // 종료
                }
            }
            
            ans.add(cnt);
            System.out.println(ans);
            
        }
        int[] answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++){
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
}