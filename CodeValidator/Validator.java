/**
 * Class for validating the java text
 */
public class Validator {
    private Stack<Character> stack = new Stack<>();
    private Stack<Integer> lineNumberStack = new Stack<>();

    /**
     * Validate a string.
     * 
     * @param raw The string to validate.
     * @return int
     */
    public int validate(String raw) {
        int parentheses = 0;
        int brackets = 0;
        int curlys = 0;
        buildStacks(raw);
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
        return parentheses == 0 && curlys == 0 && brackets == 0 ? -1 : line;
    }

    /**
     * Check if a character is a bracket.
     * 
     * @param c The character.
     * @return boolean
     */
    private boolean isBracket(char c) {
        return c == '[' || c == ']';
    }

    /**
     * Check if a character is a parentheses.
     * 
     * @param c The character.
     * @return boolean
     */
    private boolean isParentheses(char c) {
        return c == '(' || c == ')';
    }

    /**
     * Check if a character is a curly bracket.
     * 
     * @param c The character.
     * @return boolean
     */
    private boolean isCurly(char c) {
        return c == '{' || c == '}';
    }

    /**
     * Check if a character is a parentheses, bracket, or curly bracket.
     * 
     * @param c The character.
     * @return boolean
     */
    private boolean isOfType(char c) {
        return isBracket(c) || isCurly(c) || isParentheses(c);
    }

    /**
     * Check if a character is a closing parentheses, bracket, or curly bracket.
     * 
     * @param c The character.
     * @return boolean
     */
    private boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    /**
     * Check if a character is a parentheses, bracket, or curly bracket.
     * 
     * @param c The character
     * @return boolean
     */
    private boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    /**
     * Check to see if the characters are the same but opposite direction.
     * 
     * @param a The character expected to be closing.
     * @param b The character expected to be opening.
     * @return boolean
     */
    private boolean isOpposite(char a, char b) {
        return (a == ')' && b == '(') || (a == '}' || b == '{') || (a == ']' || b == '[');
    }

    /**
     * Build the character and line stacks.
     * 
     * @param raw The raw text to validate.
     */
    private void buildStacks(String raw) {
        stack = new Stack<>();
        lineNumberStack = new Stack<>();
        int lineNumber = 1;
        for (int i = 0; i < raw.length(); i++) {
            char c = raw.charAt(i);
            if (c == '\n') {
                lineNumber++;
            } else if (isOfType(c)) {
                stack.add(c);
                lineNumberStack.add(lineNumber);
            }
        }
    }
}
