package arrayList;

import singlyLinkedList.List;

import java.util.Iterator;

public class ArrayList implements List {
    private int size;
    //Internal array used for storing data
    private int[] elements;

    public ArrayList() {
        this.size = 0;
        //Initialize internal array with a size of 10
        this.elements = new int[10];
    }

    /**
     *
     * @return the size of the arraylist
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     *
     * @return true if arraylist is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     *
     * @param element - element to be checked in the list
     * @return true if element is in the list, false otherwise
     */
    @Override
    public boolean contains(int element) {
        for (int e : elements) {
            if (e == element) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>This method adds the specified element at the end of list.</p>
     * @param element to be added in the list
     */
    @Override
    public void add(int element) {
        //Check if array has space
        if (elements.length == size) {
            ensureCapacity();
        }
        elements[size++] = element;
    }

    /**
     * <p></p>
     * @param index at which new element is to be added
     * @param element to be added at the index
     */
    public void addAtIndex(int index, int element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index :" + index + " ,size: " + size);
        }

        if (elements.length == size) {
            ensureCapacity();
        }

        //Move all the element after the provided index to right by one position so that new element can fit at that place
        int i = index;
        while (i < size) {
            elements[i + 1] = elements[i];
            i++;
        }
        elements[index] = element;
        size++;
    }

    /**
     * <p>This method removes the specified element from the list</p>
     * @param element element to be removed from the list
     * @return the removed element
     */
    @Override
    public int remove(int element) {
        //index of element to be deleted
        int index = -1;
        //Search for the element in the list
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) {
                index = i;
                break;
            }
        }
        return removeAtIndex(index);
    }

    /**
     * <p>This method removes the element at specified index, if the index is legal</p>
     * @param index at which element to be removed
     * @return the removed element at the index
     */
    public int removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index :" + index + " ,size: " + size);
        }
        int removeElement = elements[index];
        int i = index;
        //Move all the elements after the removed element one place left
        while (i < size-1) {
            elements[i] = elements[i + 1];
            i++;
        }
        size--;
        return removeElement;

    }

    /**
     * <p>Clears out all the elements in the array</p>
     */
    @Override
    public void clear() {
        //Make all object null in the list
        for (int i = 0; i < size; i++) {
            elements[i] = -1;
        }
        size = 0;
    }

    /**
     *
     * @param index from which element is to be fetched
     * @return the element from the specified index
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index :" + index + " ,size: " + size);
        }
        return elements[index];
    }

    /**
     *
     * @param index at which the element is to be stored
     * @param element which is to be stored at the specified index in the list
     */
    public void set(int index, int element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index :" + index + " ,size: " + size);
        }
        elements[index] = element;
    }

    /**
     * @return Returns an iterator over the elements in this list in proper sequence.
     */
    @SuppressWarnings("unchecked")
    public Iterator<Integer> iterator() {

        return new Iterator<Integer>() {
            // Index of the next element to return
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public Integer next() {
                return  elements[cursor++];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("[");
        for (int i = 0; i < size - 1; i++) {
            output.append(elements[i]).append(", ");
        }
        output.append(elements[size - 1]).append("]");
        return output.toString();
    }


    private void ensureCapacity() {
        int newCapacity = 2 * elements.length;
        //Create new array with twice the capacity of last one
        int[] updatedElement = new int[newCapacity];
        //Copy all elements on old array to new array
        System.arraycopy(elements, 0, updatedElement, 0, elements.length);
        //Assign the updated array to the original array
        elements = updatedElement;
    }
}
