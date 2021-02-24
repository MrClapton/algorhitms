package task3;

import task1.LinkedListImpl;

public class LinkedQueueImpl<Item> {
    private LinkedListImpl<Item> queue = new LinkedListImpl<>();

    //добавляет элемент в конец очереди
    public void insert(Item item) {
        queue.insertLast(item);
    }

    //удаляет и возвращает элемент из начала очереди
    public Item remove() {
        return queue.removeFirst();
    }

    //возвращает элемент из начала очереди
    public Item peek() {
        return queue.getFirst();
    }

    //возвращает true, если очередь пустая
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    //возвращает размер очереди
    public int size() {
        return queue.size();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
