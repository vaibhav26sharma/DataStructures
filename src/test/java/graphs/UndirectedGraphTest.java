package graphs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UndirectedGraphTest {

    private final Graph<String> testUndirectedGraph = new UndirectedGraph<>();

    @BeforeEach
    void setUp() {
        String[] vertices = new String[]{"A", "B", "C", "D", "E", "F"};
        for (String vertex : vertices) {
            testUndirectedGraph.addVertex(vertex);
        }
        testUndirectedGraph.addEdge("A", "B");
        testUndirectedGraph.addEdge("A", "C");
        testUndirectedGraph.addEdge("A", "D");
        testUndirectedGraph.addEdge("B", "F");
        testUndirectedGraph.addEdge("B", "E");
        testUndirectedGraph.addEdge("D", "E");
        testUndirectedGraph.addEdge("E", "F");
        testUndirectedGraph.addEdge("C", "D");
    }

    @Test
    void testAddVertex() {
        testUndirectedGraph.addVertex("G");
        testUndirectedGraph.addEdge("E", "G");
        testUndirectedGraph.addEdge("F", "G");
        assertEquals(2, testUndirectedGraph.inDegree("G"));
        assertEquals(2, testUndirectedGraph.outDegree("G"));
        assertEquals(3, testUndirectedGraph.outDegree("F"));
        assertEquals(4, testUndirectedGraph.outDegree("E"));
    }

    @Test
    void testRemoveVertex() {
        assertThrows(IllegalArgumentException.class, () -> testUndirectedGraph.removeVertex("G"));
        testUndirectedGraph.removeVertex("A");
        assertEquals(2, testUndirectedGraph.outDegree("B"));
    }

    @Test
    void addEdge() {
        testUndirectedGraph.addEdge("B", "D");
        assertEquals(4, testUndirectedGraph.outDegree("B"));
    }

    @Test
    void removeEdge() {
        testUndirectedGraph.removeEdge("A", "B");
        assertEquals(2, testUndirectedGraph.inDegree("A"));
        assertThrows(IllegalArgumentException.class, () -> testUndirectedGraph.removeEdge("A", "G"));
    }

    @Test
    void outDegree() {
        assertEquals(3, testUndirectedGraph.outDegree("A"));
        assertEquals(3, testUndirectedGraph.outDegree("B"));
        assertEquals(2, testUndirectedGraph.outDegree("C"));
    }

    @Test
    void inDegree() {
        assertEquals(2, testUndirectedGraph.inDegree("C"));
        assertEquals(3, testUndirectedGraph.inDegree("D"));
    }

    @Test
    void getVertexCount() {
    }

    @Test
    void getEdgesCount() {
    }

    @Test
    void hasVertex() {
    }

    @Test
    void hasEdge() {
    }

    @Test
    void testBfs() {
        List<String> expected = Arrays.asList("A", "B", "C", "D", "F", "E");
        assertEquals(expected, testUndirectedGraph.bfs("A"));
        expected = Arrays.asList("B", "A", "F", "E", "C", "D");
        assertEquals(expected, testUndirectedGraph.bfs("B"));
    }


    @Test
    void testDfs() {
        List<String> expected = Arrays.asList("A", "D", "C", "E", "F", "B");
        assertEquals(expected, testUndirectedGraph.dfs("A"));
        expected = Arrays.asList("B", "E", "F", "D", "C", "A");
        assertEquals(expected, testUndirectedGraph.dfs("B"));
    }
}