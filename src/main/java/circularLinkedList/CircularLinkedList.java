package circularLinkedList;

import singlyLinkedList.List;

public class CircularLinkedList implements List {
    public Node head;
    public Node tail;
    private int size;

    CircularLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(int element) {
        if (head == null) {
            return false;
        } else {
            Node temp = head;
            do {
                if (temp.data == element)
                    return true;

                temp = temp.next;
            } while (temp != head); //Run the loop until temp again comes back to head. Can't use while(temp.next!=null)
            // since temp.next wont be null for any node in circular LL
        }
        return  false;
    }

    /**
     *
     * @param element to be added at the end of the list
     */
    public void add(int element) {
        Node new_node = new Node(element);
        if(head == null) {
            head = new_node;
            tail = new_node;
            tail.next = head;
            size++;
            return;
        }

        tail.next = new_node;
        tail = new_node;
        tail.next = head;
        size++;
    }


    /**
     *
     * @param element to be added in the beginning of the list
     */
    public void push(int element) {
        Node new_node = new Node(element);
        if(head == null) {
            head = tail = new_node;
            tail.next = head;
            size++;
        }
        else {
            new_node.next = head.next;
            head = new_node;
            tail.next = head;
            size++;
        }
    }

    /**
     *
     * @param element to be added after elementInTheList
     * @param elementInTheList is the node after which new element is to be added
     */
    public void addAfterNode(int element, int elementInTheList) {
        if(!contains(elementInTheList)) {
            throw  new IllegalArgumentException("Give node doesn't exist in the list");
        }

        Node temp = head;
        do {
            if(temp.data == elementInTheList)
                break;
            temp = temp.next;
        } while(temp != head);


        Node new_node = new Node(element);
        if(temp == tail) {
            tail = new_node;
            tail.next = head;
        }
        Node next_node = temp.next;
        temp.next = new_node;
        new_node.next = next_node;
        size++;
    }


    /**
     *
     * @param element  to be removed from the list
     * @return - returns the removed element
     */
    @Override
    public int remove(int element) {
        if(!contains(element))
            throw new IllegalArgumentException("Element doesn't exist");

        if(head.data == element){
            head = head.next;
            size--;
            return element;
        }

        if(tail.data == element) {
            //Find reference to element prior to tail
            Node temp = head;
            while(temp.next!=tail) {
                temp = temp.next;
            }

            int data = tail.data;
            tail = temp;
            tail.next = head;
            size--;
            return data;
        }

        Node temp = head;
        do {
            if(temp.next.data == element) {
                break;
            }
            temp = temp.next;
        } while(temp!=head);

        if(temp.next!=null) {
            temp.next = temp.next.next;
        }
        size--;
        return element;
    }


    /**
     *
     * @return the head after removal
     */
    public int removeHead() {
        if(head == null)
            throw new IllegalArgumentException("Cannot delete from an empty list");

        if(head == tail) {
            int data = head.data;
            head = tail = null;
            size--;
            return data;
        }

        int data = head.data;
        Node next_node = head.next;
        tail.next = next_node;
        head = next_node;
        size--;
        return data;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    static class Node{
        Node next;
        int data;

        Node(int data) {
            this.data = data;
        }
    }
}
