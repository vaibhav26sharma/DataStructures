package graphs;

import java.util.*;

public class UndirectedGraph<T> implements Graph<T> {

    //Adjacency list for the graph
    private final Map<T, List<T>> adjacencyList;

    public UndirectedGraph() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * @param u - value of the vertex to be added
     */
    @Override
    public void addVertex(T u) {
        //If vertex is not present in adjacency map
        //create a new node with vertex's value pointing to a linkedlist
        adjacencyList.putIfAbsent(u, new ArrayList<>());
    }

    /**
     * @param u - value of the vertex to be removed
     */
    @Override
    public void removeVertex(T u) {
        if (!adjacencyList.containsKey(u)) {
            throw new IllegalArgumentException("Vertex is not present. Cannot remove");
        }
        // First we will remove the vertex from the adjacency list
        // This will remove all the outgoing edges from this node
        adjacencyList.remove(u);

        // Now, to remove the incoming edges to this node, we will
        // lists of all other vertices and delete if there's any edge.
        for (T vertex : adjacencyList.keySet()) {
            // Get the list corresponding to this vertex
            List<T> list = adjacencyList.get(vertex);
            // for (int i = 0; i < list.size(); i++) {
            list.remove(u);
            //}
        }
    }

    /**
     * This method add a new edge(s) between given vertices
     *
     * @param u - source vertex
     * @param v - destination vertex
     */
    @Override
    public void addEdge(T u, T v) {
        // Check if we have vertices with given values, if not,
        // we will create vertex
        if (!adjacencyList.containsKey(u)) {
            addVertex(u);
        }

        if (!adjacencyList.containsKey(v)) {
            addVertex(v);
        }
        // Make bidirectional link between both nodes
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u);
    }

    /**
     * This method removes the edge(s) between given vertices
     *
     * @param u - source vertex
     * @param v - destination vertex
     */
    @Override
    public void removeEdge(T u, T v) {
        if (!adjacencyList.containsKey(u) || !adjacencyList.containsKey(v)) {
            throw new IllegalArgumentException("Vertex is not present in the graph");
        }
        // Get the lists of both the nodes
        List<T> uList = adjacencyList.get(u);
        List<T> vList = adjacencyList.get(v);

        // If the link is present, remove it
        if (uList != null && !uList.isEmpty()) {
            uList.remove(v);
        }

        if (vList != null && !vList.isEmpty()) {
            vList.remove(u);
        }
    }

    /**
     * @param u - given vertex
     * @return number of nodes coming out of this edge
     * or
     * total items in the linkedlist of this vertex's index
     */
    @Override
    public int outDegree(T u) {
        return adjacencyList.get(u).size();
    }

    /**
     * @param u - given vertex
     * @return number of nodes coming to this edge
     */
    @Override
    public int inDegree(T u) {
        return 0;
    }

    /**
     * @return number of vertices in the graph
     */
    public int getVertexCount() {
        return adjacencyList.keySet().size();
    }

    /**
     * @return number of edges in a graph
     */
    public int getEdgesCount() {
        int count = 0;
        //Add all nodes in all the lists and divide by 2 since they are bi-directional
        for (T v : adjacencyList.keySet()) {
            count += adjacencyList.get(v).size();
        }
        return count / 2;
    }

    /**
     * @param u - vertex to check for in the graph
     * @return true if vertex exists, false otherwise
     */
    public boolean hasVertex(T u) {
        return adjacencyList.containsKey(u);
    }

    /**
     * @param u - source vertex
     * @param v - destination vertex
     * @return - true if there is an edge between source and destination, false otherwise
     */
    public boolean hasEdge(T u, T v) {
        return adjacencyList.get(u).contains(v);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (T v : adjacencyList.keySet()) {
            //Appending the vertex
            sb.append(v.toString() + ": ");
            for (T u : adjacencyList.get(v)) {
                //Appending vertices in respective vertex's list
                sb.append(u.toString() + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
