package trie;

import java.util.LinkedList;
import java.util.List;

public class TrieImpl implements Trie {

    private final TrieNode root;

    //Create a dummy root node
    public TrieImpl() {
        this.root = new TrieNode('\u0000');
    }

    /**
     * @return true, if trie is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root.children == null || root.children.isEmpty();
    }

    /**
     * @param word to be inserted in the Trie
     */
    @Override
    public void insert(String word) {
        //Check if word is already in the trie
        if (search(word)) {
            return;
        }

        //Reference of the root node
        TrieNode temp = root;
        for (char c : word.toCharArray()) {
            //Get the child of curent character (Starting from root)
            TrieNode child = temp.getChild(c);
            // If the child is not null, we will move to the child
            // of the current temp
            if (child != null) {
                temp = child;
            }
            // If there is no child, then we will add current character
            // as the new node
            else {
                temp.children.add(new TrieNode(c));
                temp = temp.getChild(c);
            }
            temp.count++;
        }
        // Set last node as the leaf node
        temp.isLeaf = true;
    }

    /**
     * @param word to be removed from the trie
     * @return true, if the word is removed, false otherwise
     */
    @Override
    public boolean remove(String word) {
        return false;
    }

    /**
     * @param word to be searched
     * @return true if the key is found, false otherwise
     */
    @Override
    public boolean search(String word) {
        //Check if Trie is empty
        if (isEmpty()) {
            return false;
        }
        //Reference of the root node
        TrieNode temp = root;
        // Search in the trie character by character
        for (char c : word.toCharArray()) {
            // If the char is not found in the trie node
            if (temp.getChild(c) == null) {
                return false;
            }
            // If found, move to its child
            else {
                temp = temp.getChild(c);
            }
        }
        // If we reach to the leaf node while searching, it means
        // we have found the word
        return temp.isLeaf;
    }

    /**
     * Empties the trie by deleting all nodes
     */
    @Override
    public void clear() {

    }

    @Override
    public List<String> getAllWords() {
        return null;
    }

    static class TrieNode {
        private final char data;
        private boolean isLeaf;
        private int count;
        private List<TrieNode> children;

        TrieNode(char data) {
            this.data = data;
            this.isLeaf = false;
            this.count = 0;
            this.children = new LinkedList<>();
        }

        public TrieNode getChild(char c) {
            for (TrieNode child : children) {
                if (child.data == c) {
                    return child;
                }
            }
            return null;
        }
    }
}
