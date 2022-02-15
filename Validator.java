/**
 * Class for validating the java text
 */
public class Validator {
    Stack<Character> stack = new Stack<>();
    Stack<Integer> lineNumberStack = new Stack<>();
    public int validate(String raw) {
        int parentheses = 0;
        int brackets = 0;
        int curlys = 0;

        buildStack(raw);
        Character last = null;
        int line = 0;
        while (stack.size() > 0) {
            line = lineNumberStack.pop();
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
                    return line;
                }
            } else {
                if (isOpening(stack.peek())) {
                    return line;
                }
            }
            last = stack.pop();
        }
        return parentheses == 0 && curlys == 0 && brackets == 0 ? -1 : line ;
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

    private void buildStack(String raw) {
        int lineNumber = 1;
        for (int i = 0; i < raw.length(); i++) {
            char c = raw.charAt(i);
            if (c=='\n'){
                lineNumber++;
            }
            else if (isOfType(c)) {
                stack.add(c);
                lineNumberStack.add(lineNumber);
            }
        }
    }

}
