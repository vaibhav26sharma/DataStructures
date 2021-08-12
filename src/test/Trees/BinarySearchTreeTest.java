package Trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    private final BinarySearchTree<Integer> testBST = new BinarySearchTree<>();

    @BeforeEach
    void setUp() {
        int[] elements = {5, 7, 1, 6, 3, 9, 2, 8, 4, 0, 20, 25, 30};
        for (int element : elements) {
            testBST.add(element);
        }
    }


    @Test
    void height() {
        System.out.println(testBST.height());
    }

    @Test
    void isEmpty() {
    }

    @Test
    void size() {
    }

    @Test
    void add() {
        assertTrue(testBST.contains(8));
    }

    @Test
    void remove() {
    }

    @Test
    void contains() {
    }

    @Test
    void clear() {
    }
}