package ua.belaya.collections.list;

import java.util.*;

public class LinkedList<T> implements List<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public void set(int index, T object) {
        indexCheck(index);
        searchNode(index).object = object;
    }

    public T get(int index) {
        indexCheck(index);
        return searchNode(index).object;
    }

    public void add(T object) {
        add(size, object);
    }

    public void add(int index, T object) {
        indexCheckForAdd(index);

        if (index == 0 && !isEmpty()) {
            addToBeginning(object);
            return;
        }

        if (isEmpty()) {
            addToEmptyList(object);
            return;
        }

        if (index == size) {
            addToEnd(object);
            return;
        }

        Node<T> node = searchNode(index - 1);
        Node<T> newNode = new Node<>(node, node.next, object);
        node.next = newNode;
        node.next.prev = newNode;

        size++;
    }

    @SuppressWarnings("unchecked")
    public void addAll(T... objects) {
        for (T object : objects) {
            add(object);
        }
    }

    public void remove(T object) {
        Node<T> node = first;

        if (object == null) {
            for (int i = 0; i < size; i++, node = node.next) {
                if (node.object == null) {
                    removeElement(node);
                    return;
                }
            }
        } else {
            for (int i = 0; i < size; i++, node = node.next) {
                if (object.equals(node.object)) {
                    removeElement(node);
                    return;
                }

            }
        }
    }

    public boolean contains(T object) {
        return indexOf(object) != -1;
    }

    public int indexOf(T object) {
        Node<T> node = first;

        if (object == null) {
            for (int i = 0; i < size; i++, node = node.next) {
                if (node.object == null) {
                    return i;
                }

            }
        } else {
            for (int i = 0; i < size; i++, node = node.next) {
                if (object.equals(node.object)) {
                    return i;
                }

            }
        }

        return -1;
    }

    public int lastIndexOf(T object) {
        Node<T> node = last;

        if (object == null) {
            for (int i = size - 1; i >= 0; i--, node = node.prev) {
                if (node.object == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--, node = node.prev) {
                if (object.equals(node.object)) {
                    return i;
                }
            }
        }

        return -1;
    }

    public void clear() {
        if (size != 0) {
            Node<T> node = first;
            Node<T> nextNode = node.next;

            for (int i = 0; i < size - 1; i++) {
                node.prev = null;
                node.next = null;
                node.object = null;
                node = nextNode;
                nextNode = nextNode.next;
            }

            size = 0;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        Node<T> node = first;

        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                list.append(node.object).append(", ");
            } else {
                list.append(node.object).append(". ");
            }

            node = node.next;
        }

        return list.toString();
    }

    private void indexCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index = " + index + "size = " + size);
        }
    }

    private void indexCheck(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("index = " + index + "size = " + size);
        }
    }

    private void removeElement(Node<T> node) {
        if (size != 1) {
            if (first.equals(node)) {
                node.next.prev = null;
                first = node.next;
            } else if (last.equals(node)) {
                node.prev.next = null;
                last = node.prev;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
        }

        node.object = null;

        size--;
    }

    private void addToBeginning(T object) {
        Node<T> newNode = new Node<>(null, first, object);

        first.prev = newNode;
        first = newNode;

        size++;
    }

    private void addToEnd(T object) {
        Node<T> newNode = new Node<>(last, null, object);
        last.next = newNode;
        last = newNode;

        size++;
    }

    private void addToEmptyList(T object) {
        Node<T> newNode = new Node<>(null, null, object);

        first = newNode;
        last = newNode;

        size++;
    }

    private class LinkedListIterator implements Iterator<T> {
        private int cursor;
        private Node<T> element;

        public T next() {
            if (cursor == size) {
                throw new IndexOutOfBoundsException("cursor = " + cursor + "size = " + size);
            }

            if (cursor == 0) {
                element = first;
            } else {
                element = element.next;
            }

            cursor++;

            return element.object;
        }

        public boolean hasNext() {
            return cursor < size;
        }

        public void remove() {
            if (cursor == 0) {
                throw new IndexOutOfBoundsException("cursor = " + cursor);
            }

            if (element.equals(first)) {
                first = element.next;
                element.next.prev = null;
            } else if (element.equals(last)) {
                last = element.prev;
                element.prev.next = null;
            } else {
                element.next.prev = element.prev;
                element.prev.next = element.next;
            }

            element.object = null;
            element = element.next;
            size--;
        }
    }

    private Node<T> searchNode(int index) {
        Node<T> node;

        if (index < size / 2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }

        return node;
    }

    private class Node<E> {
        private Node<E> next;
        private Node<E> prev;
        private E object;

        private Node(Node<E> prev, Node<E> next, E object) {
            this.prev = prev;
            this.next = next;
            this.object = object;
        }
    }
}
