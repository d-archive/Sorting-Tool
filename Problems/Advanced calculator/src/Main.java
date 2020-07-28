import java.util.Arrays;
/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        
        int[] argList = Arrays.stream(Arrays.copyOfRange(args, 1, args.length))
                                .mapToInt(Integer::parseInt)
                                .toArray();

        int value = argList[0];
        
        if ("MIN".equals(operator)) {
            for (int num : argList) {
                if (num < value) {
                    value = num;
                }
            }    
        } else if ("MAX".equals(operator)) {
            for (int num : argList) {
                if (num > value) {
                    value = num;
                }
            }
        } else if ("SUM".equals(operator)) {
            value = 0;
            for (int num : argList) {
                value += num;
            }
        }
        
        System.out.println(value);
    }
}
