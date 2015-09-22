package ua.belaya.collections.list;

public interface List<T> extends Iterable<T> {
    void add(T object);

    void add(int index, T object);

    @SuppressWarnings("unchecked")
    void addAll(T... objects);

    void remove(T object);

    boolean contains(T object);

    void clear();

    boolean isEmpty();

    int indexOf(T object);

    int lastIndexOf(T object);

    int size();

    void set(int index, T element);

    T get(int index);
}
