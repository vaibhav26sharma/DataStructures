package Trees;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> {

    // Tracks the number of nodes in this BST
    private int count;

    // This BST is a rooted tree so we maintain a handle on the root node
    private Node root;

    public BinarySearchTree() {
        this.root = null;
        this.count = 0;
    }

    private class Node {
        T data;
        Node left;
        Node right;

        Node(Node left, Node right, T data) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * @return true, if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return the number of nodes in this binary tree
     */
    public int size() {
        return count;
    }


    /**
     * @param element to be added in the bst
     * @return true if element is added, false otherwise
     */
    public boolean add(T element) {
        //Check if the value already exists in this
        //binary tree, if it does ignore adding it
        if (contains(element)) {
            return false;
        } else { //Otherwise add this element to the binary tree
            root = add(root, element);
            count++;
            return true;
        }
    }

    //Private method to recursively add a value in the binary tree
    private Node add(Node node, T element) {
        //Base case: found a leaf node
        if (node == null) {
            node = new Node(null, null, element);
        } else {
            //Pick a subtree to insert the element
            if (element.compareTo(node.data) < 0) {
                //The new element will be inserted in the left subtree of the current node
                node.left = add(node.left, element);
            } else {
                node.right = add(node.right, element);
            }
        }
        return node;
    }

    /**
     * @param element to be removed from the tree
     * @return removed element
     */
    public boolean remove(T element) {
        if (contains(element)) {
            root = remove(root, element);
            count--;
            return true;
        }
        return false;
    }

    //Private method to delete the element recursively
    private Node remove(Node node, T element) {
        if (node == null) return null;

        int compare = element.compareTo(node.data);

        // Dig into left subtree, the value we're looking
        // for is smaller than the current value
        if (compare < 0) {
            node.left = remove(node.left, element);
        }
        // Dig into right subtree, the value we're looking
        // for is greater than the current value
        else if (compare > 0) {
            node.right = remove(node.right, element);
        }
        // Found the node we wish to remove
        else {
            // This is the case where node to be removed has only a right subtree or
            // no subtree at all. In this situation just
            // swap the node we wish to remove with its right child.
            if (node.left == null) {
                return node.right;
            }
            // This is the case where node to be removed has only a left subtree or
            // no subtree at all. In this situation just
            // swap the node we wish to remove with its left child.
            else if (node.right == null) {
                return node.left;
            }
            // When removing a node from a binary tree with two links the
            // successor of the node being removed can either be the largest
            // value in the left subtree or the smallest value in the right
            // subtree. In this implementation I have decided to find the
            // smallest value in the right subtree which can be found by
            // traversing as far left as possible in the right subtree.
            else {
                // Find the leftmost node in the right subtree
                Node tmp = findMin(node.right);

                //Swap the data
                node.data = tmp.data;

                // Go into the right subtree and remove the leftmost node we
                // found and swapped data with. This prevents us from having
                // two nodes in our tree with the same value.
                node.right = remove(node.right, tmp.data);

                // If instead we wanted to find the largest node in the left
                // subtree as opposed to smallest node in the right subtree
                // here is what we would do:
                // Node tmp = findMax(node.left);
                // node.data = tmp.data;
                // node.left = remove(node.left, tmp.data);

            }
        }
        return null;
    }

    // Helper method to find the leftmost node (which has the smallest value)
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Helper method to find the rightmost node (which has the largest value)
    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }


    /**
     * @param element to be searched in the tree
     * @return true, if tree contains the element, false otherwise
     */
    public boolean contains(T element) {
        return contains(root, element);
    }

    //Private recursive method to find an element in the tree
    private boolean contains(Node node, T element) {
        //Base Case: reached bottom, value not found
        if (node == null) return false;

        int compare = element.compareTo(node.data);

        //Dig into the left subtree because the value we're looking for
        //is smaller than the current node's value
        if (compare < 0) {
            return contains(node.left, element);

        }

        //Dig into the right subtree because the value we're looking for
        // is greater than the current node's value
        else if (compare > 0) {
            return contains(node.right, element);
        }

        //Compare = 0 i.e. we found the node we were looking for
        else return true;
    }

    /**
     * @return height of the tree
     */
    public int height() {
        return height(root);
    }

    //Height of tree is distance of root node from the deepest node in the tree i.e.  1 + no of edges on the longest path from root to deepest leaf or number of nodes from root to deepest leaf including both
    //So, height of tree will be the MAX(Height of root's left subtree, Height of root's right subtree) + 1
    //An additional one is for the edge from root to either left or right subtree whichever is longer
    private int height(Node node) {
        if (node == null) return 0;
        int left = height(node.left);
        int right = height(node.right);
        return Math.max(left, right) + 1;
        //OR
        //return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * @return preorder traversal of tree i.e. Node -> L -> R
     */
    public List<T> preorderTraversal() {
        List<T> output = new ArrayList<>();
        preorderTraversal(root, output);
        return output;
    }

    private void preorderTraversal(Node node, List<T> output) {
        if (node == null)
            return;

        //Add current node's data to the list
        output.add(node.data);
        //Traverse left subtree
        preorderTraversal(node.left, output);
        //Traverse right subtree
        preorderTraversal(node.right, output);
    }

    /**
     * @return inorder traversal of tree i.e.  L -> Node -> R
     */
    public List<T> inorderTraversal() {
        List<T> output = new ArrayList<>();
        inorderTraversal(root, output);
        return output;
    }

    private void inorderTraversal(Node node, List<T> output) {
        if (node == null)
            return;

        // Traverse left subtree
        inorderTraversal(node.left, output);
        //Add current node's data to the list
        output.add(node.data);
        //Traverse right subtree
        inorderTraversal(node.right, output);
    }

    /**
     * @return postorder traversal of tree i.e.  L -> R -> Node
     */
    public List<T> postorderTraversal() {
        List<T> output = new ArrayList<>();
        postorderTraversal(root, output);
        return output;
    }

    private void postorderTraversal(Node node, List<T> output) {
        if (node == null)
            return;

        // Traverse left subtree
        postorderTraversal(node.left, output);
        //Traverse right subtree
        postorderTraversal(node.right, output);
        //Add current node's data to the list
        output.add(node.data);
    }

    /**
     * @return level order traversal of the tree or BFS
     */
    public void levelOrderTraversal() {
        //List to store the final output
        //List<List<T>> output = new ArrayList<>();
        //Base case
        if (root == null) {
            System.out.println("No tree");
            return;
        }
        //Queue to store nodes
        Queue<Node> queue = new LinkedList<>();
        //Add root to the queue
        queue.add(root);
        //Loop until the queue is empty
        while (!queue.isEmpty()) {
            //Remove present head
            Node temp = queue.poll();
            System.out.println(temp.data + " ");

            //Enqueue left child
            if (temp.left != null) {
                queue.add(temp.left);
            }

            //Enqueue right child
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
    }

    /**
     * Deletes all nodes from the tree
     */
    public void clear() {

    }
}
