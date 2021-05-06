import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * PROBLEM DESCRIPTION:
 *
 * A palindrome is a whole number that's the same when read backwards in base 10,
 * such as 12321 or 9449.
 *
 * Given a positive whole number, find the smallest palindrome greater than the given number.
 *
 * For large inputs, your solution must be more efficient than incrementing and checking each
 * subsequence number to see if it's a palindrome. Find nextpal(3^39) before posting your solution.
 */
public class NextPalindrome {
    /**
     * This is a utility function that flips a Long using a stack
     * O(n)
     *
     * @param flippedLong The long that is to be flipped
     * @return Returns the flipped version of the long
     */
    private static Long flip(Long flippedLong) {
        String toFlip = flippedLong.toString();
        /*
        The FILO nature of the stack allows for proper flipping when
        reading from left to right.
         */
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < toFlip.length() ; i++) {
            stack.add(toFlip.charAt(i));
        }
        StringBuilder returned = new StringBuilder();
        while (!stack.isEmpty()) {
            returned.append(stack.pop());
        }
        return Long.parseLong(returned.toString());
    }

    /**
     * This is the utility function that finds the nearest palindrome.
     * O(n)
     *
     * @param start The starting number to find the nearest palindrome.
     * @return Returns the nearest palindrome to the starting number
     */
    public static long findPalindrome(long start) {
        /*
        Increments the starting value.
        This checks the edge case where the starting number IS a palindrome.
         */
        start += 1;
        /*
        Sets the starting number to a string so that it can be parsed.
         */
        String number = Long.toString(start);
        boolean even = number.length() % 2 == 0;
        int length = number.length();
        int left = 0;
        int right = length - 1;
        Queue<Character> queue = new LinkedList<>();
        StringBuilder leftString = new StringBuilder();
        StringBuilder rightString = new StringBuilder();
        /*
        Creates a left and a right substring.
        This accounts for a middle character due to the > sign.
        The queue produces the flipped right number.
        This is because FIFO works for concatenation when reading from right to left.
         */
        while(right > left) {
            leftString.append(number.charAt(left++));
            queue.add(number.charAt(right--));
        }
        while (!queue.isEmpty()) {
            rightString.append(queue.poll());
        }
        /*
        Instantiates each number as well as its flipped counterpart.
         */
        Long leftNumber = Long.parseLong(leftString.toString());
        Long flippedLeftNumber = flip(leftNumber);
        Long flippedRightNumber = Long.parseLong(rightString.toString());
        Long rightNumber = flip(flippedRightNumber);
        /*
        This is the mathematics and string manipulation that creates the palindrome.
         */
        if (flippedLeftNumber < rightNumber) {
            leftNumber++;
        }
        String palindrome = leftNumber.toString();
        if (!even) {
            palindrome += number.charAt((length - 1)/2);
        }
        palindrome += flip(leftNumber);
        /*
        Garbage collects and then returns the nearest palindrome
         */
        System.gc();
        return Long.parseLong(palindrome);
    }
}
