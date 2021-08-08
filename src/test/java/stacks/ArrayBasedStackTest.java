package stacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBasedStackTest {

    private final ArrayBasedStack<Integer> testArrayBasedStack = new ArrayBasedStack<Integer>(10);


    @BeforeEach
    void setUp() {
        for (int i = 0; i <= 5; i++) {
            testArrayBasedStack.push(i);
        }
    }

    @Test
    void size() {
        assertEquals(6, testArrayBasedStack.size());
        testArrayBasedStack.push(100);
        assertEquals(7, testArrayBasedStack.size());
    }

    @Test
    void isEmpty() {
        assertFalse(testArrayBasedStack.isEmpty());
        for (int i = 0; i <= 5; i++) {
            testArrayBasedStack.pop();
        }
        assertTrue(testArrayBasedStack.isEmpty());
    }

    @Test
    void contains() {
        assertTrue(testArrayBasedStack.contains(4));
        assertFalse(testArrayBasedStack.contains(400));
    }

    @Test
    void push() {
        testArrayBasedStack.push(500);
        assertTrue(testArrayBasedStack.contains(500));
        assertEquals(500, testArrayBasedStack.peek());
    }

    @Test
    void pop() {
        testArrayBasedStack.push(111);
        assertEquals(111, testArrayBasedStack.pop());
    }

    @Test
    void peek() {
        assertEquals(5, testArrayBasedStack.peek());
        testArrayBasedStack.push(500);
        assertEquals(500, testArrayBasedStack.peek());
    }

    @Test
    void clear() {
        testArrayBasedStack.clear();
        assertThrows(IndexOutOfBoundsException.class, () -> testArrayBasedStack.peek());
    }
}