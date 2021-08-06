package stacks;

import java.util.Iterator;

public class ListBasedStack<T> implements Stack<T>, Iterable<T> {
    private Node<T> top;
    private int size;

    public ListBasedStack() {
        this.size = 0;
        this.top = null;
    }

    static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    /**
     * @return size of the stack
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
        //OR
        //return top == null;
    }

    /**
     * @param element to be searched in the stack
     * @return true if element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        Node<T> temp = top;
        while (temp != null) {
            if (temp.data.equals(element))
                return true;
            temp = temp.next;
        }
        return false;
    }

    /**
     * This method stores element at the top of the stack
     *
     * @param element to be stored in the stack
     */
    @Override
    public void push(T element) {
        Node<T> new_node = new Node<>(element);
        new_node.next = top;
        top = new_node;
        size++;
    }

    /**
     * This method removes and returns the top of the stack
     *
     * @return the top of the stack
     */
    @Override
    public T pop() {
        if (top == null) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    /**
     * This method returns the top of the stack without removing it
     *
     * @return the top of the stack
     */
    @Override
    public T peek() {
        if (top == null) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        return top.data;
    }


    /**
     * Empties the stack by removing all the elements
     */
    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    /**
     * @return an iterator over the elements in stack in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            //Reference fo the top
            Node<T> temp = top;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T next() {
                T data = temp.data;
                temp = temp.next;
                return data;
            }
        };
    }


    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder output = new StringBuilder();
        output.append("[");
        // Reference of the top
        Node<T> temp = top;
        while (temp.next != null) {
            output.append(temp.data).append(", ");
            temp = temp.next;
        }
        output.append(temp.data).append("]");
        return output.toString();
    }


}
