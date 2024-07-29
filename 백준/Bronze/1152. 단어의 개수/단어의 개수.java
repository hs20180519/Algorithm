
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] arr = str.split("");

        // str안의 공백을 세고 + 1 = 단어의 개수
        int count = 0;
        if (arr[0].equals(" ")){
            count--;
        }
        // 맨 끝 공백 제거
        if (arr[arr.length-1].equals(" ")){
            count--;
        }

        for (String s : arr) {
            if (s.equals(" ")) {
                count++;
            }
        }

        System.out.println(count+1);

    }

}
