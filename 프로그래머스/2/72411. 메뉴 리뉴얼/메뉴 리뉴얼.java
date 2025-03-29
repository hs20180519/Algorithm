import java.util.*;
class Solution {
    Map<String, Integer> map = new HashMap<>();
    List<String> result;
    public String[] solution(String[] orders, int[] course) {
       result = new ArrayList<>();
        
        for(int c : course) {
            map = new HashMap<>();
            for(String order : orders){
                char[] arr = order.toCharArray();
                Arrays.sort(arr);
                combi(arr, 0, "", c);
            }

            // 최대값 찾기
            int max = 0;
            for(int v : map.values()){
                max = Math.max(max, v);
            }

            // 조건: 2번 이상 주문된 조합만 결과에 추가
            if(max >= 2){
                for(String key : map.keySet()){
                    if(map.get(key) == max){
                        result.add(key);
                    }
                }
            }
        }
        Collections.sort(result);
        return result.toArray(new String[0]);
    }
    
    public void combi(char[] arr, int start, String path, int target){
         if(path.length() == target){
            map.put(path, map.getOrDefault(path, 0) + 1);
            return;
        }

        for(int i = start; i < arr.length; i++){
            combi(arr, i + 1, path + arr[i], target);
        }
    }

}