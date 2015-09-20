package ua.belaya.collections.list;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] list;
    private int size = 0;

    public ArrayList() {
        list = (T[]) new Object[10];
    }

    public void set(int index, T object) {
        checkIndex(index);
        list[index] = object;
    }

    public T get(int index) {
        checkIndex(index);

        return list[index];
    }

    public void add(T object) {
        add(size, object);
    }

    public void add(int index, T object) {
        checkIndexForAdd(index);

        if (size == list.length) {
            T[] newArray = (T[]) new Object[list.length * 2];
            System.arraycopy(list, 0, newArray, 0, list.length);
            list = newArray;
        }

        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = object;
        size++;
    }

    public void addAll(T... objects) {
        for (T i : objects) {
            add(i);
        }
    }

    public void trimToSize() {
        if (list.length > size) {
            T[] newList = (T[]) new Object[size];
            System.arraycopy(list, 0, newList, 0, size);
            list = newList;
        }
    }

    public void remove(T object) {
        if (object == null) {
            for (int i = 0; i < size; i++) {
                if (list[i] == null) {
                    System.arraycopy(list, i + 1, list, i, size - i - 1);
                    list[size] = null;
                    size--;
                    break;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (object.equals(list[i])) {
                    System.arraycopy(list, i + 1, list, i, size - i - 1);
                    list[size] = null;
                    size--;
                    break;
                }
            }
        }
    }

    public boolean contains(T object) {
        return indexOf(object) != -1;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            list[i] = null;
        }

        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int indexOf(T object) {
        if (object != null) {
            for (int i = 0; i < size; i++) {
                if (object.equals(list[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (list[i] == null) {
                    return i;
                }
            }
        }

        return -1;
    }

    public int lastIndexOf(T object) {
        if (object != null) {
            for (int i = size - 1; i >= 0; i--) {
                if (object.equals(list[i])) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (list[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int cursor;

        public T next() {
            if (cursor == size) {
                throw new IndexOutOfBoundsException("cursor = " + cursor + "size = " + size);
            }

            T element = list[cursor];
            cursor++;

            return element;
        }

        public boolean hasNext() {
            return cursor < size;
        }

        // 0 1 2
        public void remove() {
            if (cursor == 0) {
                throw new IndexOutOfBoundsException("cursor = " + cursor);
            }

            if (cursor < size) {
                System.arraycopy(list, cursor, list, cursor - 1, size - cursor);
            }

            list[size] = null;
            size--;
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("index = " + index + "size = " + size);
        }
    }

    private void checkIndex(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException("index = " + index + "size = " + size);
        }
    }
}
