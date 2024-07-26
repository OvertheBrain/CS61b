public class LinkedListDeque<T> implements Deque<T> {
    private int size;

    private class Node{
        private T item;
        private Node next;
        private Node prev;

        public Node(T item, Node next, Node prev){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node sentinel;
    private Node last;
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        last = new Node(null, null, sentinel);
        sentinel.next = last;
        this.size = 0;
    }

    public void addFirst(T item){
        Node newNode = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        this.size += 1;
    }

    public void addLast(T item){
        Node newNode = new Node(item, last, last.prev);
        last.prev.next = newNode;
        last.prev = newNode;
        this.size += 1;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public int size(){
        return this.size;
    }

    public void printDeque(){
        Node node = sentinel.next;
        while (node != last) {
            System.out.print(node.item.toString() + " ");
            node = node.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if (isEmpty()){
            return null;
        }

        Node first = sentinel.next;
        T value = first.item;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        this.size -= 1;
        return value;
    }

    public T removeLast(){
        if (isEmpty()){
            return null;
        }

        Node node = last.prev;
        T value = node.item;
        last.prev = node.prev;
        node.prev.next = last;
        this.size -= 1;
        return value;
    }

    public T get(int index){
        if (index >= size){
            System.out.println("Index out of bounds");
            return null;
        }

        Node node = sentinel.next;
        for (int i = 0; i < index; i++){
            node = node.next;
        }
        return node.item;
    }

    public T getRecursive(int index){
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Node node){
        if(index == 0){
            return node.item;
        }
        return getRecursiveHelper(index - 1, node.next);
    }
}
