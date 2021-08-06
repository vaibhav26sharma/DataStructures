package stacks;

import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayBasedStack<T> implements Stack<T>, Iterable<T> {
    //Size of the stack
    private int size;
    //Internal array used to store stack elements
    private Object[] elements;
    //Max capacity of the stack
    private int capacity;

    public ArrayBasedStack(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.elements = new Object[capacity];
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
    }

    /**
     * @param element to be searched in the stack
     * @return true if element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        //Search in the array to find element
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
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
        if (size == capacity) {
            throw new StackOverflowError("Stack if full, cannot add");
        }
        //Store the element at top of the stack
        elements[size] = element;
        size++;
    }

    /**
     * This method removes and returns the top of the stack
     *
     * @return the top of the stack
     */
    @Override
    public T pop() {
        //Check if stack is empty
        if (size == 0) {
            throw new IndexOutOfBoundsException("Stack is empty, cannot Pop!");
        }
        T data = (T) elements[size - 1];
        elements[size - 1] = null;
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
        //Check if stack is empty
        if (size == 0) {
            throw new IndexOutOfBoundsException("Stack is empty, cannot Pop!");
        } else return (T) elements[size - 1];
    }


    /**
     * Empties the stack by removing all the elements
     */
    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * @return an iterator over the elements in stack in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            //Index of next element to return
            int cursor = size - 1;

            @Override
            public boolean hasNext() {
                return cursor != -1;
            }

            @Override
            public T next() {
                return (T) elements[cursor--];
            }
        };
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder output = new StringBuilder();
        output.append("[");
        for (int i = size - 1; i > 0; i--) {
            output.append(elements[i]).append(", ");
        }
        output.append(elements[0]).append("]");
        return output.toString();
    }

}
