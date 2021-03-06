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
        return adjacencyList.get(u).size();
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

    /**
     * @param u - source vertex
     * @return List of vertices in BFS
     */
    @Override
    public List<T> bfs(T u) {
        //List to store the output
        List<T> output = new ArrayList<>();
        //Set to store all the visited nodes
        Set<T> visited = new HashSet<>();
        //Queue to store all the nodes in BFS
        Queue<T> nodes = new LinkedList<>();
        //Add source node to the queue and mark visited
        nodes.add(u);
        visited.add(u);

        //Loop until the queue is empty
        while (!nodes.isEmpty()) {
            //Poll a node from the queue and add it to output
            T current = nodes.poll();
            output.add(current);

            //Get all the adjacent nodes of popped vertex
            for (T node : adjacencyList.get(current)) {
                //Add node/vertex only if it is not visited already
                if (!visited.contains(node)) {
                    visited.add(node);
                    nodes.add(node);
                }
            }
        }
        return output;
    }

    /**
     * @param u - source vertex
     * @return List of vertices in DFS
     */
    @Override
    public List<T> dfs(T u) {
        //List to store the output
        List<T> output = new ArrayList<>();
        //Set to store all the visited nodes
        Set<T> visited = new HashSet<>();
        //Queue to store all the nodes in BFS
        Stack<T> nodes = new Stack<>();
        //Push the source node to the stack
        nodes.push(u);
        while (!nodes.isEmpty()) {
            // Get the top of the stack
            T current = nodes.pop();
            // If the node is not visited before, then only we
            // will add it to the output
            if (!visited.contains(current)) {
                visited.add(current);
                output.add(current);
            }

            // Get all the vertices which are neighbors of the
            // popped vertex and if they are not visited before,
            // add them to the stack
            for (T node : adjacencyList.get(current)) {
                if (!visited.contains(node)) {
                    nodes.push(node);
                }
            }

        }
        return output;
    }
}
