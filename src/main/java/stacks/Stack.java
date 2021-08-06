package stacks;

import java.util.Iterator;

public interface Stack<T> {

    /**
     *
     * @return size of the stack
     */
    int size();

    /**
     *
     * @return true if stack is empty, false otherwise
     */
    boolean isEmpty();

    /**
     *
     * @param element to be searched in the stack
     * @return true if element is found, false otherwise
     */
    boolean contains(T element);

    /**
     * This method stores element at the top of the stack
     * @param element to be stored in the stack
     */
    void push(T element);

    /**
     * This method removes and returns the top of the stack
     * @return the top of the stack
     */
    T pop();

    /**
     * This method returns the top of the stack without removing it
     * @return the top of the stack
     */
    T peek();

    /**
     *
     * @return an iterator over the elements in stack in proper sequence
     */
    Iterator<T> iterator();

    /**
     * Empties the stack by removing all the elements
     */
    void clear();
}
