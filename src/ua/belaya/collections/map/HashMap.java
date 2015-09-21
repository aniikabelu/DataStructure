package ua.belaya.collections.map;

import ua.belaya.collections.list.ArrayList;
import ua.belaya.collections.list.LinkedList;

public class HashMap<K, V> implements Map<K, V> {
    private LinkedList<Entry<K,V>>[] entryList;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        entryList = (LinkedList<Entry<K,V>>[]) new LinkedList[16];

        for (int i = 0; i < entryList.length; i++) {
            entryList[i] = new LinkedList<>();
        }
    }

    public void put(K key, V value) {
        LinkedList<Entry<K,V>> list;

        if (key == null) {
            list = entryList[0];

            for (Entry<K,V> entry : list) {
                if (entry.key == null) {
                    entry.value = value;
                    return;
                }
            }

        } else {
            list = entryList[hash(key.hashCode())];


            for (Entry<K,V> entry : list) {
                if (key.equals(entry.key)) {
                    entry.value = value;
                    return;
                }
            }

        }

        list.add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        LinkedList<Entry<K,V>> list;

        if (key == null) {
            list = entryList[0];


            for (Entry<K,V> entry : list) {

                if (entry.key == null) {
                    return entry.value;
                }
            }

        } else {
            list = entryList[hash(key.hashCode())];


            for (Entry<K,V> entry : list) {

                if (key.equals(entry.key)) {
                    return entry.value;
                }
            }

        }

        return null;
    }

    public void remove(K key) {
        LinkedList<Entry<K,V>> list;

        if (key == null) {
            list = entryList[0];


            for (Entry<K,V> entry : list) {

                if (entry.key == null) {
                    list.remove(entry);
                    size--;
                    return;
                }

            }
        } else {
            list = entryList[hash(key.hashCode())];


            for (Entry<K,V> entry : list) {

                if (key.equals(entry.key)) {
                    list.remove(entry);
                    size--;
                    return;
                }
            }

        }
    }

    public boolean containsKey(K key) {
        LinkedList<Entry<K,V>> list;

        if (key == null) {
            list = entryList[0];


            for (Entry<K,V> entry : list) {

                if (entry.key == null) {
                    return true;
                }
            }

        } else {
            list = entryList[hash(key.hashCode())];


            for (Entry<K,V> entry : list) {

                if (key.equals(entry.key)) {
                    return true;
                }
            }

        }

        return false;
    }

    public boolean containsValue(V value) {
        if (value == null) {
            for (LinkedList<Entry<K,V>> list : entryList) {
                for (Entry<K,V> entry : list) {
                    if (entry.value == null) {
                        return true;
                    }
                }
            }
        } else {
            for (LinkedList<Entry<K,V>> list : entryList) {
                for (Entry<K,V> entry : list) {
                    if (value.equals(entry.value)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void putIfAbsent(K key, V value) {
        if (containsKey(key)) {
            return;
        }

        LinkedList<Entry<K,V>> list;

        if (key == null) {
            list = entryList[0];
        } else {
            list = entryList[hash(key.hashCode())];
        }

        list.add(new Entry<>(key, value));
        size++;
    }

    public ArrayList<K> keyList() {
        ArrayList<K> arrayList = new ArrayList<>();

        for (LinkedList<Entry<K,V>> list : entryList) {
            for (Entry<K,V> entry : list) {
                arrayList.add(entry.key);
            }
        }

        return arrayList;
    }

    public ArrayList<V> valueList() {
        ArrayList<V> arrayList = new ArrayList<>();

        for (LinkedList<Entry<K,V>> list : entryList) {
            for (Entry<K,V> entry : list) {
                arrayList.add(entry.value);
            }
        }

        return arrayList;
    }

    public ArrayList<Entry<K,V>> entryList() {
        ArrayList<Entry<K,V>> arrayList = new ArrayList<>();

        for (LinkedList<Entry<K,V>> list : entryList) {
            for (Entry<K,V> entry : list) {
                arrayList.add(entry);
            }
        }

        return arrayList;
    }

    public void clear() {
        for (LinkedList<Entry<K,V>> list : entryList) {
            list.clear();
        }

        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void putAll(HashMap<K, V> hashMap) {
        for (Entry<K,V> entry : hashMap.entryList()) {
            put(entry.key, entry.value);
        }
    }

    public int size() {
        return size;
    }

    private int hash(int hashCode) {
        return hashCode % 16;
    }

    private class Entry<T,E> {
        private T key;
        private E value;

        private Entry(T key, E value) {
            this.key = key;
            this.value = value;
        }
    }
}
