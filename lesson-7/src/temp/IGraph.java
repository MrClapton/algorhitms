package temp;

public interface IGraph<T> {
    void add(T element);

    void addLinkBetween(T left, T right);

    void remove(T element);

    void removeLinkBetween(T left, T right);

    T find(T element);

    int nodes();

    int links();

    void clear();
}