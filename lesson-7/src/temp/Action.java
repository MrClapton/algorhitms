package temp;

public interface Action<T> {
    void accept(T element);
}