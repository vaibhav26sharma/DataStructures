package trees;

import java.util.List;

public interface Tree<T> {

    /**
     * @return true, if the tree is empty, false otherwise
     */
    boolean isEmpty();


    /**
     * @param element to be added in the bst
     * @return true if element is added, false otherwise
     */
    boolean add(T element);

    /**
     * @param element to be searched in the tree
     * @return true, if tree contains the element, false otherwise
     */
    boolean contains(T element);

    /**
     * @return height of the tree
     */
    int height();

    /**
     * @return diameter of the tree
     */
    int diameter();


    /**
     * @param element to be removed from the tree
     * @return removed element
     */
    T remove(T element);

    /**
     * @return preorder traversal of the tree
     */
    List<T> preOrderTraversal();

    /**
     * @return preorder traversal of the tree
     */
    List<T> postOrderTraversal();

    /**
     * @return preorder traversal of the tree
     */
    List<List<T>> levelOrderTraversal();

    /**
     * @return left view of the tree
     */
    List<T> leftView();

    /**
     * @return right view of the tree
     */
    List<T> rightView();

    /**
     * @return top view of the tree
     */
    List<T> topView();

    /**
     * @return bottom view of the tree
     */
    List<T> bottomView();

    /**
     * Deletes all nodes from the tree
     */
    void clear();
}
