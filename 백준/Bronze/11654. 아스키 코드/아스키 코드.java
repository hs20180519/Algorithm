import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // charAt : String으로 저장된 문자열 중 한 글자만 선택해서 char 타입으로 변환
        // charAt(0) : 0번째 있는 문자를 char타입으로 변환
        char a = br.readLine().charAt(0);
        

        // 문자 a를 정수형으로 캐스팅 : 아스키 코드로 변환
        System.out.println((int)a);
    }
}
