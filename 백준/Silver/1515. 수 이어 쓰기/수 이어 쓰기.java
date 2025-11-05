import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int idx = 0;
        int N = 0;

        while (idx < S.length()) {
            N++;
            String num = Integer.toString(N);

            for (int i = 0; i < num.length(); i++) {
                if (idx < S.length() && S.charAt(idx) == num.charAt(i)) {
                    idx++;
                }
            }
        }
        System.out.println(N);
    }
}
