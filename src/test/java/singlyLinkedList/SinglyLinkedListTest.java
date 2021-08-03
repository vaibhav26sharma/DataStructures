package singlyLinkedList;
import singlyLinkedList.SinglyLinkedList;
import singlyLinkedList.SinglyLinkedList.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SinglyLinkedListTest {


    private final SinglyLinkedList testSinglyLinkedList = new SinglyLinkedList();

    @BeforeEach
    public void setUp() {
        for (int i = 1; i <= 10; i++) {
            testSinglyLinkedList.push(i);
        }
    }

    @Test
    public void testPush() {
        testSinglyLinkedList.push(11);
        testSinglyLinkedList.push(12);
        Assertions.assertEquals(12, testSinglyLinkedList.size());
        testSinglyLinkedList.printList();
        System.out.println("Last "+testSinglyLinkedList.findLast());
    }

    @Test
    public void testInsertAfter() {
        testSinglyLinkedList.insertAfter(testSinglyLinkedList.head.next, 18);
        Assertions.assertEquals(18, testSinglyLinkedList.head.next.next.data);
        Assertions.assertEquals(8, testSinglyLinkedList.head.next.next.next.data);
        Assertions.assertEquals(11, testSinglyLinkedList.size());
    }

    @Test
    public void testAppend() {
        testSinglyLinkedList.append(111);
        Node t = testSinglyLinkedList.head;
        while (t.next != null)
            t = t.next;
        Assertions.assertEquals(111, t.data);
    }

    @Test
    public void testSize() {
        testSinglyLinkedList.push(20);
        Assertions.assertEquals(11,testSinglyLinkedList.size());
    }

    @Test
    public void removeFirst() {
        for (int i = 10; i >= 1; i--) {
            System.out.println(i);
            Assertions.assertEquals(i, testSinglyLinkedList.removeFirst());
        }
        Assertions.assertEquals(0,testSinglyLinkedList.size());
        Assertions.assertNull(testSinglyLinkedList.head);
    }

    @Test
    public void testIsEmpty() {
        for (int i = 10; i >= 1; i--) {
            Assertions.assertEquals(i, testSinglyLinkedList.removeFirst());
        }
        Assertions.assertTrue(testSinglyLinkedList.isEmpty());
        Assertions.assertNull(testSinglyLinkedList.head);
    }

    @Test
    void testRemoveLast() {
        for (int i = 1; i <= 10; i++) {
            Assertions.assertEquals(i, testSinglyLinkedList.removeLast());
        }
        Assertions.assertEquals(0,testSinglyLinkedList.size());
        Assertions.assertTrue(testSinglyLinkedList.isEmpty());
        Assertions.assertNull(testSinglyLinkedList.head);

    }

    @Test
    void testRemove() {
        Assertions.assertEquals(10,testSinglyLinkedList.remove(10));
        Assertions.assertEquals(5,testSinglyLinkedList.remove(5));
        Assertions.assertEquals(8,testSinglyLinkedList.size());
        Assertions.assertEquals(9,testSinglyLinkedList.remove(testSinglyLinkedList.head.data));
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            testSinglyLinkedList.head=null;
            testSinglyLinkedList.remove(10);
        });
    }

    @Test
    void testContains() {
        Assertions.assertFalse(testSinglyLinkedList.contains(100));
        Assertions.assertTrue(testSinglyLinkedList.contains(10));
        testSinglyLinkedList.push(100);
        Assertions.assertTrue(testSinglyLinkedList.contains(100));
    }

    @Test
    void testClear() {
        testSinglyLinkedList.clear();
        Assertions.assertThrows(IllegalArgumentException.class, testSinglyLinkedList::removeFirst);
    }
}