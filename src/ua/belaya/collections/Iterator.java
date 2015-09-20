package ua.belaya.collections;

public interface Iterator<T> {
    T next();
    boolean hasNext();
    void remove();
}
