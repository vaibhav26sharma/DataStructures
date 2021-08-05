package singlyLinkedList;

import java.util.Iterator;

public interface List {

    /**
     * @return the number of elements in the list
     * */
    int size();

    /**
     *
     * @return true, if list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     *
     * @param element - element to be checked in the list
     * @return true, if element is present in the list, false otherwise
     */
    boolean contains(int element);

    /**
     *
     * @param element to be added in the list
     */
    void push(int element);


    /**
     * @param element element to be removed from the list
     * @return removed element
     */
    int remove(int element);


    /**
     * Empties the list by removing all the elements from it
     */
    void clear();
}
