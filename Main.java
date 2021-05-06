public class Main {
    /**
     * This is an alternate power method to Math.pow to ensure maximum precision with large numbers.
     *
     * @param base The base of the power.
     * @param power The power to which the base is raised.
     * @return Returns the correct power.
     */
    private static long power(int base, int power) {
        if (power == 0) {
            return 1;
        }
        return(base * power(base, power - 1));
    }

    public static void main(String[] args) {
        System.out.println(NextPalindrome.findPalindrome(power(3,39)));
    }
}
