package graphs;

import java.util.*;

public class DirectedGraph<T> implements Graph<T> {

    //Adjacency List
    private final Map<T, List<T>> adjacencyList;

    //No of incoming edges to a Vertex
    private final Map<T, Integer> indegree;

    public DirectedGraph() {
        this.adjacencyList = new HashMap<>();
        this.indegree = new HashMap<>();
    }

    /**
     * @param u - value of the vertex to be added
     */
    @Override
    public void addVertex(T u) {
        //If vertex is not present in adjacency list,add it
        adjacencyList.putIfAbsent(u, new ArrayList<>());
        //Initialize indegree of new node to 0
        indegree.put(u, 0);
    }

    /**
     * @param u - value of the vertex to be removed
     */
    @Override
    public void removeVertex(T u) {
        if (!adjacencyList.containsKey(u)) {
            throw new IllegalArgumentException("Vertex is not present in the graph.");
        }

        List<T> uAdjacencyList = adjacencyList.get(u);
        //Loop for every connected vertex
        for (T vertex : uAdjacencyList) {
            //Since this vertex is to be removed,
            //reduce indegree of each of its connceted nodes by 1
            indegree.put(vertex, indegree.getOrDefault(vertex, 0) - 1);
        }

        //Remove vertex from the adjacency list
        //as it will remove all outgoing edges from u
        adjacencyList.remove(u);

        //To remove all incoming edges to u,
        //lists all other vertices and delete
        //incoming edges, if any
        for (T vertex : adjacencyList.keySet()) {
            //Get list corresponding to the vertex
            List<T> list = adjacencyList.get(vertex);
            list.remove(u);
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
        //If u and v vertices are not already there,
        //create them
        if (!adjacencyList.containsKey(u)) {
            addVertex(u);
        }
        if (!adjacencyList.containsKey(v)) {
            addVertex(v);
        }

        //Since source is u, need to add
        //edge to adjacency of u only
        adjacencyList.get(u).add(v);
        // Update the indegree count of the v
        //because v is the destination vertex,
        //hence this edge will come in to v
        indegree.put(v, indegree.getOrDefault(v, 0) + 1);
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
            throw new IllegalArgumentException("Vertex is not present in the graph.");
        }
        //Get list of the soruce node
        List<T> ulist = adjacencyList.get(u);
        if (ulist != null && !ulist.isEmpty()) {
            ulist.remove(v);
            if (indegree.get(v) != 0) {
                //Reduce indegree of v(destination) by 1
                //since we are deleting the edge
                indegree.put(v, indegree.getOrDefault(v, 0) - 1);
            }
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
        return indegree.get(u);
    }

    /**
     * @param u - source vertex
     * @return List of vertices in BFS
     */
    @Override
    public List<T> bfs(T u) {
        // List to store the output
        List<T> output = new ArrayList<>();
        // Set to store all the visited vertices
        Set<T> visited = new HashSet<>();
        // Queue to store all the nodes in the BFS
        Queue<T> nodes = new LinkedList<>();
        // Add source to the queue and mark visited
        nodes.add(u);
        visited.add(u);
        // Loop until the queue is empty
        while (!nodes.isEmpty()) {
            // Poll a node from the queue and add it to the output
            T current = nodes.poll();
            output.add(current);
            // Get all the adjacent nodes of the popped vertex
            for (T node : adjacencyList.get(current)) {
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
        // List to store the output
        List<T> output = new ArrayList<>();
        // Set to store all the visited vertices
        Set<T> visited = new HashSet<>();
        // Stack to store the nodes
        Stack<T> nodes = new Stack<>();
        // Push the source to the stack
        nodes.push(u);
        // Loop until the stack is empty
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
