package heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxHeapTest {

    private final MaxHeap<Integer> testMaxHeap = new MaxHeap<>();

    @BeforeEach
    void setUp() {
        for (int i = 0; i <= 9; i++) {
            testMaxHeap.push(i);
        }
    }

    @Test
    void testSize() {
        assertEquals(10, testMaxHeap.size());
        assertEquals(9, testMaxHeap.poll());
        testMaxHeap.push(20);
        assertEquals(10, testMaxHeap.size());
    }

    @Test
    void testIsEmpty() {
        assertFalse(testMaxHeap.isEmpty());
        testMaxHeap.clear();
        assertTrue(testMaxHeap.isEmpty());
    }

    @Test
    void testContains() {
        for (int i = 0; i <= 9; i++) {
            assertTrue(testMaxHeap.contains(i));
        }
    }

    @Test
    void testPush() {
        for (int i = 10; i <= 19; i++) {
            testMaxHeap.push(i);
            assertTrue(testMaxHeap.contains(i));
            assertEquals(i + 1, testMaxHeap.size());
        }
    }

    @Test
    void testPoll() {
        assertEquals(10, testMaxHeap.size());
        testMaxHeap.poll();
        testMaxHeap.poll();
        testMaxHeap.poll();
        testMaxHeap.poll();
        assertEquals(6, testMaxHeap.size());
    }

    @Test
    void peek() {
        testMaxHeap.poll();
        assertEquals(8, testMaxHeap.peek());
        testMaxHeap.clear();
        assertThrows(IndexOutOfBoundsException.class, testMaxHeap::peek);
    }

    @Test
    void clear() {
        assertFalse(testMaxHeap.isEmpty());
        testMaxHeap.clear();
        assertTrue(testMaxHeap.isEmpty());
    }
}