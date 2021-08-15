package heap;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> implements Heap<T> {

    //List to store the elements
    private final List<T> elements;

    public MaxHeap() {
        this.elements = new ArrayList<>();
    }

    /**
     * @return size of the heap
     */
    @Override
    public int size() {
        return elements.size();
    }

    /**
     * @return true, if heap is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * @param element to be searched in the heap
     * @return true, if the element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        return elements.contains(element);
    }

    /**
     * @param element to be added in the heap
     */
    @Override
    public void push(T element) {
        //Add element at the end of the list i.e. starting from left most leaf of the tree so that
        //tree remains a Complete Binary Tree
        elements.add(element);
        //Call the heapify up on the last index where element is added
        heapifyUp(size() - 1);
    }

    /**
     * <p> This method is used to adjust the added node and move it to the top if needed to maintain a max heap</p>
     *
     * @param index
     */
    private void heapifyUp(int index) {
        //Get the parent index of node where new element is inserted to compare it and move up if needed
        int parentIndex = getParentIndex(index);
        //Compare element and parent to check which is greater. if element is greater than parent, move it up or swap them
        if (index > 0 && elements.get(index).compareTo(elements.get(parentIndex)) > 0) {
            //Swap the two elements
            swap(index, parentIndex);
            //Recursively heapfiy up on the parent index to compare element with other ancestors in the tree
            heapifyUp(parentIndex);//Since element has now moved to parentIndex
        }
    }

    /**
     * @param index of the child whose parent's index is to be found
     * @return the index of parent
     */
    private int getParentIndex(int index) {
        if (index == 0) {
            return 0;
        }
        return (index - 1) / 2;
    }

    /**
     * @param index       , element to be moved it its parent index
     * @param parentIndex to be swapped with element
     */
    private void swap(int index, int parentIndex) {
        T temp = elements.get(index);
        //Swap element with its parent i.e. Move it up
        elements.set(index, elements.get(parentIndex));
        //Move parent down to the element's position
        elements.set(parentIndex, temp);
    }

    /**
     * @return the element at the root of the heap, after removing it
     */
    @Override
    public T poll() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty. Cannot poll!");
        }

        //Get the frst element from the list
        T root = elements.get(0);
        //Get the last element from the rightmost leaf in the list, which will move to the deleted root's position
        T last = elements.get(size() - 1);
        //Set last element as the root of the heap
        elements.set(0, last);
        //Remove the last element from the list to avoid duplication
        elements.remove(size() - 1);
        //Call heapify down on the new root node to compare it with its descendants/children and readjust if needed to maintain a Max heap
        heapifyDown(0);
        return root;
    }

    private void heapifyDown(int index) {
        //Get left & right child of root i.e. 2*i * 2*i+1 to compare them
        int left = 2 * index;
        int right = 2 * index + 1;
        //Find the index with largest value
        int largest = index;
        //Compare left with root element's to check if left is greater and we need to move new root downwards
        if (left < size() && elements.get(left).compareTo(elements.get(index)) > 0) {
            largest = left;
        }
        if (right < size() && elements.get(right).compareTo(elements.get(index)) > 0) {
            largest = right;
        }

        //If we found either left or right child as larger than new root then move if down or heapifyDown
        if (largest != index) {
            swap(index, largest);
            heapifyUp(largest);//Since new root that was added has now been moved to largest's index after swap
        }
    }

    /**
     * @return the element at the root of the heap, without removing it
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty. Cannoy peek!");
        }
        return elements.get(0);
    }

    /**
     * Empties the queue by deleting all the elements
     */
    @Override
    public void clear() {
        elements.clear();
    }
}
