package ua.belaya.collections.list;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public abstract class AbstractListTest {
    private List<Object> list;
    public abstract List<Object> createList();

    @Before
    public void setUp() {
        list = createList();
    }

    @Test
    public void testSet(){
        list.addAll(12, 13, null);
        list.set(0, "anya");
        list.set(1, "artem");
        list.set(2, "java");

        assertEquals(list.get(0),"anya");
        assertEquals(list.get(1),"artem");
        assertEquals(list.get(2),"java");
    }

    @Test
    public void testAdd() {
        assertTrue(list.size() == 0);

        list.add("anya");
        list.add("artem");

        assertTrue(list.size() == 2);

        assertEquals(list.get(0), "anya");
        assertEquals(list.get(1), "artem");
    }

    @Test
    public void testAddByIndex() {
        assertTrue(list.size() == 0);

        list.add(0, "anya");
        list.add(1, "artem");

        assertTrue(list.size() == 2);

        assertEquals(list.get(0), "anya");
        assertEquals(list.get(1), "artem");

        list.add(0, "java");
        list.add(1, "hi");

        assertTrue(list.size() == 4);

        assertEquals(list.get(0), "java");
        assertEquals(list.get(2), "anya");
    }

    @Test
    public void testAddAll() {
        assertTrue(list.size() == 0);

        list.addAll(12, 13, 14, 15);

        assertTrue(list.size() == 4);

        assertEquals(list.get(0), 12);
        assertEquals(list.get(1), 13);
        assertEquals(list.get(2), 14);
        assertEquals(list.get(3), 15);
    }

    @Test
    public void testRemove() {
        assertTrue(list.size() == 0);

        list.remove(null);
        list.addAll(12, 13, 14, 15);
        assertTrue(list.size() == 4);

        list.remove(12);
        assertTrue(list.size() == 3);

        assertEquals(list.get(0), 13);

        list.remove(14);
        assertTrue(list.size() == 2);

        assertEquals(list.get(1), 15);
    }

    @Test
    public void testContains() {
        list.addAll(12, 13, null);

        assertTrue(list.contains(12));
        assertFalse(list.contains("anya"));
        assertTrue(list.contains(13));
        assertTrue(list.contains(null));
        assertFalse(list.contains("artem"));
    }

    @Test
    public void testFirstIndexOf() {
        list.addAll("anya", "artem", "anya", "artem", null, null);

        assertTrue(list.indexOf("anya") == 0);
        assertTrue(list.indexOf("artem") == 1);
        assertTrue(list.indexOf(null) == 4);
    }

    @Test
    public void testLastIndexOf() {
        list.addAll("anya", "artem", "anya", "artem", null, null);

        assertTrue(list.lastIndexOf("anya") == 2);
        assertTrue(list.lastIndexOf("artem") == 3);
        assertTrue(list.lastIndexOf(null) == 5);
    }

    @Test
    public void testClear() {
        assertTrue(list.size() == 0);

        list.addAll("anya", "artem", "anya", "artem", null, null);
        assertTrue(list.size() == 6);

        list.clear();
        assertTrue(list.size() == 0);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());

        list.addAll("anya", "artem", "anya", "artem", null, null);
        assertFalse(list.isEmpty());

        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testGet() {
        list.addAll("anya", "artem", null);

        assertEquals(list.get(0), "anya");
        assertEquals(list.get(1), "artem");
        assertEquals(list.get(2), null);
    }

    @Test
    public void testSize() {
        assertTrue(list.size() == 0);

        list.addAll("anya", "artem", null);
        assertTrue(list.size() == 3);

        list.remove("anya");
        assertTrue(list.size() == 2);
    }

    @Test
    public void testToString() {
        list.add(0, "anya");
        list.add(1, "artem");

        list.add(0, "java");
        list.add(1, "hi");

        System.out.println(list.toString());
    }

    @Test
    public void testNext() {
        Iterator iterator = list.iterator();

        list.addAll("anya", "artem", null);

        assertEquals("anya", iterator.next());
        assertEquals("artem", iterator.next());
        assertEquals(null, iterator.next());
    }

    @Test
    public void testHasNext() {
        Iterator iterator = list.iterator();

        list.addAll("anya", "artem", null);

        iterator.next();
        assertTrue(iterator.hasNext());

        iterator.next();
        assertTrue(iterator.hasNext());

        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRemoveByIteration() {
        Iterator iterator = list.iterator();

        list.addAll("anya", "artem", null, "java");

        assertTrue(list.size() == 4);

        iterator.next();
        iterator.remove();

        assertTrue(list.size() == 3);
        assertEquals(list.get(1), null);

        iterator.next();
        iterator.remove();

        assertTrue(list.size() == 2);
        assertEquals(list.get(0), "artem");
        assertEquals(list.get(1), "java");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenIterationEqualsSizeWhenCallingNextThenExceptionShouldBeRaised() {
        Iterator iterator = list.iterator();

        list.addAll("anya", "artem");
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenIndexBiggerThenSizeWhenCallingAddThenExceptionShouldBeRaised() {
        list.addAll("anya", "artem", null);

        list.add(4, "java");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenIndexLessThen0WhenCallingAddThenExceptionShouldBeRaised() {
        list.add(-1, "java");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenIndexBiggerThenSizeMinusOneWhenCallingGetThenAnExceptionShouldBeRaised() {
        list.addAll("anya", "artem", null);

        list.get(4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenIndexLessThen0WhenCallingGetThenAnExceptionShouldBeRaised() {
        list.addAll("anya", "artem", null);

        list.get(-1);
    }
}
