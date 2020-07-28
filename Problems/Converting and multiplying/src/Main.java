import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = "";
        while (!"0".equals(s)) {
            try {
                s = scanner.next();
                int n = Integer.parseInt(s);
                if (n == 0) {
                    continue;
                }
                System.out.println(n * 10);
            } catch (NumberFormatException e) {
                System.out.printf("Invalid user input: %s%n", s);
            }
        }
    }
}