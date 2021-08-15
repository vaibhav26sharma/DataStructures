package heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {

    private final MinHeap<Integer> testMinHeap = new MinHeap<>();

    @BeforeEach
    void setUp() {
        for (int i = 9; i >= 0; i--) {
            testMinHeap.push(i);
        }
    }

    @Test
    void size() {
        assertEquals(10, testMinHeap.size());
        testMinHeap.poll();
        testMinHeap.poll();
        assertEquals(8, testMinHeap.size());
        testMinHeap.push(20);
        assertEquals(9, testMinHeap.size());
    }

    @Test
    void isEmpty() {
        assertFalse(testMinHeap.isEmpty());
        for (int i = 0; i <= 9; i++) {
            testMinHeap.poll();
        }
        assertTrue(testMinHeap.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, testMinHeap::poll);
    }

    @Test
    void contains() {
        assertTrue(testMinHeap.contains(9));
        assertFalse(testMinHeap.contains(90));
        testMinHeap.push(90);
        assertTrue(testMinHeap.contains(90));
    }

    @Test
    void push() {
        for (int i = 10; i <= 25; i++) {
            testMinHeap.push(i);
            assertTrue(testMinHeap.contains(i));
            assertEquals(i + 1, testMinHeap.size());
        }
    }

    @Test
    void poll() {
        assertEquals(0, testMinHeap.poll());
        assertEquals(1, testMinHeap.poll());
    }

    @Test
    void peek() {
        testMinHeap.push(-4);
        assertEquals(-4, testMinHeap.peek());
        testMinHeap.poll();
        assertEquals(0, testMinHeap.peek());
    }

    @Test
    void clear() {
        assertFalse(testMinHeap.isEmpty());
        testMinHeap.clear();
        assertTrue(testMinHeap.isEmpty());
    }
}