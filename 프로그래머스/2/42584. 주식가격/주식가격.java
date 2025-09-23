import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // 현재 가격이 이전 스택의 가격보다 작으면 → 떨어진 것
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int idx = stack.pop();
                answer[idx] = i - idx; // 떨어지기까지 걸린 시간
            }
            stack.push(i); // 아직 안 떨어졌으니 스택에 저장
        }

        // 끝까지 안 떨어진 시점들 처리
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = n - 1 - idx;
        }

        return answer;
    }
}
