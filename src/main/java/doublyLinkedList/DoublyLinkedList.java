package doublyLinkedList;

import singlyLinkedList.List;
import singlyLinkedList.SinglyLinkedList;

public class DoublyLinkedList implements List {
    int size;
    Node head;

    DoublyLinkedList() {
        this.size = 0;
        this.head = null;
    }

    static class Node {
        int data;
        Node previous;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    /*------ Utility Methods ------- */

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(int element) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == element)
                return true;
            temp = temp.next;
        }
        return false;
    }


    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * This function prints the contents of SinglyLinkedlist
     */
    public void printList() {
        DoublyLinkedList.Node tnode = head;
        while (tnode != null) {
            System.out.println(tnode.data + " ");
            tnode = tnode.next;
        }
    }


    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("[");
        // Reference of the head
        Node temp = head;
        while (temp.next != null) {
            output.append(temp.data).append(", ");
            temp = temp.next;
        }
        output.append(temp.data).append("]");
        return output.toString();
    }

    static class ListNode<T> {
        T data;
        ListNode<T> previous;
        ListNode<T> next;

        ListNode(T data) {
            this.data = data;
        }
    }

    /*------ Insertion Methods ------- */


    /**
     * this function is to insert a new element at the front
     * @param element to be added in the list
     */
    @Override
    public void push(int element) {
        Node new_node = new Node(element);
        if(head==null) {
            head = new_node;
            size++;
            return;
        }

        new_node.next = head;
        head.previous = new_node;
        head = new_node;
        size++;
    }

    /**
     *
     * @param element to be added in the list
     * @param elementInTheList after which new element is to be inserted
     */
    public void addAfterNode(int element, int elementInTheList) {
        if(!contains(elementInTheList)) {
            throw new IllegalArgumentException("Given node does not exist in the list");
        }

        Node temp = head;
        while(temp.next!=null) {  // Get the reference of the node after which the new node is to be inserted
            if(temp.data == elementInTheList) {
                break;
            }
            temp = temp.next;
        }

        Node new_node = new Node(element);
        Node next_node = temp.next;
        next_node.previous = new_node;
        temp.next = new_node;
        new_node.previous = temp;
        new_node.next = next_node;
        size++;
    }

    /**
     *
     * @param element to be inserted at the end of the list
     */
    public void add(int element) {
        Node new_node = new Node(element);

        if(head == null) {
            head = new_node;
            size++;
            return;
        }

        Node temp = head;
        while(temp.next!=null) {
            temp = temp.next;
        }

        temp.next = new_node;
        new_node.previous = temp;
        size++;
    }

    /*------ Deletion Methods ------- */


    /**
     *
     * @param element  to be removed from the list
     * @return the deleted element
     */
    @Override
    public int remove(int element) {
        if(!contains(element)) {
            throw new IllegalArgumentException("Give node does not exist");
        }

        //If head is the element to be removed
        if(head.data == element) {
            head = head.next;
            head.previous = null;
            size--;
            return  element;
        }

        //Fetch the reference of node to be deleted
        Node temp = head;
        while(temp.next!=null) {
            if(temp.data==element) {
                break;
            }
            temp = temp.next;
        }

        //if next of element to be deleted isn't null, update the previous ref of its next element
        if(temp.next!=null) {
            temp.next.previous = temp.previous;
        }

        //if previous of element to be deleted isn't null, update the next ref of its previous element
        if(temp.previous!=null) {
            temp.previous.next = temp.next;
        }

        size--;
        return element;
    }

    /**
     *
     * @returns and removes the last element of the list
     */
    public int removeLast() {
        if(head == null) {
            throw new IllegalArgumentException("Cannot remove from an empty list");
        }
        //if head doesn't have a next element, then there is only 1 ele in the list which will be
        //treated as last
        if(head.next == null) {
            int data = head.data; //Fetching data to return
            head = null;
            size = 0;
            return data;
        }

        Node temp = head;
        while(temp.next!=null) {
            temp = temp.next;
        }

        int data = temp.data;
        temp.previous.next = null; //Set last 2nd element as tail
        size--;
        return data;
    }

    /**
     *
     * @returns & removes the first element of the list
     */
    public int removeFirst() {
        if(head == null) {
            throw new IllegalArgumentException("Cannot remove from an empty list");
        }

        int data = head.data;
        head = head.next;
        if(head!=null)
            head.previous = null;
        size--;
        return data;
    }
}
