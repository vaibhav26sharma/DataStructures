package heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> implements Heap<T> {

    //Internal list to store hte elements of the heap
    private final List<T> elements;

    public MinHeap() {
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
        // Add element to the end of the list
        elements.add(element);
        // Call the heapify up on the last index where the element is added
        heapifyUp(size() - 1);
    }

    private void heapifyUp(int index) {
        // Get the value at the parent of the current index
        int parentIndex = getParentIndex(index);
        //Check if parent above is larger, then swap, since this is Min heap
        //and the smaller element should move up
        if (index > 0 && elements.get(parentIndex).compareTo(elements.get(index)) > 0) {
            //Swap both elements since element is smaller than parent, move it up
            swap(index, parentIndex);
            // Recursively heapify up until the heap property is met
            heapifyUp(parentIndex);
        }
    }

    /**
     * @param index of node whose parent's index is returned
     * @return parent index of the index passed
     */
    private int getParentIndex(int index) {
        if (index == 0)
            return 0;
        return (index - 1) / 2;
    }

    //Swap the index element with its parent
    private void swap(int index, int parentIndex) {
        T temp = elements.get(index);
        elements.set(index, elements.get(parentIndex));
        elements.set(parentIndex, temp);
    }

    /**
     * @return the element at the root of the heap, after removing it
     */
    @Override
    public T poll() {
        // Check if the heap is empty
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty. Cannot poll");
        }
        // Get the root element
        T root = elements.get(0);
        // Get the last element in the heap
        T last = elements.get(size() - 1);
        // Set this last element as the root of the heap
        elements.set(0, last);
        // Delete the last element from the heap
        elements.remove(size() - 1);
        // Since we have moved the last element to the root, heap properly
        // might be violated, so perform heapify down
        heapifyDown(0);
        return root;
    }

    private void heapifyDown(int index) {
        // Get left and right indices of given index
        int left = 2 * index;
        int right = 2 * index + 1;
        // Find the index with smallest value among index, left and right
        int smallest = index;
        if (left < size() && elements.get(left).compareTo(elements.get(index)) < 0) {
            smallest = left;
        }
        if (right < size() && elements.get(right).compareTo(elements.get(smallest)) < 0) {
            smallest = right;
        }
        // Heapify down if needed
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    /**
     * @return the element at the root of the heap, without removing it
     */
    @Override
    public T peek() {
        // Check if the heap is empty
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty. Cannot peek!");
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
