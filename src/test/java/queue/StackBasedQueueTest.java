package queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackBasedQueueTest {

    private final StackBasedQueue<Integer> testStackBasedQueue = new StackBasedQueue<>();

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 5; i++) {
            testStackBasedQueue.push(i);
        }
    }

    @Test
    void size() {
        assertNotEquals(0, testStackBasedQueue.size());
        assertEquals(5, testStackBasedQueue.size());
    }

    @Test
    void isEmpty() {
        assertFalse(testStackBasedQueue.isEmpty());
        testStackBasedQueue.clear();
        assertTrue(testStackBasedQueue.isEmpty());
    }

    @Test
    void contains() {
        assertFalse(testStackBasedQueue.contains(5));
        testStackBasedQueue.push(5);
        assertTrue(testStackBasedQueue.contains(5));
    }

    @Test
    void push() {
        for (int i = 100; i < 105; i++) {
            testStackBasedQueue.push(i);
        }
        assertEquals(0, testStackBasedQueue.pop());
    }

    @Test
    void pop() {
        assertEquals(0, testStackBasedQueue.pop());
        assertEquals(1, testStackBasedQueue.pop());
    }

    @Test
    void peek() {
        assertEquals(0, testStackBasedQueue.peek());
        testStackBasedQueue.push(1000);
        assertNotEquals(1000, testStackBasedQueue.peek());
    }

    @Test
    void clear() {
        assertFalse(testStackBasedQueue.isEmpty());
        testStackBasedQueue.clear();
        assertTrue(testStackBasedQueue.isEmpty());
    }
}