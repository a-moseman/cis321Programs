/**
 * Class for validating the java text
 */
public class Validator {

    public boolean validate(String raw) {
        int parentheses = 0;
        int brackets = 0;
        int curlys = 0;

        Stack<Character> stack = buildStack(raw);
        Character last = null;
        while (stack.size() > 0) {
            char c = stack.peek();
            if (c == '(') {
                parentheses++;
            } else if (c == ')') {
                parentheses--;
            } else if (c == '[') {
                brackets++;
            } else if (c == ']') {
                brackets--;
            } else if (c == '{') {
                curlys++;
            } else if (c == '}') {
                curlys--;
            }

            if (last != null) {
                if (isClosing(last) && isOpening(stack.peek()) && !isOpposite(last, stack.peek())) {
                    System.out.println(last + ", " + stack.peek());
                    return false;
                }
            } else {
                if (isOpening(stack.peek())) {
                    return false;
                }
            }
            last = stack.pop();
        }
        return parentheses == 0 && curlys == 0 && brackets == 0;
    }

    private boolean isBracket(char c) {
        return c == '[' || c == ']';
    }

    private boolean isParentheses(char c) {
        return c == '(' || c == ')';
    }

    private boolean isCurly(char c) {
        return c == '{' || c == '}';
    }

    private boolean isOfType(char c) {
        return isBracket(c) || isCurly(c) || isParentheses(c);
    }

    private boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private boolean isOpposite(char a, char b) {
        return (a == ')' && b == '(') || (a == '}' || b == '{') || (a == ']' || b == '[');
    }

    private Stack<Character> buildStack(String raw) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < raw.length(); i++) {
            char c = raw.charAt(i);
            if (isOfType(c)) {
                stack.add(c);
            }
        }
        return stack;
    }

}
