import java.util.*;

class Solution {
    LinkedList<String> ans = new LinkedList<>();
    HashMap<String, Integer> hmap;
    public String[] solution(String[] orders, int[] course) {
        for(int c : course){
            hmap = new HashMap<>();
            for(String order : orders){
                char[] arr = order.toCharArray();
                Arrays.sort(arr);
                combi(0, "", arr, c);
            }
            
            int max = 0;
            for(int v: hmap.values()){
                max = Math.max(max, v);
            }
            
            if(max >=2){
                for(String k : hmap.keySet()){
                    if(hmap.get(k) == max){
                        ans.add(k);
                    }
                }
            }
        }
        
        String[] answer = new String[ans.size()];
        for(int i=0; i<ans.size(); i++){
            answer[i] = ans.get(i);
        }
  
       // System.out.println(ans);
        Arrays.sort(answer);
        
        return answer;
    }
    
    public void combi(int start, String path, char[] arr, int course){
        if(path.length() == course){
            hmap.put(path, hmap.getOrDefault(path, 0) + 1);
            return;
        }
        for(int i=start; i<arr.length; i++){
            combi(i+1, path + arr[i], arr, course);
        }
    }
}