package arrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {
    private final ArrayList testArrayList = new ArrayList();

    @BeforeEach
    public void setUp() {
        for(int i =0; i < 10; i++) {
            testArrayList.add(i);
        }
    }

    @Test
    void add() {
        testArrayList.add(11);
        assertTrue(testArrayList.contains(11));
        assertEquals(11,testArrayList.size());
    }

    @Test
    void addAtIndex() {
        testArrayList.addAtIndex(0, 12);
        assertEquals(12,testArrayList.get(0));
        assertEquals(0,testArrayList.get(1));
        assertThrows(IndexOutOfBoundsException.class,()-> testArrayList.addAtIndex(100, 1000));
    }

    @Test
    void remove() {
        testArrayList.remove(0);
        assertFalse(testArrayList.contains(0));
        assertEquals(9,testArrayList.size());
    }

    @Test
    void removeAtIndex() {
        assertThrows(IndexOutOfBoundsException.class,()->testArrayList.remove(10));
        testArrayList.removeAtIndex(8);
        assertFalse(testArrayList.contains(8));
    }

    @Test
    void get() {
        assertThrows(IndexOutOfBoundsException.class,()->testArrayList.get(20));
        assertEquals(0,testArrayList.get(0));
    }

    @Test
    void set() {
        assertThrows(IndexOutOfBoundsException.class,()->testArrayList.set(25,100));
        testArrayList.set(0,100);
        assertEquals(100,testArrayList.get(0));
    }
}