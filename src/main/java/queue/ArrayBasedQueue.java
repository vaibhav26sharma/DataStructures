package queue;

import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayBasedQueue<T> implements Queue<T>, Iterable<T> {
    private Object[] elements;
    private int rear;
    private int front;
    private int size;
    private int capacity;

    public ArrayBasedQueue(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[capacity];
        this.rear = -1;
        this.front = -1;
        this.size = 0;
    }

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
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element) && elements[i] != null)
                return true;
        }
        return false;
    }

    /**
     * This method stores element at the rear of the queue
     *
     * @param element to be stored in the queue
     */
    @Override
    public void push(T element) {
        if (size == capacity) {
            throw new IndexOutOfBoundsException("Queue is full");
        } else if (front == -1) { //Empty Queue
            front = 0;
            rear = 0;
            elements[rear] = element;
        } else if (rear == size - 1 && front != 0) { //if rear has reached the end of index and front isn't 0 i.e. there is space in the front of the queue
            rear = 0;
            elements[rear] = element;
        } else {
            rear++;
            elements[rear] = element;
            size++;
        }
    }

    /**
     * This method removes and returns from the front of the queue
     *
     * @return the front of the queue
     */
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty");
        }
        Object data = elements[front];
        elements[front] = null;
        front++;
        if (front == capacity) {
            front = 0;
        }
        size--;
        return (T) data;
    }

    /**
     * This method returns the front of the queue without removing it
     *
     * @return the front of the queue
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty");
        }
        return (T) elements[front];
    }

    /**
     * @return an iterator over the elements in queue in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor != capacity;
            }

            @Override
            public T next() {
                Object data = elements[cursor];
                cursor++;
                return (T) data;
            }
        };
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder output = new StringBuilder();
        output.append("[");
        for (int i = 0; i < size - 1; i++) {
            output.append(elements[i]).append(", ");
        }
        output.append(elements[size - 1]).append("]");
        return output.toString();
    }

    /**
     * Empties the queue by removing all the elements
     */
    @Override
    public void clear() {
        // Make all the elements in the internal array null
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
}
