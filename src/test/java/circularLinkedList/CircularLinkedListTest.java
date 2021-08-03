package circularLinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import singlyLinkedList.SinglyLinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {

    private final CircularLinkedList testCircularLinkedList = new CircularLinkedList();

    @BeforeEach
    public void setUp() {
        for (int i = 1; i <= 10; i++) {
            testCircularLinkedList.add(i);
        }
    }

    @Test
    public void testAdd() {
        assertEquals(1,testCircularLinkedList.head.data);
        testCircularLinkedList.add(11);
        assertEquals(11,testCircularLinkedList.tail.data);
        assertEquals(1,testCircularLinkedList.tail.next.data);
        assertEquals(11,testCircularLinkedList.size());
    }

    @Test
    public void testPush() {
        testCircularLinkedList.push(11);
        assertEquals(11,testCircularLinkedList.head.data);
        assertEquals(11,testCircularLinkedList.size());
        assertEquals(10,testCircularLinkedList.tail.data);
    }

    @Test
    public void testAddAfterNode() {
        assertThrows(IllegalArgumentException.class,()->testCircularLinkedList.addAfterNode(50,100));
        testCircularLinkedList.addAfterNode(100,10);
        assertEquals(11, testCircularLinkedList.size());
        assertEquals(100,testCircularLinkedList.tail.data);
    }

    @Test
    public void testRemove() {
        testCircularLinkedList.remove(10);
        assertEquals(9, testCircularLinkedList.tail.data);
        assertEquals(1,testCircularLinkedList.tail.next.data);
        assertEquals(9,testCircularLinkedList.size());
    }

    @Test
    public void testRemoveHead() {
        testCircularLinkedList.removeHead();
        assertEquals(2, testCircularLinkedList.head.data);
        assertEquals(9,testCircularLinkedList.size());
        testCircularLinkedList.removeHead();
        assertEquals(3,testCircularLinkedList.head.data);
    }
}