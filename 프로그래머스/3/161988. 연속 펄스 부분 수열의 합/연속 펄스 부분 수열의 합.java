import java.util.*;
import java.io.*;

class Solution {
    public long solution(int[] sequence) {
        // 누적합 배열
        int n = sequence.length;
        
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        
        long sum1 = 0;
        long sum2 = 0;
        
        for(int i=0; i<n; i++){
            int sign1 = (i%2 == 0) ? 1 : -1;
            int sign2 = (i%2 == 1) ? 1 : -1;
            
            sum1 = Math.max(sequence[i] * sign1, sum1+sequence[i] * sign1);
            sum2 = Math.max(sequence[i] * sign2, sum2+sequence[i] * sign2);
            
            max1 = Math.max(max1, sum1);
            max2 = Math.max(max2, sum2);
        }
        
        return Math.max(max1, max2);

    }
}