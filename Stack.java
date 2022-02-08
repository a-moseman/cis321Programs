import java.util.LinkedList;

public class Stack<T> {
    private LinkedList<T> elements;

    public Stack() {
        this.elements = new LinkedList<>();
    }

    public void add(T element) {
        elements.add(element);
    }

    public void remove() {
        elements.remove(elements.size());
    }

    public T peek(int index) {
        return elements.get(index);
    }

    public int size() {
        return elements.size();
    }
}