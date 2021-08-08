package queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import singlyLinkedList.List;

import static org.junit.jupiter.api.Assertions.*;

class ListBasedQueueTest {

    private final ListBasedQueue<Integer> testListBasedQueue = new ListBasedQueue<>();

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 5; i++) {
            testListBasedQueue.push(i);
        }
    }

    @Test
    void size() {
        assertNotEquals(0, testListBasedQueue.size());
        assertEquals(5, testListBasedQueue.size());
    }

    @Test
    void isEmpty() {
        assertFalse(testListBasedQueue.isEmpty());
        testListBasedQueue.clear();
        assertTrue(testListBasedQueue.isEmpty());
    }

    @Test
    void contains() {
        assertFalse(testListBasedQueue.contains(5));
        testListBasedQueue.push(5);
        assertTrue(testListBasedQueue.contains(5));
    }

    @Test
    void push() {
        for (int i = 100; i < 105; i++) {
            testListBasedQueue.push(i);
        }
        assertEquals(0, testListBasedQueue.pop());
    }

    @Test
    void pop() {
        assertEquals(0, testListBasedQueue.pop());
        assertEquals(1, testListBasedQueue.pop());
    }

    @Test
    void peek() {
        assertEquals(0, testListBasedQueue.peek());
        testListBasedQueue.push(1000);
        assertNotEquals(1000, testListBasedQueue.peek());
    }

    @Test
    void clear() {
        assertFalse(testListBasedQueue.isEmpty());
        testListBasedQueue.clear();
        assertTrue(testListBasedQueue.isEmpty());
    }
}