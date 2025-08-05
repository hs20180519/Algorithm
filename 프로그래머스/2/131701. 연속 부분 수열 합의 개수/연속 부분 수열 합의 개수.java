import java.util.*;

class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> set = new HashSet<>();
        int n = elements.length;

        for(int i=0; i<n; i++){
            int sum = 0;
            for(int j=0; j<n; j++){
                sum += elements[(i+j)% n];
                set.add(sum);
            }
        }
     
        return set.size();
    }
}
