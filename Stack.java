import java.util.LinkedList;

/**
 * Stack data structure.
 */
public class Stack<T> {
    private LinkedList<T> elements;

    /**
     * Instantiate a new stack.
     */
    public Stack() {
        this.elements = new LinkedList<>();
    }

    /**
     * Add an element to the stack.
     * 
     * @param element The element to add.
     */
    public void add(T element) {
        elements.add(element);
    }

    /**
     * Remove and return the last element of the stack.
     * 
     * @return T
     */
    public T pop() {
        T element = elements.getLast();
        elements.removeLast();
        return element;
    }

    /**
     * Look at the last element of the stack.
     * 
     * @return T
     */
    public T peek() {
        return elements.getLast();
    }

    /**
     * Get the number of elements in the stack.
     * 
     * @return int
     */
    public int size() {
        return elements.size();
    }
}