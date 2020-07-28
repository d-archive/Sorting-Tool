import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();
        int N = scanner.nextInt();
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < A; i++) {
            char c = (char) (65 + (i % 25));
            sb.append(c);
        }
        
        for (int i = 0; i < B; i++) {
            char c = (char) (97 + (i % 25));
            sb.append(c);
        }
        
        for (int i = 0; i < C; i++) {
            sb.append(i % 10);
        }
        
        for (int i = C; i < (N - (A + B + C)) + C; i++) {
            sb.append(i % 10);
        }

        System.out.println(sb.toString());
    }
}
