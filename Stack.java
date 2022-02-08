import java.util.LinkedList;

public class Stack<T> {
    private LinkedList<T> elements;

    public Stack() {
        this.elements = new LinkedList<>();
    }

    public void add(T element) {
        elements.add(element);
    }

    public T pop() {
        T element = elements.getLast();
        elements.removeLast();
        return element;
    }

    public T peek() {
        return elements.getLast();
    }

    public int size() {
        return elements.size();
    }
}