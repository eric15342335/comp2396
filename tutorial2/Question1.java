package tutorial2;
import java.io.*;

public class Question1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String id = br.readLine();

        // note: the tutorial solution didn't '+ 1' to the first character
        int sum = (id.charAt(0) - 'A' + 1) * 8;

        for (int i = 1; i <= 6; i++) {
            int value = (id.charAt(i) >= 'A' && id.charAt(i) <= 'Z') ? id.charAt(i) - 'A' + 1 : id.charAt(i) - '0';
            sum += (8 - i) * value;
        }

        int check = 11 - (sum % 11);
        if (check == 10) {
            System.out.println('A');
        } else if (check == 11) {
            System.out.println('0');
        } else {
            System.out.println(check);
        }
    }
}
