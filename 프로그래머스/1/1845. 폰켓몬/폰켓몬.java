import java.util.*;
class Solution {
    int answer = 0;
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        return Math.min(set.size(), nums.length/2);
    }
}