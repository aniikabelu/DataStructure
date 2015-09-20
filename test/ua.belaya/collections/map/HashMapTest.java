package ua.belaya.collections.map;

import org.junit.Before;
import org.junit.Test;
import ua.belaya.collections.list.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class HashMapTest {
    private HashMap<Object, String> hashMap;

    @Before
    public void createHashMap() {
        hashMap = new HashMap<>();
    }

    @Test
    public void testPut() {
        assertTrue(hashMap.size() == 0);

        hashMap.put(null, "anya");
        hashMap.put(12345, "artem");

        assertTrue(hashMap.size() == 2);

        assertEquals(hashMap.get(null), "anya");
        assertEquals(hashMap.get(12345), "artem");

        hashMap.put(null, "java");
        hashMap.put(12345, "krakov");

        assertTrue(hashMap.size() == 2);

        assertEquals(hashMap.get(null), "java");
        assertEquals(hashMap.get(12345), "krakov");
    }

    @Test
    public void testPutIfAbsent() {
        assertTrue(hashMap.size() == 0);

        hashMap.putIfAbsent(1234, "anya");
        hashMap.putIfAbsent(12345, "artem");

        assertTrue(hashMap.size() == 2);

        assertEquals(hashMap.get(1234), "anya");
        assertEquals(hashMap.get(12345), "artem");

        hashMap.putIfAbsent(1234, "java");
        hashMap.putIfAbsent(12345, "krakov");

        assertTrue(hashMap.size() == 2);

        assertEquals(hashMap.get(1234), "anya");
        assertEquals(hashMap.get(12345), "artem");
    }

    @Test
    public void testGet() {
        hashMap.put(1234, "anya");
        hashMap.put(12345, "artem");

        assertTrue(hashMap.get(1234).equals("anya"));
        assertTrue(hashMap.get(12345).equals("artem"));

        hashMap.put(1234, "sea");
        assertTrue(hashMap.get(1234).equals("sea"));
    }

    @Test
    public void testRemove() {
        assertTrue(hashMap.size() == 0);

        hashMap.put(null, "anya");
        hashMap.put(12345, "artem");

        assertTrue(hashMap.size() == 2);

        hashMap.remove(null);

        assertTrue(hashMap.size() == 1);
        assertFalse(hashMap.containsKey(null));

        hashMap.remove(12345);

        assertTrue(hashMap.size() == 0);
        assertFalse(hashMap.containsKey(12345));
    }

    @Test
    public void testContainsKey() {
        assertTrue(hashMap.size() == 0);

        hashMap.put(1234, "anya");
        hashMap.put(12345, "artem");

        assertTrue(hashMap.size() == 2);

        assertTrue(hashMap.containsKey(1234) && hashMap.containsKey(12345));

        hashMap.remove(1234);

        assertFalse(hashMap.containsKey(1234));

        hashMap.remove(12345);

        assertFalse(hashMap.containsKey(345));
        assertFalse(hashMap.containsKey(12345));
    }

    @Test
    public void testContainsValue() {
        assertTrue(hashMap.size() == 0);

        hashMap.put(1234, "anya");
        hashMap.put(12345, "artem");

        assertTrue(hashMap.size() == 2);

        assertTrue(hashMap.containsValue("anya") && hashMap.containsValue("artem"));
        assertFalse(hashMap.containsValue("java"));

        hashMap.remove(1234);

        assertFalse(hashMap.containsValue("anya"));
    }

    @Test
    public void testKeyList() {
        hashMap.put(1234, "anya");
        hashMap.put(12345, "artem");
        hashMap.put(23, "flower");
        hashMap.put(123674, "anya");
        hashMap.put(1200, "artem");
        hashMap.put(10, "flower");

        ArrayList list = hashMap.keyList();

        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + ",");
        }
    }

    @Test
    public void testValueList() {
        hashMap.put(1234, "anya");
        hashMap.put(12345, "artem");
        hashMap.put(23, "flower");
        hashMap.put(123674, "anya");
        hashMap.put(1200, "artem");
        hashMap.put(10, "flower");

        ArrayList list = hashMap.valueList();

        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + ",");
        }
    }

    @Test
    public void testEntryList(){
        hashMap.put(1234, "anya");
        hashMap.put(12345, "artem");
        hashMap.put(23, "flower");
        hashMap.put(123674, "anya");
        hashMap.put(1200, "artem");
        hashMap.put(10, "flower");

        ArrayList list = hashMap.entryList();

        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + ",");
        }
    }

    @Test
    public void testSize(){
        assertTrue(hashMap.size() == 0);

        hashMap.put(1234, "anya");
        assertTrue(hashMap.size() == 1);

        hashMap.put(12345, "artem");
        assertTrue(hashMap.size() == 2);

        hashMap.put(23, "flower");
        hashMap.put(123674, "anya");

        assertTrue(hashMap.size() == 4);

        hashMap.remove(1234);

        assertTrue(hashMap.size() == 3);
    }

    @Test
    public void testIsEmpty(){
        assertTrue(hashMap.isEmpty());

        hashMap.put(null, "anya");
        assertTrue(!hashMap.isEmpty());

        hashMap.remove(null);
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void testClear(){
        assertTrue(hashMap.isEmpty());

        hashMap.put(1234, "anya");
        assertTrue(hashMap.size() == 1);

        hashMap.put(12345, "artem");
        assertTrue(hashMap.size() == 2);

        hashMap.clear();

        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void testPutAll(){
        assertTrue(hashMap.isEmpty());

        HashMap<Object,String> newHashMap = new HashMap<>();

        newHashMap.put(1234, "anya");
        newHashMap.put(12345, "artem");
        newHashMap.put(null, "anya");

        assertTrue(newHashMap.size() == 3);

        hashMap.putAll(newHashMap);
        assertTrue(hashMap.size() == 3);

        assertEquals(hashMap.get(1234), "anya");
        assertEquals(hashMap.get(12345), "artem");
        assertEquals(hashMap.get(null), "anya");
    }
}
