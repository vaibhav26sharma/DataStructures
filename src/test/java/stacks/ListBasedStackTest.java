package stacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListBasedStackTest {

    private final ListBasedStack<Integer> testListBasedStack = new ListBasedStack<Integer>();

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 5; i++) {
            testListBasedStack.push(i);
        }
    }

    @Test
    void size() {
        assertNotEquals(0, testListBasedStack.size());
        assertEquals(5, testListBasedStack.size());
    }

    @Test
    void isEmpty() {
        assertFalse(testListBasedStack.isEmpty());
        for (int i = 1; i <= 5; i++) {
            testListBasedStack.pop();
        }
        assertTrue(testListBasedStack.isEmpty());
    }

    @Test
    void contains() {
        assertTrue(testListBasedStack.contains(5));
        assertFalse(testListBasedStack.contains(100));
    }

    @Test
    void push() {
        testListBasedStack.push(500);
        assertTrue(testListBasedStack.contains(500));
        assertEquals(500, testListBasedStack.pop());
    }

    @Test
    void pop() {
        assertEquals(5, testListBasedStack.pop());
        assertNotEquals(5, testListBasedStack.peek());
    }

    @Test
    void peek() {
        assertEquals(5, testListBasedStack.peek());
        testListBasedStack.pop();
        assertEquals(4, testListBasedStack.peek());
    }

    @Test
    void clear() {
        testListBasedStack.clear();
        assertThrows(IndexOutOfBoundsException.class, () -> testListBasedStack.pop());
    }
}