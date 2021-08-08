package queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBasedQueueTest {

    private final ArrayBasedQueue<Integer> testArrayBasedQueue = new ArrayBasedQueue<>(10);

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 5; i++) {
            testArrayBasedQueue.push(i);
        }
    }

    @Test
    void size() {
        assertNotEquals(0, testArrayBasedQueue.size());
        assertEquals(5, testArrayBasedQueue.size());
    }

    @Test
    void isEmpty() {
        assertFalse(testArrayBasedQueue.isEmpty());
        testArrayBasedQueue.clear();
        assertTrue(testArrayBasedQueue.isEmpty());
    }

    @Test
    void contains() {
        assertFalse(testArrayBasedQueue.contains(5));
        testArrayBasedQueue.push(5);
        assertTrue(testArrayBasedQueue.contains(5));
    }

    @Test
    void push() {
        for (int i = 100; i < 105; i++) {
            testArrayBasedQueue.push(i);
        }
        assertEquals(0, testArrayBasedQueue.pop());
    }

    @Test
    void pop() {
        assertEquals(0, testArrayBasedQueue.pop());
        assertEquals(1, testArrayBasedQueue.pop());
    }

    @Test
    void peek() {
        assertEquals(0, testArrayBasedQueue.peek());
        testArrayBasedQueue.push(1000);
        assertNotEquals(1000, testArrayBasedQueue.peek());
    }

    @Test
    void clear() {
        assertFalse(testArrayBasedQueue.isEmpty());
        testArrayBasedQueue.clear();
        assertTrue(testArrayBasedQueue.isEmpty());
    }
}