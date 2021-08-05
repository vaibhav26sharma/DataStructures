package singlyLinkedList;

import java.util.Iterator;

public class SinglyLinkedList implements List {

    public Node head;
    public int size;

    public static class Node {
        public int data;
        public Node next;

        Node(int d) {
            data = d;
            next = null;
        }

    }


    /*-----Insertion Methods(Reference URL- https://www.geeksforgeeks.org/linked-list-set-2-inserting-a-node/)-----*/

    /**
     * This method inserts an element at the
     * beginning of singly linked list.
     * As an input it takes the data of new node
     * <p>
     */
    public void add(int data) {
        Node new_node = new Node(data);
        new_node.next = head;
        head = new_node;
        size++;
    }

    /**
     * This method inserts an element
     * after a provided previous element.
     */
    public void insertAfter(Node prev_node, int data) {
        if (prev_node == null) {
            System.out.println("Previou node cannot be null");
            return;
        }

        Node new_node = new Node(data);
        new_node.next = prev_node.next;
        prev_node.next = new_node;
        size++;
    }

    /**
     * This method inserts an element
     * at the tail of the linedlist
     */
    public void append(int data) {
        Node new_node = new Node(data);

        if (head == null) {
            head = new_node;
            size++;
            return;
        }

        Node last = head;
        while (last.next != null) {
            last = last.next;
        }

        last.next = new_node;
        size++;
    }

    /*-----Removal Methods(Reference URL- https://www.geeksforgeeks.org/linked-list-set-2-inserting-a-node/)-----*/

    /**
     * This function removes first element of SinglyLinkedlist
     */
    public int removeFirst() {
        if(head==null)
            throw new IllegalArgumentException("Cannot remove from an empty list");

        int data = head.data;
        head = head.next;
        size--;
        return data;
    }

    /**
     * This function removes last element of SinglyLinkedlist
     */
    public int removeLast() {
        int data=0;
        if(head==null)
            throw new IllegalArgumentException("Cannot remove from an empty list");

        if(head.next == null) //Only one element in the list i.e. head = tail
        {
            data = head.data;
            head = null;
            size--;
            return data;
        }

        Node temp = head;
        while(temp.next.next!=null) {
            temp = temp.next; //Fetching last 2nd node to make it the tail
        }
        data = temp.next.data;
        temp.next=null;
        size--;
        return data;

    }

    /**
     * This function removes the specified element of SinglyLinkedlist
     */
    public int remove(int element) {
        if(head ==null)
            throw new IllegalArgumentException("Cannot remove from an empty list");

        if(head.data == element) { //i.e. head is to be deleted
            head = head.next;
            size--;
            return  element;
        }

        Node temp = head;
        while(temp.next!=null){
            if(temp.next.data == element) { //Find previous node of the node to be deleted to update its next pointer
                break;
            }
            temp = temp.next;
        }

        if(temp.next!=null) {
            temp.next = temp.next.next;//Move next ptr of previous element to element being deleted by +1, so that it skips element to be deleted
        }
        size--;
        return element;
    }

    /*-----Utility Methods(Reference URL- https://www.geeksforgeeks.org/linked-list-set-2-inserting-a-node/)-----*/

    /**
     * This function prints the contents of SinglyLinkedlist
     */
    public void printList() {
        Node tnode = head;
        while (tnode != null) {
            System.out.println(tnode.data + " ");
            tnode = tnode.next;
        }
    }


    public int findLast() {
        Node temp = head;
        while(temp.next!=null)
            temp = temp.next;
        return temp.data;
    }


    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public boolean contains(int element) {
        Node temp = head;
        while(temp!=null) {        // Loop through the linked list to check each element
            if(temp.data == element)
                return true;
            temp = temp.next;
        }
        return false;
    }

    public void clear() {
        head=null;
        size=0;
    }

    @Override
    public  String toString() {
        StringBuilder output = new StringBuilder();
        output.append("[");
        Node temp = head;
        while(temp.next!=null) {
            output.append(temp.data).append(",");
            temp= temp.next;
        }
        output.append(temp.data).append("]");
        return output.toString();
    }
}