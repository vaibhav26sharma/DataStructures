package queue;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

public class StackBasedQueue<T> implements Queue<T>, Iterable<T> {
    private int size;
    private Stack<T> A;
    private Stack<T> B;

    /**
     * @return size of the queue
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @return true if queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * @param element to be searched in the queue
     * @return true if element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        return A.contains(element);
    }

    /**
     * This method stores element at the rear of the queue
     *
     * @param element to be stored in the queue
     */
    @Override
    public void push(T element) {
        //Move all elements from A to B, so that the new elements can be maintained per FIFO
        while (!A.isEmpty()) {
            B.push(A.pop());
        }
        //Push the new element to A
        A.push(element);
        //Move all elements from B back to A
        while (!B.isEmpty()) {
            A.push(B.pop());
        }
        size++;
    }

    /**
     * This method removes and returns from the front of the queue
     *
     * @return the front of the queue
     */
    @Override
    public T pop() {
        if (A.isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty.");
        }
        size--;
        return A.pop();
    }

    /**
     * This method returns the front of the queue without removing it
     *
     * @return the front of the queue
     */
    @Override
    public T peek() {
        if (A.isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty.");
        }
        return A.peek();
    }

    /**
     * @return an iterator over the elements in queue in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        Collections.reverse(A);
        return A.iterator();
    }

    /**
     * Empties the queue by removing all the elements
     */
    @Override
    public void clear() {
        A.clear();
        size = 0;
    }


    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        if (A.isEmpty()) {
            return "[]";
        }
        StringBuilder output = new StringBuilder();
        output.append("[");
        for (int i = A.size() - 1; i >= 1; i--) {
            output.append(A.get(i)).append(", ");
        }
        output.append(A.get(0)).append("]");
        return output.toString();
    }
}
