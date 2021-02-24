package task1;

import java.util.Iterator;
import java.util.ListIterator;

//двусторонний связанный список
@SuppressWarnings("ALL")
public class LinkedListImpl<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size = 0;

    public LinkedListImpl() {
        first = null;
        last = null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iter();
    }

    public ListIterator<Item> listIterator() {
        return new ListIter();
    }

    /**
     * Внутренний класс листитератора(двустороннего)
     */
    public class ListIter implements ListIterator<Item> {
        Node current = new Node(null, first);

        //возвращает true, если есть следующий элемент
        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        //сдвигает текущий элемент на следующую позицию вправо
        @Override
        public Item next() {
            current = current.next;
            return (Item) current.getValue();
        }

        //возвращает true, если есть предыдующий элемент
        @Override
        public boolean hasPrevious() {
            return current.getPrevious() != null;
        }

        //сдвигает текущий элемент на следующую позицию влево
        @Override
        public Item previous() {
            current = current.previous;
            return (Item) current.getValue();
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        //удаляет текущий элемент
        @Override
        public void remove() {
            current.getNext().setPrevious(current.previous);
            current.getPrevious().setNext(current.next);
            size--;
        }

        //заменяет объект текущего элемента на новый
        @Override
        public void set(Item item) {
            current.setValue(item);
        }

        //добавляем новый объект после? реально до текущего элемента
        @Override
        public void add(Item item) {
            Node newNode = new Node(item, current, current.next);
            current.setNext(newNode);
            current.next.setPrevious(newNode);
            size++;
        }

    }

    /**
     * Внутренний класс итератора
     */
    public class Iter implements Iterator<Item> {//private
        Node current = new Node(null, first);

        //возвращает true, если есть следующий элемент
        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        //сдвигает текущий элемент на следующую позицию вправо
        @Override
        public Item next() {
            current = current.next;
            return (Item) current.getValue();
        }

        //удаляет текущий элемент
        @Override
        public void remove() {
            if (current.getNext() != null) {
                current.getNext().setPrevious(current.previous);
            }
            if (current.getPrevious() != null) {
                current.getPrevious().setNext(current.next);
            }
        }

    }

    /**
     * Внутренний класс элемента ссылочного списка
     */
    static class Node<Item> {
        private Item value;
        private Node next;
        private Node previous;

        public Node(Item value) {
            this.value = value;
        }

        public Node(Item value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(Item value, Node previous, Node next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        public Item getValue() {
            return value;
        }

        public void setValue(Item value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    public void insertFirst(Item item) {
        Node newNode = new Node(item);
        newNode.setNext(first);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.setPrevious(newNode);
        }
        first = newNode;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node oldFirst = first;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        } else {
            first.setPrevious(null);
        }
        return (Item) oldFirst.getValue();
    }

    public void insertLast(Item item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
        } else {
            newNode.setPrevious(last);
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node oldLast = last;
        if (last.getPrevious() != null) {
            last.getPrevious().setNext(null);
        } else {
            first = null;
        }
        size--;
        last = last.getPrevious();
        return (Item) oldLast.getValue();
    }

    public void insert(Item item, int index) {
        if (index <= 0) {
            insertFirst(item);
            return;
        }
        if (index > size) {
            index = size;
        }
        Node current = first;
        int i = 0;
        while (i < index - 1) {
            current = current.next;
            i++;
        }
        Node newNode = new Node(item);
        newNode.setNext(current.next);
        newNode.setPrevious(current);
        current.setNext(newNode);
        newNode.getNext().setPrevious(newNode);
        size++;
    }

    public boolean remove(Item item) {
        if (isEmpty()) {
            return false;
        }
        if (first.getValue().equals(item)) {
            removeFirst();
            return true;
        }
        Node current = first;
        while (current != null &&
                !current.getValue().equals(item)) {
            current = current.next;
        }
        if (current == null) {
            return false;
        }
        if (current == last) {
            removeLast();
            return true;
        }
        current.getNext().setPrevious(current.previous);
        current.getPrevious().setNext(current.next);
        size--;
        return true;
    }

    public boolean contains(Item item) {
        return indexOf(item) > -1;
    }

    public int indexOf(Item item) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.getValue().equals(item)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public Item getFirst() {
        if (isEmpty()) {
            return null;
        }
        return (Item) first.value;
    }

    public Item getLast() {
        if (isEmpty()) {
            return null;
        }
        return (Item) last.value;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        Node current = first;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (current != null) {
            sb.append(current.getValue()).append(", ");
            current = current.next;
        }
        sb.replace(sb.length() - 2, sb.length(), "]");
        return sb.toString();
    }
}