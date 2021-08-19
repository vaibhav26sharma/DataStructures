package graphs;

public interface Graph<T> {
    /**
     * @param u - value of the vertex to be added
     */
    void addVertex(T u);

    /**
     * @param u - value of the vertex to be removed
     */
    void removeVertex(T u);

    /**
     * This method add a new edge(s) between given vertices
     *
     * @param u - source vertex
     * @param v - destination vertex
     */
    void addEdge(T u, T v);

    /**
     * This method removes the edge(s) between given vertices
     *
     * @param u - source vertex
     * @param v - destination vertex
     */
    void removeEdge(T u, T v);

    /**
     * @param u - given vertex
     * @return number of nodes coming out of this edge
     * or
     * total items in the linkedlist of this vertex's index
     */
    int outDegree(T u);

    /**
     * @param u - given vertex
     * @return number of nodes coming to this edge
     */
    int inDegree(T u);
}