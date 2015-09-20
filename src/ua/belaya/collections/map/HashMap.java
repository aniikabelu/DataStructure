package ua.belaya.collections.map;

import ua.belaya.collections.list.ArrayList;
import ua.belaya.collections.list.LinkedList;

public class HashMap<K, V> implements Map<K, V> {
    private LinkedList<Entry>[] entryList;
    private int size;

    public HashMap() {
        entryList = (LinkedList<Entry>[]) new LinkedList[16];

        for (int i = 0; i < entryList.length; i++) {
            entryList[i] = new LinkedList<>();
        }
    }

    public void put(K key, V value) {
        LinkedList<Entry> list;

        if (key == null) {
            list = entryList[0];

            if (!list.isEmpty()) {
                for (Entry entry : list) {
                    if (entry.key == null) {
                        entry.value = value;
                        return;
                    }
                }
            }
        } else {
            list = entryList[hash(key.hashCode())];

            if (!list.isEmpty()) {
                for (Entry entry : list) {
                    if (key.equals(entry.key)) {
                        entry.value = value;
                        return;
                    }
                }
            }
        }

        list.add(new Entry(key, value));
        size++;
    }

    public V get(K key) {
        LinkedList<Entry> list;

        if (key == null) {
            list = entryList[0];

            if (!list.isEmpty()) {
                for (Entry entry : list) {

                    if (entry.key == null) {
                        return entry.value;
                    }
                }
            }
        } else {
            list = entryList[hash(key.hashCode())];

            if (!list.isEmpty()) {
                for (Entry entry : list) {

                    if (key.equals(entry.key)) {
                        return entry.value;
                    }
                }
            }
        }

        return null;
    }

    public void remove(K key) {
        LinkedList<Entry> list;

        if (key == null) {
            list = entryList[0];

            if (!list.isEmpty()) {
                for (Entry entry : list) {

                    if (entry.key == null) {
                        list.remove(entry);
                        size--;
                        return;
                    }
                }
            }
        } else {
            list = entryList[hash(key.hashCode())];

            if (!list.isEmpty()) {
                for (Entry entry : list) {

                    if (key.equals(entry.key)) {
                        list.remove(entry);
                        size--;
                        return;
                    }
                }
            }
        }
    }

    public boolean containsKey(K key) {
        LinkedList<Entry> list;

        if (key == null) {
            list = entryList[0];

            if (!list.isEmpty()) {
                for (Entry entry : list) {

                    if (entry.key == null) {
                        return true;
                    }
                }
            }
        } else {
            list = entryList[hash(key.hashCode())];

            if (!list.isEmpty()) {
                for (Entry entry : list) {

                    if (key.equals(entry.key)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean containsValue(V value) {
        if (value == null) {
            for (LinkedList<Entry> list : entryList) {
                for (Entry entry : list) {
                    if (entry.value == null) {
                        return true;
                    }
                }
            }
        } else {
            for (LinkedList<Entry> list : entryList) {
                for (Entry entry : list) {
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

        LinkedList<Entry> list;

        if (key == null) {
            list = entryList[0];
        } else {
            list = entryList[hash(key.hashCode())];
        }

        list.add(new Entry(key, value));
        size++;
    }

    public ArrayList<K> keyList() {
        ArrayList<K> arrayList = new ArrayList<>();

        for (LinkedList<Entry> list : entryList) {
            for (Entry entry : list) {
                arrayList.add(entry.key);
            }
        }

        return arrayList;
    }

    public ArrayList<V> valueList() {
        ArrayList<V> arrayList = new ArrayList<>();

        for (LinkedList<Entry> list : entryList) {
            for (Entry entry : list) {
                arrayList.add(entry.value);
            }
        }

        return arrayList;
    }

    public ArrayList<Entry> entryList() {
        ArrayList<Entry> arrayList = new ArrayList<>();

        for (LinkedList<Entry> list : entryList) {
            for (Entry entry : list) {
                arrayList.add(entry);
            }
        }

        return arrayList;
    }

    public void clear() {
        for (LinkedList<Entry> list : entryList) {
            list.clear();
        }

        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void putAll(HashMap<K, V> hashMap) {
        for (Entry entry : hashMap.entryList()) {
            put(entry.key, entry.value);
        }
    }

    public int size() {
        return size;
    }

    private int hash(int hashCode) {
        return hashCode % 16;
    }

    private class Entry {
        private K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
