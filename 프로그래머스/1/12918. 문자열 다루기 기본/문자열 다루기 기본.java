class Solution {
    public boolean solution(String s) {
        boolean answer = true;
        // char [] arr = s.toCharArray();
        if(s.length() == 4 || s.length() == 6){
            for(char c : s.toCharArray()){
                if(0<=c-'0'&& c-'0'<=9){
                    continue;
                }else{
                    return false;
                }
            }
        }else{
            return false;
        }
        return true;
    }
}