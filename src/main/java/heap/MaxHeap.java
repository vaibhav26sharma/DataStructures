package heap;

public class MaxHeap<T extends Comparable<T>> implements Heap<T> {
    /**
     * @return size of the heap
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * @return true, if heap is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * @param element to be searched in the heap
     * @return true, if the element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        return false;
    }

    /**
     * @param element to be added in the heap
     */
    @Override
    public void push(T element) {

    }

    /**
     * @return the element at the root of the heap, after removing it
     */
    @Override
    public T poll() {
        return null;
    }

    /**
     * @return the element at the root of the heap, without removing it
     */
    @Override
    public T peek() {
        return null;
    }

    /**
     * Empties the queue by deleting all the elements
     */
    @Override
    public void clear() {

    }
}
