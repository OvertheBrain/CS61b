public class ArrayDeque<T> implements Deque<T>{
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    private static final int INITIAL_CAPACITY = 8;

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = a;
    }

    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public void addFirst(T item) {
        if (size == items.length - 2 && size > 0) {
            resize(size * 2);
        }

        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        this.size += 1;
    }

    public void addLast(T item) {
        if (size == items.length - 2 && size > 0) {
            resize(size * 2);
        }

        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        this.size += 1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i).toString() + " ");
        }
        System.out.println();
    }

    private void stuffBeforeRemove() {
        if (size < items.length / 4 && size >= 8) {
            resize(size * 2);
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        stuffBeforeRemove();

        T value = items[(nextFirst + 1) % items.length];
        items[(nextFirst + 1) % items.length] = null;
        nextFirst = (nextFirst + 1) % items.length;
        this.size -= 1;
        return value;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        stuffBeforeRemove();

        T value = items[(nextLast - 1 + items.length) % items.length];
        items[(nextLast - 1 + items.length) % items.length] = null;
        nextLast = (nextLast - 1 + items.length) % items.length;
        this.size -= 1;
        return value;
    }

    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }
}
