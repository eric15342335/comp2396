import java.io.*;

public class Question2 {
    public static int digit_sum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
    public static int d(int n) {
        return n + digit_sum(n);
    }
    public static boolean is_self_number(int n) {
        for (int i = 1; i < n; i++) {
            if (d(i) == n) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(reader);
        int n = Integer.parseInt(buffer.readLine());
        for (int i = 1; i < n; i++) {
            if (is_self_number(i)) {
                System.out.println(i);
            }
        }
    }
}
