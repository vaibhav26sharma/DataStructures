package cache;

import java.util.HashMap;

public class LRUCache {
    //Entry will have the value
    HashMap<Integer, Entry> hashmap;
    Entry head, tail;
    int LRU_SIZE = 4;

    public LRUCache() {
        hashmap = new HashMap<Integer, Entry>();
    }

    public int getEntry(int key) {
        if (hashmap.containsKey(key)) {
            Entry entry = hashmap.get(key);
            //Remove accessed record from its position
            //since it wl be moved to frnt nw
            removeNode(entry);
            //move accessed entry to front/top
            addToTop(entry);
            return entry.value;
        }
        return -1;
    }

    public void putEntry(int key, int value) {
        //if already exists then just make it most recent
        //update its value
        //and move it to the top
        if (hashmap.containsKey(key)) {
            Entry entry = hashmap.get(key);
            entry.value = value;
            removeNode(entry);
            addToTop(entry);
        } else {
            Entry newNode = new Entry();
            newNode.left = null;
            newNode.right = null;
            newNode.value = value;
            newNode.key = key;

            //if LRU size is reached,
            //remove LRU node from tail
            //and add new one at the top
            if (hashmap.size() > LRU_SIZE) {
                hashmap.remove(tail.key);
                removeNode(tail);
                addToTop(newNode);
            }
            //if map has space, add new entry to top
            else {
                addToTop(newNode);
            }
        }
    }

    public void addToTop(Entry node) {
        //Since node will be head now, old head will be node.right
        node.right = head;
        node.left = null;
        if (head != null) {
            head.left = node;
        }
        head = node;
        if (tail == null) {
            tail = head;
        }
    }

    public void removeNode(Entry node) {
        if (node.left != null) {
            node.left.right = node.right;
        } else {//if node was the head
            head = node.right;
        }

        if (node.right != null) {
            node.right.left = node.left;
        } else {
            tail = node.left;
        }
    }
}

class Entry {
    int value;
    int key;
    Entry left;
    Entry right;
}
