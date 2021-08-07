package queue;

import java.util.Iterator;

public interface Queue<T> {
    /**
     * @return size of the queue
     */
    int size();

    /**
     * @return true if queue is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * @param element to be searched in the queue
     * @return true if element is found, false otherwise
     */
    boolean contains(T element);

    /**
     * This method stores element at the rear of the queue
     *
     * @param element to be stored in the queue
     */
    void push(T element);

    /**
     * This method removes and returns from the front of the queue
     *
     * @return the front of the queue
     */
    T pop();

    /**
     * This method returns the front of the queue without removing it
     *
     * @return the front of the queue
     */
    T peek();

    /**
     * @return an iterator over the elements in queue in proper sequence
     */
    Iterator<T> iterator();

    /**
     * Empties the queue by removing all the elements
     */
    void clear();
}
