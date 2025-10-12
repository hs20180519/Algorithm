import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;

        String[] str1Ssang = makeSsang(str1);
        String[] str2Ssang = makeSsang(str2);
        if(isGongZiphab(str1Ssang) && isGongZiphab(str2Ssang)) return 65536;
        
        Map<String, Integer> a = new HashMap<>();
        Map<String, Integer> b = new HashMap<>();
        
        for(int i=0; i<str1Ssang.length; i++){
            if(str1Ssang[i] == null) continue;
            
            a.put(str1Ssang[i], a.getOrDefault(str1Ssang[i], 0) + 1);
        }
        
        for(int i=0; i<str2Ssang.length; i++){
            if(str2Ssang[i] == null) continue;
            b.put(str2Ssang[i], b.getOrDefault(str2Ssang[i], 0) + 1);
        }
        
        int gyu = 0;
        int hap = 0;
        for(String i: a.keySet()){
            if(b.getOrDefault(i, 0) != 0){
                gyu += Math.min(a.get(i), b.get(i));
                hap += Math.max(a.get(i), b.get(i));
            }else{
                hap += a.get(i);
            }
        }
        
        for(String i: b.keySet()){
            if(a.getOrDefault(i, 0) == 0){
                hap += b.get(i);
            }
        }

        double aaa = (double) gyu/hap;
        return (int)(aaa*65536);
        
    }
    
    public String[] makeSsang(String str){
        char[] arr = str.toCharArray();
        String[] ans = new String[str.length()-1];
        
        int idx = 0;
        int count = 0;
        int ansIdx = 0;
        StringBuilder sb = new StringBuilder();
        
        while(idx < str.length()){
            arr[idx] = Character.toLowerCase(arr[idx]);
            if(Character.isLetter(arr[idx])){
                sb.append(arr[idx]);
                count++;
            }else{
                sb = new StringBuilder();
                count = 0;
            }
            idx++;
            
            if(count == 2){
                ans[ansIdx++] = sb.toString();
                sb = new StringBuilder();
                count = 0;
                idx--;
            }
        }
        return ans;
    }
    
    public boolean isGongZiphab(String[] arr){
        for(int i=0; i<arr.length; i++){
            if(arr[i] != null) return false;
        }
        return true;
    }
}