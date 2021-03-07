import java.util.LinkedList;

public class ChainingHashMap<Key, Value> {
    private final int capacity = 7;
    private int size = 0;
    private LinkedList<Node>[] st;

    ChainingHashMap() {
        st = new LinkedList[capacity];
        for (int i = 0; i < st.length; i++) {
            st[i] = new LinkedList<>();
        }
    }

    private class Node {
        Key key;
        Value value;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private boolean isKeyNotNull(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Key null");
        }
        return true;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value value) {
        isKeyNotNull(key);
        int i = hash(key);
        for (Node node : st[i]) {
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }
        }
        st[i].addLast(new Node(key, value));
        size++;
    }

    public Value get(Key key) {
        isKeyNotNull(key);
        int i = hash(key);
        for (Node node : st[i]) {
            if (key.equals(node.key)) {
                return node.value;
            }
        }
        return null;
    }

    //TODO L8hwTask1.Added
    Value delete(Key key) {
        isKeyNotNull(key);
        int i = hash(key);
        for (int j = 0; j < st[i].size(); j++) {
            if (key.equals(st[i].get(j).key)) {
                Node temp = st[i].get(j);
                st[i].remove(st[i].get(j));
                size--;
                return temp.value;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("***ChainingHashMap***\n");
        for (int i = 0; i < capacity; i++) {
            sb.append(i).append(": [");
            for (int j = 0; j < st[i].size(); j++) {
                sb.append(st[i].get(j).key);
                if (j != st[i].size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}