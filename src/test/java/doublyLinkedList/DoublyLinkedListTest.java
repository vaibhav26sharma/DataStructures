package doublyLinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    private final DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 10; i++) {
            doublyLinkedList.add(i);
        }
        System.out.println("Initializing test DLL with elements:");
        doublyLinkedList.printList();
    }

    @Test
    void testPush() {
        doublyLinkedList.push(200);
        assertEquals(200, doublyLinkedList.head.data);
        doublyLinkedList.push(300);
        assertEquals(300, doublyLinkedList.head.data);
        assertEquals(300, doublyLinkedList.head.next.previous.data);
        assertEquals(12, doublyLinkedList.size());
        doublyLinkedList.clear();
        assertTrue(doublyLinkedList.isEmpty());
        assertNull(doublyLinkedList.head);
    }

    @Test
    void addAfterNode() {
        doublyLinkedList.addAfterNode(100, 1);
        assertEquals(100, doublyLinkedList.head.next.data);
        assertEquals(100, doublyLinkedList.head.next.next.previous.data);
        doublyLinkedList.addAfterNode(200, 100);
        assertEquals(200, doublyLinkedList.head.next.next.data);
        assertThrows(IllegalArgumentException.class, () -> doublyLinkedList.addAfterNode(50, 2000));
    }

    @Test
    void add() {
        doublyLinkedList.add(100);
        assertTrue(doublyLinkedList.contains(100));
        assertEquals(11, doublyLinkedList.size());
    }

    @Test
    void testRemove() {
        doublyLinkedList.remove(10);
        assertFalse(doublyLinkedList.contains(10));
        doublyLinkedList.remove(1);
        assertEquals(2, doublyLinkedList.head.data);
        assertThrows(IllegalArgumentException.class, () -> doublyLinkedList.remove(100));
        assertEquals(7, doublyLinkedList.remove(7));
    }


    @Test
    void testRemoveLast() {
        doublyLinkedList.add(11);//adding an element to the tail
        assertTrue(doublyLinkedList.contains(11));
        doublyLinkedList.removeLast();
        assertFalse(doublyLinkedList.contains(11));
    }

    @Test
    void testRemoveFirst() {
        for(int i =0; i<10; i++) {
            doublyLinkedList.removeFirst();
        }
        assertTrue(doublyLinkedList.isEmpty());
        assertEquals(0,doublyLinkedList.size());
        assertThrows(IllegalArgumentException.class,()->doublyLinkedList.remove(10));
    }
}