class Solution {
    public int solution(int[][] sizes) {
        // 가로는 최대
        // 세로는 최소
        
        int n = sizes.length;
        int maxX = 0;
        int maxY = 0;

        for(int i=0; i<n; i++){
            if(sizes[i][0] < sizes[i][1]){
                int temp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = temp;
            }
        }
        
        for(int i=0; i<n; i++){
            maxX = Math.max(sizes[i][0], maxX);
            maxY = Math.max(sizes[i][1], maxY);
        }
    
        return maxX*maxY;
    }
}