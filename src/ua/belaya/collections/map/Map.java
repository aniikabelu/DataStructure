package ua.belaya.collections.map;

import ua.belaya.collections.list.ArrayList;

public interface Map<K, V> {
    void put(K key, V value);

    V get(K key);

    void remove(K key);

    boolean containsKey(K key);

    boolean containsValue(V value);

    void putIfAbsent(K key, V value);

    ArrayList keyList();

    ArrayList<V> valueList();

    void putAll(HashMap<K, V> hashMap);

    ArrayList entryList();

    int size();

    boolean isEmpty();

    void clear();
}
