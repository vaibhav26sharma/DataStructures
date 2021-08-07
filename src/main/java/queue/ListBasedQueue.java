package queue;

import java.util.Iterator;
import java.util.List;

public class ListBasedQueue<T> implements Queue<T>, Iterable<T> {
    private int size;
    private Node front;
    private Node rear;

    public ListBasedQueue() {
        this.size = 0;
        this.front = null;
        this.rear = null;
    }

    static class Node<T> {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
        }
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
        //Reference of the front node
        Node<T> temp = front;
        while (temp != null) {
            if (temp.data.equals(element)) {
                return true;
            }
            temp = temp.next;
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
        Node<T> new_node = new Node<>(element);

        //If queue is empty, new node will both be front and rear
        if (rear == null) {
            front = new_node;
            rear = new_node;
            size++;
            return;
        }

        //Add this node as next of current rear
        rear.next = new_node;
        rear = new_node;
        size++;
    }

    /**
     * This method removes and returns from the front of the queue
     *
     * @return the front of the queue
     */
    @Override
    public T pop() {
        if (rear == null)
            throw new IndexOutOfBoundsException("Queue is empty.");

        //Get reference of previous front to return
        Node<T> previousFront = front;
        front = front.next;
        //If front becomes null, rear should also be null
        if (front == null) {
            rear = null;
        }
        size--;
        return previousFront.data;
    }

    /**
     * This method returns the front of the queue without removing it
     *
     * @return the front of the queue
     */
    @Override
    public T peek() {
        if (rear == null)
            throw new IndexOutOfBoundsException("Queue is empty.");
        return (T) front.data;
    }

   
    /**
     * Empties the queue by removing all the elements
     */
    @Override
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            // Reference to the front
            Node<T> cursor = front;

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public T next() {
                T data = cursor.data;
                cursor = cursor.next;
                return data;
            }
        };
    }

    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        if (front == null && rear == null) {
            return "[]";
        }
        StringBuilder output = new StringBuilder();
        output.append("[");
        Node<T> temp = front;
        while (temp != null && temp.next != null) {
            output.append(temp.data).append(", ");
            temp = temp.next;
        }
        output.append(temp.data).append("]");
        return output.toString();
    }

    static class QueueNode<T> {
        T data;
        QueueNode<T> next;

        QueueNode(T data) {
            this.data = data;
        }
    }

}
