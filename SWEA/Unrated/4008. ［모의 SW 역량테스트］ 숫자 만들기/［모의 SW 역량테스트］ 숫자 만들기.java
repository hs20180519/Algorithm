import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N;
    static int[] numbers;
    static char[] operators;
    static int maxNum;
    static int minNum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            // 각 연산자의 개수 +, -, *, /
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] operatorCount = new int[4];
            for (int i = 0; i < 4; i++) {
                operatorCount[i] = Integer.parseInt(st.nextToken());
            }

            // 수식에 사용되는 연산자 배열 생성
            List<Character> opList = new ArrayList<>();
            for (int i = 0; i < operatorCount[0]; i++) opList.add('+');
            for (int i = 0; i < operatorCount[1]; i++) opList.add('-');
            for (int i = 0; i < operatorCount[2]; i++) opList.add('*');
            for (int i = 0; i < operatorCount[3]; i++) opList.add('/');

            operators = new char[N - 1];
            for (int i = 0; i < N - 1; i++) {
                operators[i] = opList.get(i);
            }

            // 수식에 사용되는 숫자
            st = new StringTokenizer(br.readLine());
            numbers = new int[N];
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            minNum = Integer.MAX_VALUE;
            maxNum = Integer.MIN_VALUE;

            // 연산자 배열의 모든 순열을 생성하여 계산
            Arrays.sort(operators);
            do {
                calculate();
            } while (nextPermutation(operators));

            System.out.println("#" + tc + " " + (maxNum - minNum));
        }
    }

    // 현재 연산자 배열에 대해 계산을 수행
    public static void calculate() {
        int[] tempNumbers = Arrays.copyOf(numbers, N);
        for (int i = 0; i < N - 1; i++) {
            int a = tempNumbers[i];
            int b = tempNumbers[i + 1];
            switch (operators[i]) {
                case '+': tempNumbers[i + 1] = a + b; break;
                case '-': tempNumbers[i + 1] = a - b; break;
                case '*': tempNumbers[i + 1] = a * b; break;
                case '/': tempNumbers[i + 1] = a / b; break;
            }
        }
        int result = tempNumbers[N - 1];
        minNum = Math.min(minNum, result);
        maxNum = Math.max(maxNum, result);
    }

    // 다음 순열을 찾는 함수
    public static boolean nextPermutation(char[] array) {
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i]) i--;
        if (i <= 0) return false;

        int j = array.length - 1;
        while (array[j] <= array[i - 1]) j--;

        char temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
        return true;
    }
}
