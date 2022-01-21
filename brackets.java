import java.util.Stack;

public class Bracket {
    /*
    "()", true
    ")(", false
    ")((()))", false
    "((()))()",true
    "", true
     */
    public static boolean isCorrect(String input) {
        Stack<Character> brackets = new Stack<>();
        char[] chars = input.toCharArray();
        for (Character c : chars) {
            if (c == '(') {
                brackets.push('(');
            } else if (c == ')') {
               if (brackets.isEmpty()) {
                   return false;
               } else {
                   brackets.pop();
               }
            }
        }
        return brackets.isEmpty();
    }

    public static void testOne() {
        String input = "()";
        System.out.println("Testing one: " + isCorrect(input));
    }

    public static void testCloseFirst() {
        String input = ")((()))";
        System.out.println("Testing close first: " + isCorrect(input));
    }

    public static void testTwoClose() {
        String input = "())";
        System.out.println("testTwoClose: " + isCorrect(input));
    }

    public static void main(String[] args) {
        testOne();
        testCloseFirst();
        testTwoClose();
    }
}
